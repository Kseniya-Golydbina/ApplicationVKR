package ru.example.appvkr.Helper

import android.content.Context
import android.os.Environment
import android.preference.PreferenceManager
import android.text.TextUtils
import ru.example.appvkr.Domain.Trips
import java.util.Arrays
import com.google.gson.Gson
import ru.example.appvkr.Domain.Hotel
import ru.example.appvkr.Domain.Path
import java.lang.NullPointerException

class TinyDB(private val appContext: Context) {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(appContext)
    private var defaultAppImageDataDirectory: String? = null

    fun getListString(key: String): ArrayList<String> {
        return ArrayList(Arrays.asList(*TextUtils.split(preferences.getString(key, ""), "‚‗‚")))
    }
    fun getListObjectTrips(key: String): ArrayList<Trips>{
        val gson = Gson()
        val objStrings = getListString(key)
        val playerList = ArrayList<Trips>()

        for(jObjString in objStrings){
            val player: Trips = gson.fromJson(jObjString, Trips::class.java)
            playerList.add(player)
        }
        return playerList
    }
    fun getListObjectHotel(key: String): ArrayList<Hotel>{
        val gson = Gson()
        val objStrings = getListString(key)
        val playerList = ArrayList<Hotel>()

        for(jObjString in objStrings){
            val player: Hotel = gson.fromJson(jObjString, Hotel::class.java)
            playerList.add(player)
        }
        return playerList
    }
    fun getListObjectPath(key: String): ArrayList<Path>{
        val gson = Gson()
        val objStrings = getListString(key)
        val playerList = ArrayList<Path>()

        for(jObjString in objStrings){
            val player: Path = gson.fromJson(jObjString, Path::class.java)
            playerList.add(player)
        }
        return playerList
    }
    fun putListString(key: String, stringList: ArrayList<String>) {
        checkForNullKey(key)
        val myStringList = stringList.toTypedArray()
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply()
    }
    fun putListObjectTrips(key: String, playerList: MutableList<Trips>) {
        checkForNullKey(key)
        val gson = Gson()
        val objStrings = playerList.map { gson.toJson(it) }
        putListString(key, ArrayList(objStrings))
    }
    fun putListObjectHotel(key: String, playerList: MutableList<Hotel>) {
        checkForNullKey(key)
        val gson = Gson()
        val objStrings = playerList.map { gson.toJson(it) }
        putListString(key, ArrayList(objStrings))
    }
    fun putListObjectPath(key: String, playerList: MutableList<Path>) {
        checkForNullKey(key)
        val gson = Gson()
        val objStrings = playerList.map { gson.toJson(it) }
        putListString(key, ArrayList(objStrings))
    }
    companion object{
        private fun isExternalStorageReadable(): Boolean{
            return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        }
        private fun isExternalStorageWritable(): Boolean{
            return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        }
    }
    private fun checkForNullKey(key: String?) {
        key ?: throw NullPointerException("Key is null")
    }

    private fun checkForNullValue(value: String?) {
        value ?: throw NullPointerException("Value is null")
    }
}