package com.example.sun29.data.remote

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

/**
 * Animal 作用：
 * 可以把 Fuel 远程获取的 JSON 数据 也就是 Javabean 代码，反序列化成为数组。所以此文件需要添加GSON依赖
 */
data class Animal(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
){
    class Deserializer : ResponseDeserializable<Animal> {
        override fun deserialize(content: String): Animal? =
            Gson().fromJson(content, Animal::class.java)
    }
}