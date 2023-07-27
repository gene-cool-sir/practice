package com.example.bridgemodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.bridgemodel
 * @className Product
 * @description
 * 桥接模式主要目的是通过组合的方式建立两个类之间的联系，而不是继承。但又类似于多重继承方
 * 案，但是多重继承方案往往违背了类得单一职责原则，其复用性比较差，桥接模式是比多重继承更好的
 * 替代方案。桥接模式的核心在于解耦抽象和实现。
 * 注：此处的抽象并不是指抽象类象接口这种商层概念，实现也不是继承或接口实现。抽象与实现其实指的是两种独立
 * 变化的维度。
 *
 * eg: 家用电器，有各种家用电器比如洗衣机、冰箱、空调，所有的家用电器又对应这自己的品牌，
 *    这就分离出来了两个维度一个是家用电器，还一个是品牌两个都能扩展，这里就很适合使用桥接模式
 *    具体代码如下： 首先把家用电器抽象出来
 * @date 2023/2/16 16:04
 */
public abstract class Product {
    protected ICompany company;

    Product(ICompany company) {
        this.company = company;
    }

    public abstract void printProductInfo();
}
 