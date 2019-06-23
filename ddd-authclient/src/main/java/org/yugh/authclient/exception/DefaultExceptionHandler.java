package org.yugh.authclient.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yugh.authclient.domain.CodeEnum;
import org.yugh.authclient.utils.ResultJson;

/**
 * // 系统异常
 *
 * @author: 余根海
 * @creation: 2019-04-08 16:15
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    /**
     * 处理所有自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomerException.class)
    public ResultJson handleCustomException(CustomerException e){
        log.error(e.getResultJson().getMsg());
        return e.getResultJson();
    }

    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultJson handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(e.getBindingResult().getFieldError().getField() + e.getBindingResult().getFieldError().getDefaultMessage());
        return ResultJson.failure(CodeEnum.BAD_REQUEST, e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
