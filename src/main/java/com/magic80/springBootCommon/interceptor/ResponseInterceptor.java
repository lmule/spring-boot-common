package com.magic80.springBootCommon.interceptor;


import com.magic80.springBootCommon.exception.BaseBizException;
import com.magic80.springBootCommon.exception.InternalServerException;
import com.magic80.springBootCommon.exception.RequestParameterException;
import com.magic80.springBootCommon.helper.ResponseHelper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseInterceptor implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // TODO:
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof BaseBizException) {
            return ResponseHelper.renderException((BaseBizException) o);
        }
        return ResponseHelper.renderSuccess(o);
    }

    /**
     * 兜底的异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public BaseBizException defaultExceptionHandler(Exception ex) {
        return new InternalServerException(ex);
    }

    /**
     * post请求参数异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseBizException postParameterExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorList = new ArrayList<>();
        if (bindingResult != null && bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fe : fieldErrors) {
                errorList.add(fe.getField() + ":" + fe.getDefaultMessage());
            }
        }
        return new RequestParameterException(
                errorList.stream().collect(Collectors.joining(","))
        );
    }

    /**
     * get请求参数异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseBizException getParameterExceptionHandler(ConstraintViolationException ex) {
        return new RequestParameterException(
                ex.getConstraintViolations().stream().map(v->v.getMessage()).collect(Collectors.joining(";"))
        );
    }

    /**
     * 基于BaseBizException的异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(BaseBizException.class)
    public BaseBizException baseBizExceptionHandler(BaseBizException ex){
        return ex;
    }
}
