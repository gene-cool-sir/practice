package com.example.decoratormodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.decoratormodel
 * @className Client
 * @description
 * 通过上文的描述我们可以大概总结出装饰器模式的适用场景
 *
 * 用于扩展一个类的功能或者给一个类添加附加职责
 * 动态的给一个对象添加功能，这些功能可以再动态的撤销
 * 需要为一批兄弟类进行改装或者加装功能
 *
 *
 * 门面模式、静态代理模式、装饰器模式了，下面总结一下三者的区别
 *
 * 装饰器模式和门面模式一样都是特殊的静态代理模式（仅此点一样）
 * 装饰器模式强调的代码的变化、门面模式强调的代码的封装，代理模式则是代码的增强
 * 一般装饰器模式源对象是需要通过构造方法传入的，门面模式则是在内部初始化子系统的对象主要是对子系统的封装
 * 静态代理模式和装饰器模式的差别就没有那么大主要是理念上的不同，比如使用装饰器层层装饰，如果改用静态代理层层代理也挺奇怪
 *
 * @date 2023/2/9 11:13
 */
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Decorator decoratorA = new DecoratorA(component);
        Decorator decoratorB = new DecoratorB(decoratorA);
        System.out.println("装饰器A对真实组件做了事情: ");
        decoratorA.doSomething();
        System.out.println("========================================================");
        System.out.println("装饰器做了一系列的装饰事件: ");
        decoratorB.doSomething();
    }
}
 