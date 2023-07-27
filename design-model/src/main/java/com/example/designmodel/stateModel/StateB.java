package com.example.designmodel.stateModel;

/**
 * StateB
 *
 * @author Gene
 * @version 1.0
 * @description
 * @date 2023/2/1 11:03
 */
public class StateB implements IState{
    @Override
    public void handle() {
        System.out.println("状态B下的操作");
    }
}
 