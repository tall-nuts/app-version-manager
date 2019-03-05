package cn.imeina.avm.dto;

/**
 * @author gaopengfei
 * @date 2019-02-27
 * @description 响应结果基类
 */
public class BaseResult<T> {

    private String code;
    private String message;
    private T data;

    public BaseResult() {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
