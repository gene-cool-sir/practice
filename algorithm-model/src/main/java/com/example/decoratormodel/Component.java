package com.example.decoratormodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.decoratormodel
 * @className Component
 * @description
 * 抽象组件（Component）：可以是一个接口或者一个抽象类,充当被修饰类的原始对象,规定了被装饰对象的行为
 * 具体组件（ConcreteComponent）：就是被装饰对象，实现或者集成抽象组件的一盒对象
 * 抽象装饰器(Decorator):通用的装饰ConcreteComponent的装饰器，一般情况下是一个抽象类（如果装饰器只有一个的话可以直接省掉这个类）其内部必然有一个属性是指向Component抽象组件的
 * 具体装饰器（ConcreteDecorator）:Decorator的具体实现类，理论上每个ConcreteDecorator都扩展了Conponent一种功能（当然也可以不扩展，如果不扩展不就是写了一个没用的类吗）
 *
 * @date 2023/2/9 11:04
 */
public interface Component {
    /**
     * doSomething...
     */
    public void doSomething();
}
 