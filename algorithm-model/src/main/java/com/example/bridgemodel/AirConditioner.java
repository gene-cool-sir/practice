package com.example.bridgemodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.bridgemodel
 * @className AirConditioner
 * @description
 * @date 2023/2/16 16:12
 */
public class AirConditioner extends Product{
    AirConditioner(ICompany company) {
        super(company);
    }

    @Override
    public void printProductInfo() {
        System.out.println(company.getName());
        System.out.println("公司一");
        System.out.println("空调");
    }
}
 