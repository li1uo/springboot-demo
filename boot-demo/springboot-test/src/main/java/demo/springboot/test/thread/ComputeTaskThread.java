package demo.springboot.test.thread;

import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;

/**
 * @author LILUO
 * @date 2021/05/18
 */
@AllArgsConstructor
public class ComputeTaskThread implements Callable<Integer> {

    private Integer num;

    @Override
    public Integer call() throws Exception {
        return this.num;
    }
}
