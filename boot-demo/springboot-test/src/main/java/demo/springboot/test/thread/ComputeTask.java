package demo.springboot.test.thread;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 多线程计算
 *
 * @author LILUO
 * @date 2021/05/18
 */
public class ComputeTask {

    @SneakyThrows
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Integer sum = 0;

        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            list.add(executorService.submit(new ComputeTaskThread(i)));
        }

        for (Future<Integer> future : list) {
            sum = sum + future.get();
        }
        executorService.shutdown();
        System.out.println("sum = " + sum);
    }
}
