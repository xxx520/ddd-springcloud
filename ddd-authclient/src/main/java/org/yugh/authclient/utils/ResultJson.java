package org.yugh.authclient.utils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.yugh.authclient.domain.CodeEnum;

import java.io.Serializable;

/**
 * //返回数据
 *
 * @author: 余根海
 * @creation: 2019-04-08 15:55
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Data
public final class ResultJson<T> implements Serializable {

    @Setter(AccessLevel.PRIVATE)
    private Integer code;
    @Setter(AccessLevel.PRIVATE)
    private String msg;
    @Setter(AccessLevel.PRIVATE)
    private T result;

    private ResultJson() {
    }


    public static ResultJson ok() {
        return ok("");
    }

    public static ResultJson ok(Object o) {
        return new ResultJson(CodeEnum.SUCCESS, o);
    }

    public static ResultJson failure(CodeEnum code) {
        return failure(code, "");
    }

    public static ResultJson failure(CodeEnum code, Object o) {
        return new ResultJson(code, o);
    }

    public ResultJson(CodeEnum resultCode) {
        setResultCode(resultCode);
    }

    public ResultJson(CodeEnum resultCode, T result) {
        setResultCode(resultCode);
        this.result = result;
    }

    public void setResultCode(CodeEnum resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +
                ", \"msg\":\"" + msg + '\"' +
                ", \"data\":\"" + result + '\"' +
                '}';
    }
}
