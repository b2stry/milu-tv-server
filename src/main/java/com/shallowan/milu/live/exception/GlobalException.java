package com.shallowan.milu.live.exception;


import com.shallowan.milu.live.result.CodeMsg;

/**
 * @author ShallowAn
 */
public class GlobalException extends RuntimeException {

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
