package com.example.designmodel.stateModel;

/**
 * StateA
 *
 * @author Gene
 * @version 1.0
 * @description
 * @date 2023/2/1 11:03
 */
public class StateA implements IState{
    @Override
    public void handle() {
        System.out.println("状态A下的操作");
    }
}
 