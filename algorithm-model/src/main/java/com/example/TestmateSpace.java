package com.example;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example
 * @className TestmateSpace
 * @description
 * -Xms200m -Xmx200m -XX:+PrintGC -XX:MetaspaceSize=100m -XX:MaxMetaspaceSize=100m  -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution  -XX:+PrintHeapAtGC -XX:+PrintGCApplicationStoppedTime
 * @date 2023/2/23 11:04
 */
public class TestmateSpace {

    public static void main(String[] args) throws Exception{

        TestmateSpace testmateSpace = new TestmateSpace();
        testmateSpace.test02();;
    }
    private ExecutorService workoutExecutor = new ThreadPoolExecutor(16, 30, 0L,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(30000));
    public void test02() throws Exception{
        List<CompletableFuture<Void>> allTask = new ArrayList<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i =0 ; i< 10000; i++) {
            // 处理WordOutData
            int finalI = i;
            allTask.add(CompletableFuture.runAsync(() -> {
                WechatStepResult wechatStepResult = test03();
                System.out.println(finalI);
            }, workoutExecutor));
        }
        CompletableFuture<Void> all = CompletableFuture.allOf(allTask.toArray(new CompletableFuture[allTask.size()]));
        all.get();
        stopWatch.stop();
        //workoutExecutor.shutdown();
        System.out.println("处理完毕了:" + stopWatch.getTotalTimeMillis());
        test02();
    }
    public  WechatStepResult test03() {
        String aaa = " {\"stepInfoList\":[{\"timestamp\":1673244594,\"step\":3722},{\"timestamp\":1670515200,\"step\":3895},{\"timestamp\":1670601600,\"step\":4290},{\"timestamp\":1670688000,\"step\":10185},{\"timestamp\":1670774400,\"step\":3933},{\"timestamp\":1670860800,\"step\":2247},{\"timestamp\":1670947200,\"step\":9449},{\"timestamp\":1671033600,\"step\":3554},{\"timestamp\":1671120000,\"step\":1629},{\"timestamp\":1671206400,\"step\":3298},{\"timestamp\":1671292800,\"step\":5719},{\"timestamp\":1671379200,\"step\":4343},{\"timestamp\":1671465600,\"step\":3079},{\"timestamp\":1671552000,\"step\":7564},{\"timestamp\":1671638400,\"step\":101},{\"timestamp\":1671724800,\"step\":114},{\"timestamp\":1671811200,\"step\":248},{\"timestamp\":1671897600,\"step\":0},{\"timestamp\":1671984000,\"step\":139},{\"timestamp\":1672070400,\"step\":1492},{\"timestamp\":1672156800,\"step\":2657},{\"timestamp\":1672243200,\"step\":5271},{\"timestamp\":1672329600,\"step\":3283},{\"timestamp\":1672416000,\"step\":2739},{\"timestamp\":1672502400,\"step\":2774},{\"timestamp\":1672588800,\"step\":5636},{\"timestamp\":1672675200,\"step\":3806},{\"timestamp\":1672761600,\"step\":4466},{\"timestamp\":1672848000,\"step\":7167},{\"timestamp\":1672934400,\"step\":5657},{\"timestamp\":1673020800,\"step\":1215}],\"watermark\":{\"timestamp\":1673084365,\"appid\":\"wx9e9ae8d99119d30f\"}}";
        WechatStepResult wechatStepResult = JSONObject.parseObject(aaa, WechatStepResult.class);
        List<WechatStepInfo> stepInfoList = wechatStepResult.getStepInfoList();
        return wechatStepResult;
    }
    public static void test01() {
        try {
           // while (true) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(TestmateSpace.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {

                    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        return -1;
                    }
                });

                enhancer.create();
           // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 