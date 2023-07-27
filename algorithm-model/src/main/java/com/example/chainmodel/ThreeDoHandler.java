package com.example.chainmodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.chainmodel
 * @className ThreeDoHandler
 * @description
 * @date 2023/2/8 11:22
 */
public class ThreeDoHandler extends Handler{
    @Override
    public void doHandler(Object T) {
        System.out.println("this is Three DoHandler");
        if (T != null) {
            // 注意以什么处理方式为结束处理....
            // this.next.doHandler(T);
        } else {
            System.out.println("结束调用链...");
        }
    }

    public static void main(String[] args) {
        Integer objectT = 5;
        FirstHandler firstHandler = new FirstHandler();
        TwoHandler twoHandler = new TwoHandler();
        ThreeDoHandler threeDoHandler = new ThreeDoHandler();
        firstHandler.setNext(twoHandler);
        twoHandler.setNext(threeDoHandler);
        firstHandler.doHandler(objectT);
        System.out.println("===========================================");
        // 方式二: 构建者
        Handler.Builder builder = new Handler.Builder();
        builder.addHandler(firstHandler).addHandler(twoHandler).addHandler(threeDoHandler);
        Handler headHandler = builder.build();
        headHandler.doHandler(objectT);
    }
}
 