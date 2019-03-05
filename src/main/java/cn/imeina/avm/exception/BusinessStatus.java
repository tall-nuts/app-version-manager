package cn.imeina.avm.exception;

/**
 * 业务状态
 */
public enum BusinessStatus {

    SUCCESS("000000", "操作成功！"),

    FAILURE("-000001", "操作失败！"),

    LOGIN_FAILURE("-000002", "用户名或密码错误！"),

    PARAMETER_ERROR("-000003", "请求参数错误！"),

    APP_EXIST("-000004", "应用已存在！"),

    FILE_UPLOAD_FAILURE("-000005", "文件上传失败！"),

    FILE_PARSE_ERROR("-000006", "文件解析错误！"),

    FILE_FORMAT_ERROR("-000007", "文件格式错误！"),

    APP_NOT_EXIST("-000008", "应用不存在！");

    private final String code;
    private final String message;

    BusinessStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
