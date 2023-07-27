package com.example.chainmodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.chainmodel
 * @className TwoHandler
 * @description
 * @date 2023/2/8 11:20
 */
public class TwoHandler extends Handler{
    @Override
    public void doHandler(Object T) {
        System.out.println("this is Two handler");
        if (T != null) {
            // 继续调用下一个调用链
            this.next.doHandler(T);
        } else {
            System.out.println("接收调用链");
        }
    }
}
 