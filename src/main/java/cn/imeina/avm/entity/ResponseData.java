package cn.imeina.avm.entity;

import cn.hutool.core.util.StrUtil;

/**
 * API接口响应数据
 */
public class ResponseData {
    /**
     * 响应码
     */
    private String code;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 数据源
     */
    private Object data;

    public ResponseData() {
    }

    public ResponseData(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseData(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseData success() {
        return new ResponseData("000000", "Success");
    }

    public static ResponseData success(Object data){
        return new ResponseData("000000", "Success", data);
    }

    public static ResponseData unauthorized() {
        return new ResponseData("000401", "Unauthorized");
    }

    public static ResponseData badRequest() {
        return new ResponseData("000400", "Bad Request");
    }

    public static ResponseData forbidden() {
        return new ResponseData("000403", "Forbidden");
    }

    public static ResponseData serverInternalError() {
        return new ResponseData("000500", "Server Internal Error");
    }

    public static ResponseData customerError(String message) {
        return new ResponseData("-000001", StrUtil.isEmpty(message) ? "Unknown Error" : message);
    }
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
