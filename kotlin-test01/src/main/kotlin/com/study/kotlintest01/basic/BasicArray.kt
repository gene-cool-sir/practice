package com.study.kotlintest01.basic

class BasicArray {
    fun arrayTraversal() {
        for (item in arrayOf(1,2,3)) {
            println(item)
        }
        // 带索引遍历
        var listOf = listOf<Int>(4, 5, 6)
        for (i in listOf.indices) {
            println(i.toString() + "->" + listOf[i])
        }
        // 遍历元素（带索引）
        for ((index, item) in listOf.withIndex()) {
            println("$index->$item")
        }
        // forEach 遍历数组
        listOf.forEach { println(it) }
        //  forEach 增强版
        listOf.forEachIndexed { index, item ->
            println("$index：$item")
        }
        //
    }

    /**
     * 数组
     */
    fun arrayType() {
// arrayOf
        val array: Array<Int> = arrayOf(1, 2, 3)
// ArrayOfNulls
        val array1 = arrayOfNulls<Int>(3)
        array1[0] = 4
        array1[1] = 5
        array1[2] = 6
// 通过Array的构造函数
        val array2 = Array(5) { i -> (i * i).toString() }
// 原生类型数组
        val x = intArrayOf(1, 2, 3)
        println("x[0] + x[1] = ${x[0] + x[1]}")
// 大小为5、值为 [0, 0, 0, 0, 0] 的整型数组
        val array3: IntArray = IntArray(5)
// 例如：用常量初始化数组中的值
// 大小为5、值为 [42, 42, 42, 42, 42] 的整型数组
        val array4 = IntArray(5) { 42 }
// 例如：使用 lambda 表达式初始化数组中的值
// 大小为5、值为 [0, 1, 2, 3, 4] 的整型数组 （值初始化为其索引值）
        val array5 = IntArray(5) { it * 1 }
        println(array5[4])
        /***遍历数组的5种方式***/
// 数组遍历
        for (item in array) {
            println(item)
        }
// 带索引遍历数组
        for (i in array.indices) {
            println("$i -> ${array[i]}")
        }
// 带索引遍历数组2
        for ((index, item) in array.withIndex()) {
            println("$index -> $item")
        }
//forEach 遍历数组
        array.forEach { println(it) }
//forEach 增强版
        array.forEachIndexed { index, item ->
            println("$index -> $item")
        }
    }
}

fun main() {
    // Array<T>
    // arrayOf来创建一个数组并传递元素值给它
    var arrayOf = arrayOf(1, 2, 3)
    // 指定大小,所有元素都为空的数组
    var arrayOfNulls = arrayOfNulls<Any>(3)
    // 创建有初始值的数组
    Array(5){ i -> (i*i).toString()}
    // 原生类数组通过intArrayOf、floatArrayOf、doubleArrayOf等创建数组
    val x: IntArray = intArrayOf(1, 2, 3)
    println("x[1] + x[2] = ${x[1] + x[2]}")
    // 大小为 5、值为 [0, 0, 0, 0, 0] 的整型数组
    val arr = IntArray(5)
    // 例如：用常量初始化数组中的值
    // 大小为 5、值为 [42, 42, 42, 42, 42] 的整型数组
    val arrConstants = IntArray(5) { 42 }
    // 例如：使用 lambda 表达式初始化数组中的值
    // 大小为 5、值为 [0, 1, 2, 3, 4] 的整型数组（值初始化为其索引值）
    var arrLambda  = IntArray(5) { it * 1 }

    var basicArray = BasicArray()
    basicArray.arrayTraversal();
}