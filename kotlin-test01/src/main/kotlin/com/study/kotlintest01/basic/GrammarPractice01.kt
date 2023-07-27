package com.study.kotlintest01.basic

class GrammarPractice01 {
    fun doSomething() {
        println("do something...")
    }
    fun doSomething(vararg number: Int) {
        number.forEach { println(it) }
    }

}
fun main() {
    // 打印日志
    println("hello, function")
    // 定义变量
    var name = "hello function"
    // 常量
    val constants = "hello function"
    val score = 22;
    // 可为空定义
    var nullable  : String? = "123"
    println(nullable)
    var a = nullable?.let{
         it.length
    }
    println(nullable?.length)
    print(a)
    // 三元表达式
    if (name == constants) {
        println(message = true)
        println(message = true)
    } else 
        println(message = false)
    
    // 多重条件判断
    if (score in 0..30) println("multi if > ") else println("multi if <")
    
    // 灵活的case语句
    var symnol = when (score) {
        9, 10 -> "9 or 10"
        in 0..8 -> "<8"
        else -> ">"
    }
    println("when $symnol")
    // for 循环
    for (i in 1..score) {
        print("for i:$i")
    }

    // 集合
    val listOf = listOf<String>("1", "2", "3")
    val array = listOf<Int>(1, 2, 3)
    var mapOf = mapOf<Int, String>(1 to "one", 2 to "two", 3 to "three")
    // 遍历
    listOf.forEach { println(it) }
    mapOf.forEach { println("${it.key} -- ${it.value}") }

    var grammar01 = GrammarPractice01();
    grammar01.doSomething()
    grammar01.doSomething(1,2,3)

    println(score.toByte())

} 