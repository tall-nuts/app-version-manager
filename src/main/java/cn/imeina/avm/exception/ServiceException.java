package cn.imeina.avm.exception;

/**
 * 业务处理异常
 */
public class ServiceException extends Exception{
    private String code;
    private String message;

    public ServiceException(BusinessStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public ServiceException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}