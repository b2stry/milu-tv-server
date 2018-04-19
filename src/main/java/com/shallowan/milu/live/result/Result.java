package com.shallowan.milu.live.result;

import lombok.Data;

/**
 * @author ShallowAn
 */
@Data
public class Result<T> {
    private int code;
    private int errCode;
    private String errMsg;
    private T data;

    private Result(T data) {
        this.code = 1;
        this.errCode = 200;
        this.errMsg = "success";
        this.data = data;
    }

    private Result(CodeMsg codeMsg) {
        if (codeMsg == null) {
            return;
        }
        this.code = 0;
        this.errCode = codeMsg.getCode();
        this.errMsg = codeMsg.getMsg();
    }

    /**
     * 成功时候的调用
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 失败时候的调用
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>(codeMsg);
    }
}
