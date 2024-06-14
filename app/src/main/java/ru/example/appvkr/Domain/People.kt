package ru.example.appvkr.Domain

data class People(
    var Id: Int? = null,
    var count: String? = null
){
    override fun toString(): String {
        return count ?: ""
    }
}