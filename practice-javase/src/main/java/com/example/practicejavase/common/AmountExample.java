package com.example.practicejavase.common;

import javax.measure.Measure;
import javax.measure.converter.UnitConverter;
import javax.measure.quantity.Length;
import javax.measure.quantity.Quantity;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.UnitFormat;
import java.text.ParseException;

public class AmountExample {

    public static void main(String[] args) throws ParseException {
        Measure<Double, Length> length1 = Measure.valueOf(5.0, SI.METRE);
        Measure<Double, Velocity> length2 = Measure.valueOf(10.01, SI.METERS_PER_SECOND);
        System.out.println("Length1: " + length1.getValue() + " " + length1.getUnit());
        System.out.println("Length2: " + length2.getValue() + " " + length2.getUnit());


        // 单位转换
        Measure<Double, Length> length3 = Measure.valueOf(5.0, SI.METRE);
        UnitConverter converterTo = SI.METRE.getConverterTo(NonSI.FOOT);// 米 -> 英尺
        double convert = converterTo.convert(5.0);
        System.out.println(convert);
        Measure<Double, Length> to = length3.to(NonSI.FOOT);
        System.out.println("Length3TO: " + to.getValue() + " " + to.getUnit());

        // 字符串->物理量对象
        String lengthStr = "10 m";
        UnitFormat instance = UnitFormat.getInstance();

        /*length4.
        System.out.println("length4: " + length4.ge() + " " + length4.getUnit());*/

    }
}
