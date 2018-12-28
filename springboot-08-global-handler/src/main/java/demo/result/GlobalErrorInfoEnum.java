package demo.result;

/**
 * @author LILUO
 * @date 2018/5/11
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface{

    /**成功**/
    SUCCESS("200","success"),

    /**参数错误**/
    PARAMETER_ERROR("400", "parameter error"),

    /***服务器错误*/
    SERVER_ERROR("500","server_error");

    private String code;

    private String message;

    GlobalErrorInfoEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
