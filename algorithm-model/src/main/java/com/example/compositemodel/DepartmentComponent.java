package com.example.compositemodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.compositemodel
 * @className DepartmentComponent
 * @description
 * 组合模式(Composite Pattern ） 也称为整体-部分(Part-Whole）模式，
 * 它的宗旨是通过将单个 对象（叶子节点）和组合对象（树枝节点）用相同的接口进行表示，
 * 使得客户对单个对象和组合对象的 使用具有一致性，属于结构型模式。
 * @date 2023/2/16 14:19
 */
public abstract class DepartmentComponent {
    private final String name;


    public DepartmentComponent(String name) {
        this.name = name;
    }

    String getName() {
       return this.name;
    }

    void print() {
        throw new UnsupportedOperationException("不支持操作");
    }

    void addChild(DepartmentComponent component) {
        throw new UnsupportedOperationException("不支持操作");
    }

    void removeChild(DepartmentComponent component) {
        throw new UnsupportedOperationException("no impl method");
    }





}
