package ru.example.appvkr.Domain

data class Town(
    var Id: Int? = null,
    var Name: String? = null
){
    override fun toString(): String {
        return Name ?: ""
    }
}