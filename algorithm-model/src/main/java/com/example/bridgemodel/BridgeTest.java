package com.example.bridgemodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.bridgemodel
 * @className BridgeTest
 * @description
 * @date 2023/2/16 16:15
 */
public class BridgeTest {

    public static void main(String[] args) {
        Product washer = new Washer(new Haier());
        Product meidi = new AirConditioner(new Meidi());
        washer.printProductInfo();
        meidi.printProductInfo();
    }
}
 