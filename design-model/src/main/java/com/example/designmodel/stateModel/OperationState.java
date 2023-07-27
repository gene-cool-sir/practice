package com.example.designmodel.stateModel;

/**
 * OperationState
 *首先是状态模式和责任链模式，责任链是不知道自己下一个处理对象是谁的，责任链的联调是客户端去链接的，而状态模式，自己是知道下一个状态是什么的。
 * 状态模式和策略模式的区别，其实上文中的例子可能大家看到都会觉得这不就是策略模式吗，只能说可以使用策略模式实现，他们有一个最大的区别是策略模式只能挑一种算法去实现（用户去选择其中一种）并且每个算法之间是没有相互关系的，而状态模式各个状态之间是有某种联系的并且用户是不能去指定状态的，只能是初始化状态
 *
 * @author Gene
 * @version 1.0
 * @description
 * @date 2023/2/1 11:03
 */
public class OperationState {
    private IState state;

    private final IState stateB = new StateB();
    private final IState stateA = new StateA();
    public void setState(IState state) {
        this.state = state;
    }

    public void handle(Boolean status) {
        if (status) {
            setState(stateA);
        } else {
            setState(stateB);
        }
        state.handle();
    }

    public static void main(String[] args) {
        OperationState operationState = new OperationState();
        //
        operationState.handle(true);
    }
}
 