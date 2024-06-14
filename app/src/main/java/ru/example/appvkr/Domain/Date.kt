package ru.example.appvkr.Domain

data class Date(
    var Id: Int? = null,
    var Value: String? = null
){
    override fun toString(): String {
        return Value ?: ""
    }
}