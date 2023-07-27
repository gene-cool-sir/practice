package com.example.compositemodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.compositemodel
 * @className Staff
 * @description
 * @date 2023/2/16 14:38
 */
public class Staff extends DepartmentComponent{
    public Staff(String name) {
        super(name);
    }

    @Override
    void print() {
        System.out.println("员工: " + super.getName());
    }
}
 