package ru.example.appvkr.Helper

import android.content.Context
import android.widget.Toast
import ru.example.appvkr.Domain.Path

class ManagmentCartPath (private val context: Context) {
    private val tinyDB: TinyDB = TinyDB(context)

    fun insertPath(item: Path){
        val listPop = getListCart().toMutableList()
        var existsAlready = false
        var n = 0
        for(i in listPop.indices){
            if(listPop[i].Name == item.Name){
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
        tinyDB.putListObjectPath("CartList", listPop)
        Toast.makeText(context, "Отель добавлен в ваш список", Toast.LENGTH_SHORT).show()
    }
    fun getListCart(): ArrayList<Path>{
        return tinyDB.getListObjectPath("CartList") ?: arrayListOf()
    }
    fun getTotalFee(): Double{
        val listItem = getListCart()
        var fee = 0.0
        for(item in listItem){
            fee += item.Price!! * item.numberInCart!!
        }
        return fee
    }
    fun minusNumberItem(listItem: ArrayList<Path>, position: Int, updateCart: () -> Unit){
        if (listItem[position].numberInCart == 1){
            listItem.removeAt(position)
        }else{
            listItem[position].numberInCart = listItem[position].numberInCart!! - 1
        }
        tinyDB.putListObjectPath("CartList", listItem)
        updateCart()

    }
    fun plusNumberItem(listItem: ArrayList<Path>, position: Int, updateCart: () -> Unit) {
        listItem[position].numberInCart = listItem[position].numberInCart!! + 1
        tinyDB.putListObjectPath("CartList", listItem)
        updateCart()
    }
}