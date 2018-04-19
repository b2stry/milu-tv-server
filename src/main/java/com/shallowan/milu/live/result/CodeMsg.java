package com.shallowan.milu.live.result;

import lombok.Data;

/**
 * @author ShallowAn
 */
@Data
public class CodeMsg {
    private int code;
    private String msg;

    //通用异常
    public static CodeMsg SUCEESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常！");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s!");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求参数非法！");
    public static CodeMsg ACCESS_LIMIT_REACHED = new CodeMsg(500103, "访问太频繁！");


    public static CodeMsg CREATE_ERROR = new CodeMsg(500200, "创建房间失败！");
    public static CodeMsg QUERY_ERROR = new CodeMsg(500201, "获取直播房间失败！");
    public static CodeMsg QUERY_LIST_ERROR = new CodeMsg(500202, "获取直播房间列表失败！");
    public static CodeMsg QUIT_ERROR = new CodeMsg(500203, "创建房间失败！");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... objects) {
        int code = this.code;
        String message = String.format(this.msg, objects);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
