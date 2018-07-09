package com.jumi.shareproject

/**
 * Created by abe on 2018/6/20.
 * 创建数据类
 */
data class Customer(val name: String = "", val email: String = "") {
    fun foo(a : Int = 0, b : String = ""){

    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}