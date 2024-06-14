package ru.example.appvkr.Domain

data class Price(
    var Id: Int? = null,
    var Value: String? = null
){
    override fun toString(): String {
        return Value ?: ""
    }
}