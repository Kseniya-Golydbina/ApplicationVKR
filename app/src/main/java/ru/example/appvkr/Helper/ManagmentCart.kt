package ru.example.appvkr.Helper

import android.content.Context
import android.widget.Toast
import ru.example.appvkr.Domain.Trips

class ManagmentCart (private val context: Context){
    private val tinyDB: TinyDB = TinyDB(context)

    fun insertTrip(item: Trips){
        val listPop = getListCart().toMutableList()
        var existsAlready = false
        var n = 0
        for(i in listPop.indices){
            if(listPop[i].Title == item.Title){
                existsAlready = true
                n = i
                break
            }
        }
        if(existsAlready){
            listPop[n].numberInCart = item.numberInCart
        }else{
            listPop.add(item)
        }
        tinyDB.putListObjectTrips("CartList", listPop)
        Toast.makeText(context, "Отдых добавлен в ваш список", Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): ArrayList<Trips>{
        return tinyDB.getListObjectTrips("CartList") ?: arrayListOf()
    }
    fun getTotalFee(): Double{
        val listItem = getListCart()
        var fee = 0.0
        for(item in listItem){
            fee += item.Price!! * item.numberInCart!!
        }
        return fee
    }

    fun minusNumberItem(listItem: ArrayList<Trips>, position: Int, updateCart: () -> Unit){
        if (listItem[position].numberInCart == 1){
            listItem.removeAt(position)
        }else{
            listItem[position].numberInCart = listItem[position].numberInCart!! - 1
        }
        tinyDB.putListObjectTrips("CartList", listItem)
        updateCart()

    }
    fun plusNumberItem(listItem: ArrayList<Trips>, position: Int, updateCart: () -> Unit) {
        listItem[position].numberInCart = listItem[position].numberInCart!! + 1
        tinyDB.putListObjectTrips("CartList", listItem)
        updateCart()
    }
}