package demo.result;

/**
 * 返回结果实体类
 * @author LILUO
 * @date 2018/5/11
 */
public class ResultBean {

    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应结果
     */
    private String message;

    /**
     * 返回结果
     */
    private Object result;

    public ResultBean(ErrorInfoInterface errorInfoInterface){
        this.code = errorInfoInterface.getCode();
        this.message = errorInfoInterface.getMessage();
    }

    public ResultBean(Object result){
        this.code = GlobalErrorInfoEnum.SUCCESS.getCode();
        this.message = GlobalErrorInfoEnum.SUCCESS.getMessage();
        this.result = result;
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
