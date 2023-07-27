package com.example.compositemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.compositemodel
 * @className Department
 * @description
 * @date 2023/2/16 14:30
 */
public class Department extends DepartmentComponent{
    private Integer level;

    private List<DepartmentComponent> dept = new ArrayList<>();
    public Department(String name, Integer level) {
        super(name);
        this.level = level;
    }

    @Override
    void addChild(DepartmentComponent component) {
        dept.add(component);
    }

    @Override
    void removeChild(DepartmentComponent component) {
        dept.remove(component);
    }

    @Override
    void print() {
        System.out.println("部门-" + super.getName());
        for (DepartmentComponent component : dept) {
            if (this.level != null) {
                for (int i = 0; i < level; i++) {
                    System.out.print("--");
                }
            }
            component.print();
        }
    }
}
 