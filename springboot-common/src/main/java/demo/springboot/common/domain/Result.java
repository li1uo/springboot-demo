package demo.springboot.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回数据
 *
 * @author LILUO
 * @date 2020/01/30
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -4014814974930090557L;

    /**
     * 返回码
     */
    private int code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public Result() {
        this(ResultCode.SUCCESS);
    }

    public Result(int code, String message){
        this.code = code;
        this.msg = message;
    }

    public Result(IResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMessage());
    }

    public Result(T t) {
       this(ResultCode.SUCCESS);
       this.data = t;
    }

    /**
     * 返回成功结果
     *
     * @return
     */
    public static Result success(){
        return new Result();
    }

    /**
     * 返回自定义消息结果
     *
     * @param message
     * @return
     */
    public static Result success(String message){
        Result result = new Result();
        result.setMsg(message);
        return result;
    }

    /**
     * 返回错误
     *
     * @param code
     * @param message
     * @return
     */
    public static Result fail(int code, String message){
        return new Result(code, message);
    }

    /**
     * 返回错误
     *
     * @param resultCode
     * @return
     */
    public static Result fail(IResultCode resultCode){
        return fail(resultCode.getCode(), resultCode.getMessage());
    }


    /**
     * 返回数据
     *
     * @param t
     * @return
     */
    public static <T> Result<T> data(T t){
        return new Result<>(t);
    }
}
