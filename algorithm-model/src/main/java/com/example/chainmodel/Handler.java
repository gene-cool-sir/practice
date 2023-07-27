package com.example.chainmodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.chainmodel
 * @className Handler
 * @description
 * @date 2023/2/8 11:10
 */
public abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    /**
     * doHandler
     * @description 责任链调用
     * @date 2023/2/8
     * @param T 贯穿整个卡调用链的对象
     * @return
     */
    public abstract void doHandler(Object T);

    /**
     * 添加一个构造方式
     */
    public static class Builder {
        /**
         * 调用的头对象
         */
        private Handler head;
        /**
         * 调用链的下一个对象
         */
        private Handler tail;

        public Builder addHandler(Handler handler) {
            if (head == null) {
                this.head = handler;
                this.tail = handler;
                return this;
            }
            // 设置下一个调用链
            this.tail.setNext(handler);
            // 赋值下一个调用链是谁
            this.tail = handler;
            return this;
        }

        /**
         * 返回调用链的初始对象
         * @return
         */
        public Handler build(){
            return this.head;
        }
    }
}
 