package com.magic80.springBootCommon.interceptor;

import com.magic80.springBootCommon.annotation.AutoCreateAnnotation;
import com.magic80.springBootCommon.annotation.AutoUpdateAnnotation;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Properties;

@Intercepts({ @Signature(
        type = Executor.class,
        method = "update",
        args = { MappedStatement.class, Object.class })
})
public class AutoUpdateFieldInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        // 获取 SQL 命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 获取参数
        Object parameter = invocation.getArgs()[1];

        Class clazz = parameter.getClass();
        while (!clazz.getName().equals("java.lang.Object")) {
            Field[] declaredFields = clazz.getDeclaredFields();
            Integer currentTimestamp = (int) Math.floor(System.currentTimeMillis() / 1000);
            for (Field field : declaredFields) {
                if (field.getAnnotation(AutoCreateAnnotation.class) != null) {
                    if (SqlCommandType.INSERT.equals(sqlCommandType)) { // insert 语句插入 createTime
                        field.setAccessible(true);
                        field.set(parameter, currentTimestamp);
                    }
                }

                if (field.getAnnotation(AutoUpdateAnnotation.class) != null) { // insert 或 update 语句插入 updateTime
                    if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                        field.setAccessible(true);
                        field.set(parameter, currentTimestamp);
                    }
                }
            }

            clazz = clazz.getSuperclass();
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
