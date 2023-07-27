package com.study.kotlintest01.basic

/**
 * Kotlin 标准库提供了一整套用于管理集合的工具，集合是可变数量（可能为零）的一组条目，各种集合对于解决
问题都具有重要意义，并且经常用到。
List 是一个有序集合，可通过索引访问元素。元素可以在 list 中出现多次。List 列表的顺序很重要并且元素
可以重复。
Set 是唯一元素的集合。一般来说 set 中元素的顺序并不重要。
Map 是一组键值对。键是唯一的，每个键都刚好映射到一个值，值可以重复。
 */
class BasicCollections {
    // 不可变集合
    fun immutableSet() {
        val stringList = listOf("one", "two", "one")
        println(stringList)
        val stringSet = setOf("one", "two", "three")
        println(stringSet)
    }

    // 可变集合
    fun mutableSet() {
        val numbers = mutableListOf(1, 2, 3, 4)
        numbers.add(5)
        numbers.removeAt(1)
        numbers[0] = 0
        println(numbers)
    }

    // 集合排序
    fun setSorting() {
        val numbers = mutableListOf(1, 2, 3, 4)
        //随机排列元素
        numbers.shuffle()
        println(numbers)
        numbers.sort()//排序，从小打到
        numbers.sortDescending()//从大到小
        println(numbers)
        //定义一个Language类，有name 和 score 两属性
        data class Language(var name: String, var score: Int)
        val languageList: MutableList<Language> = mutableListOf()
        languageList.add(Language("Java", 80))
        languageList.add(Language("Kotlin", 90))
        languageList.add(Language("Dart", 99))
        languageList.add(Language("C", 80))
//使用sortBy进行排序，适合单条件排序
        languageList.sortBy { it.score }
        println(languageList)
//使用sortWith进行排序，适合多条件排序
        languageList.sortWith(compareBy(
//it变量是lambda中的隐式参数
            { it.score }, { it.name })
        )
        println(languageList)
    }

    fun useSet() {
        /**set**/
        val hello = mutableSetOf("H", "e", "l", "l", "o")//自动过滤重复元素
        hello.remove("o")
//集合的加减操作
        hello += setOf("w", "o", "r", "l", "d")
        println(hello)
        /**Map<K, V> 不是 Collection 接口的继承者；但是它也是 Kotlin 的一种集合类型**/
        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
        println("All keys: ${numbersMap.keys}")
        println("All values: ${numbersMap.values}")
        if ("key2" in numbersMap) println("Value by key \"key2\": ${numbersMap["key2"]}")
        if ("key1" in numbersMap.keys) println("Value by key \"key1\": ${numbersMap["key1"]}")
        if (1 in numbersMap.values) println("1 is in the map")
        if (numbersMap.containsValue(1)) println(" 1 is in the map")
    }

    fun questionSet() {
        /**
         * Q1、两个具有相同键值对，但顺序不同的Map相等吗？为什么？
         *
         */
        val numberMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 4, "key5" to 5)
        val anotherMap = mapOf("key2" to 2, "key1" to 1, "key3" to 3, "key4" to 4, "key5" to 5)
        println("anotherMap == numberMap:${anotherMap == numberMap}")
        /**
         * Q2、两个具有相同元素，但顺序不同的list相等吗？为什么？
         *
         */
        val stringList1 = listOf<String>("one", "two", "three")
        val stringList2 = listOf<String>("three", "two", "one")
        println("stringList1 == stringList2:${stringList1 == stringList2}")
        //可以看到 Map 的 equals() 方法会去拿出一个 Map 中的一个 key 值，然后查看另外一个 Map 中是否有同样的
        //key 值，然后再去比较这个两个 key 值所对应的值是否相等就可以了，所以跟顺序是无关的。

        // 可以看到 List 的 equals() 方法会去比较每一个索引上对应的值是否相等，所以顺序不同的 list 是不相等的。


    }
}


fun main() {
    var basicCollections = BasicCollections()
    basicCollections.mutableSet()
    basicCollections.setSorting()
    basicCollections.useSet()
    basicCollections.questionSet()
}