package com.jumi.shareproject

import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        createItems()
        exeIntItems()
    }

    fun exeIntItems() {
        val items = mapOf(1 to "hello 1", 2 to "hello 2", 4 to "hello 3", 5 to "hello 4")
        val a = items[1];
        println(a)
        for ((k, v) in items) {
            println("$k -> $v")
        }

        //懒属性
        val p: String by lazy {
            "fd"
        }

        /**
         * 扩展函数
         */
        fun String.spaceToCamlCase() {

        }
        "hello".spaceToCamlCase();

        val data = ""
        val isEmpty = "is Empty"
        val isNotEmpty = "is no empty"
        //let 执行该操作
        println(data?.let { println("is no empty") } ?: "empty")

        val res = if (data == "a") {
            "one"
        } else if (data == "b") {
            "tow"
        } else {

        }
    }

    //创建单例模式
    object Resource {
        val name = "name";
    }

    /**
     * 问号代表可以为空
     */
    fun createItems(): Array<String>? {
        val items = listOf("apple", "banana", "kiwi", "apple", "orange")

        when {
            "apple" in items -> println("juicy")
            2 in 1..3 -> println("apple is fine too")
        }

        items.filter { it.startsWith("a") }//过滤
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { println(it) }
        return null
    }

    /**
     * while循环遍历
     */
    fun traverse(items: Array<String>) {
        val index = 0;
        while (index < items.size) {
            println("item at ${items[index]}");
        }
    }
}