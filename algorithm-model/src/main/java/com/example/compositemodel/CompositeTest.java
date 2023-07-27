package com.example.compositemodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.compositemodel
 * @className CompositeTest
 * @description
 * @date 2023/2/16 14:39
 */
public class CompositeTest {

    public static void main(String[] args) {
        Department department1 = new Department("部门1", 1);
        Department department2 = new Department("部门2", 2);
        Department department3 = new Department("部门3", 3);

        Staff staff1 = new Staff("gene1");
        Staff staff2 = new Staff("gene2");
        Staff staff3 = new Staff("gene3");
        Staff staff4 = new Staff("gene4");
        Staff staff5 = new Staff("gene5");
        Staff staff6 = new Staff("gene6");
        Staff staff7 = new Staff("gene7");
        Staff staff8 = new Staff("gene8");

        department1.addChild(department2);
        department1.addChild(department3);
        department1.addChild(staff1);
        department2.addChild(staff2);
        department2.addChild(staff3);
        department2.addChild(staff4);

        department3.addChild(staff5);
        department3.addChild(staff6);
        department3.addChild(staff7);
        department3.addChild(staff8);

        department1.print();
    }
}
 