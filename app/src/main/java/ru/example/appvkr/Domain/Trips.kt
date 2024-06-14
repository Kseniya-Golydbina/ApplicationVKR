package ru.example.appvkr.Domain

import java.io.Serializable

data class Trips(
    var BestFood: Boolean? = null,
    var CategoryId: Int? = null,
    var Id: Int? = null,
    var ImagePath: String? = null,
    var Description: String? = null,
    var PeopleId: String? = null,
    var Price: Double? = null,
    var DateId: Int? = null,
    var Month: String? = null,
    var DateValue: String? = null,
    var Star: Double? = null,
    var PriceId: Int? = null,
    var PriceValue: Double? = null,
    var TownId: Int? = null,
    var Name: String? = null,
    var Title: String? = null,
    var numberInCart: Int? = null
): Serializable{
    override fun toString(): String {
        return Title ?: ""
        return Month ?: ""
        return Name ?: ""
        return Description ?: ""
        return DateValue ?: ""
    }
}