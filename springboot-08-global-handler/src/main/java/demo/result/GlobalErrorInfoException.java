package demo.result;

/**
 * @author LILUO
 * @date 2018/5/11
 */
public class GlobalErrorInfoException extends Exception{

    private ErrorInfoInterface errorInfoInterface;

    public GlobalErrorInfoException(ErrorInfoInterface errorInfoInterface){
        this.errorInfoInterface = errorInfoInterface;
    }

    public ErrorInfoInterface getErrorInfoInterface() {
        return errorInfoInterface;
    }

    public void setErrorInfoInterface(ErrorInfoInterface errorInfoInterface) {
        this.errorInfoInterface = errorInfoInterface;
    }
}
