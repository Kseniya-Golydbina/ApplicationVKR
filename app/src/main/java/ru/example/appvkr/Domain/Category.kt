package ru.example.appvkr.Domain

data class Category(
    var Id: Int? = null,
    var ImagePath: String? = null,
    var Name: String? = null
)
{
    override fun toString(): String {
        return Name ?: ""
    }
}