package com.example.bridgemodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.bridgemodel
 * @className Washer
 * @description
 * @date 2023/2/16 16:13
 */
public class Washer extends Product{
    Washer(ICompany company) {
        super(company);
    }

    @Override
    public void printProductInfo() {
        System.out.println(company.getName());
        System.out.println("公司--");
        System.out.println("洗衣机");
    }
}
 