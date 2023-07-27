package com.example.chainmodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.chainmodel
 * @className FirstHandler
 * @description
 * @date 2023/2/8 11:18
 */
public class FirstHandler extends Handler {
    @Override
    public void doHandler(Object T) {
        System.out.println("this is first handler");
        if (T != null) {
            // 继续传递下一个调用链
            this.next.doHandler(T);
        }
    }
}
 