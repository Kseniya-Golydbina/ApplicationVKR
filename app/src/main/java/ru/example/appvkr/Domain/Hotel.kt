package ru.example.appvkr.Domain

import java.io.Serializable

data class Hotel(
    var Id: Int? = null,
    var Name: String? = null,
    var TownId: Int? = null,
    var Price: Double? = null,
    var ImagePath: String? = null,
    var Star: Double? = null,
    var DateId: Int? = null,
    var DateValue: String? = null,
    var CategoryId: Int? = null,
    var Distance: Int? = null,
    var Title: String? = null,
    var Place: String? = null,
    var Service1: String? = null,
    var Service2: String? = null,
    var Service3: String? = null,
    var Service4: String? = null,
    var Service5: String? = null,
    var numberInCart: Int? = null
): Serializable {
    override fun toString(): String {
        return Name ?: ""
        return DateValue ?: ""
        return Title ?: ""
        return Place ?: ""
        return Service1 ?: ""
        return Service2 ?: ""
        return Service3 ?: ""
        return Service4 ?: ""
        return Service5 ?: ""
    }
}
