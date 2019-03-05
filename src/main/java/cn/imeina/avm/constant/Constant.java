package cn.imeina.avm.constant;

public class Constant {

    public static final String KEY_USER = "user";
    public static final String USER_SESSION = "userSession";
    public static final String KEY_ERROR_MSG = "errorMsg";

    /**
     * RequestMapping 路径
     */
    public static class ActionPath {
        public static final String INDEX = "/";
        public static final String SIGN_IN = "/signIn";
        public static final String LOGIN = "/login";
        public static final String APP_LIST = "/allApp";
        public static final String APP_CREATE = "/create";
        public static final String APP_UPDATE = "/update";
        public static final String APP_UPGRADE = "/upgrade";
        public static final String APP_DELETE = "/delete/{appId}";
        public static final String APP_UPGRADE_STATUS_UPDATE = "/statusUpdate";
        public static final String APP_CHECK_UPGRADE = "/checkUpgrade";
    }

    /**
     * Page视图名称
     */
    public static class Page {
        public static final String LOGIN = "login";
        public static final String INDEX = "index";
    }

    /**
     * Http错误码
     */
    public static class HttpCode {
        public static final String LOGIN_ERROR = "-000001";
    }

    /**
     * Http错误消息
     */
    public static class HttpMessage {
        public static final String ERROR_MSG_LOGIN = "用户名或密码错误";
    }
}
