package com.example.decoratormodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.decoratormodel
 * @className ADecorator
 * @description
 * @date 2023/2/9 11:08
 */
public class DecoratorA extends Decorator {
    public DecoratorA(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.component.doSomething();
        System.out.println("装饰器A再做一点事情");
    }
}
 