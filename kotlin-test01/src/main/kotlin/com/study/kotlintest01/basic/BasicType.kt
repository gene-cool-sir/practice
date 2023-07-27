package com.study.kotlintest01.basic

class BasicType {
    fun printType(params: Any) {
        println("$params is ${params::class.simpleName}  type")
    }
}

fun main() {
    val num = 2;
    val num1 = 0.2
    val num2 = 0.2f

    var basicType = BasicType();
    basicType.printType(2)
    basicType.printType(num1)
    basicType.printType(num2)

    val a: Int = 10000
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    print(boxedA == anotherBoxedA)
    print(boxedA === anotherBoxedA)

}
