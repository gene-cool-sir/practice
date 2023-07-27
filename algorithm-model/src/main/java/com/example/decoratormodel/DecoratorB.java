package com.example.decoratormodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.decoratormodel
 * @className DecoratorB
 * @description
 * @date 2023/2/9 11:12
 */
public class DecoratorB extends Decorator{
    public DecoratorB(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.component.doSomething();
        System.out.println("DecoratorB 做某些事情");
    }
}
 