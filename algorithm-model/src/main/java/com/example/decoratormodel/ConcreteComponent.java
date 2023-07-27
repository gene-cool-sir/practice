package com.example.decoratormodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.decoratormodel
 * @className ConcreteComponent
 * @description
 * @date 2023/2/9 11:05
 */
public class ConcreteComponent implements Component{
    @Override
    public void doSomething() {
        System.out.println("原始对象做得某些事情");
    }
}
 