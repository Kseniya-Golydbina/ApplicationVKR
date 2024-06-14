package ru.example.appvkr.Domain

import java.io.Serializable

data class Path(
    var Id: Int? = null,
    var Place: String? = null,
    var NameWhere: String? = null,
    var NameFrom: String? = null,
    var Name: String? = null,
    var Tarif: String? = null,
    var Service1: String? = null,
    var Service2: String? = null,
    var Service3: String? = null,
    var Service4: String? = null,
    var Company: String? = null,
    var Price: Double? = null,
    var ImagePath: String? = null,
    var TownId: Int? = null,
    var DateValue: String? = null,
    var CategoryId: Int? = null,
    var Time: String? = null,
    var Title: String? = null,
    var numberInCart: Int? = null
): Serializable {
    override fun toString(): String {
        return Place ?: ""
        return NameWhere ?: ""
        return NameFrom ?: ""
        return Name ?: ""
        return Tarif ?: ""
        return Service1 ?: ""
        return Service2 ?: ""
        return Service3 ?: ""
        return Service4 ?: ""
        return Company ?: ""
        return DateValue ?: ""
        return Title ?: ""
        return Time ?: ""
    }
}
