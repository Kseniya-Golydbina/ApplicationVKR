package ru.example.appvkr.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import ru.example.appvkr.Adapter.BestTripsAdapter
import ru.example.appvkr.Adapter.CategoryAdapter
import ru.example.appvkr.Domain.Category
import ru.example.appvkr.Domain.Date
import ru.example.appvkr.Domain.People
import ru.example.appvkr.Domain.Price
import ru.example.appvkr.Domain.Trips
import ru.example.appvkr.R
import ru.example.appvkr.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("TAG", "Главный экран открыт")

        initBestTrips()
        initCategory()
        initPeople()
        initDate()
        initPrice()
        setVariable()
    }
    private fun setVariable(){
        binding.searchButton.setOnClickListener{
            val text = binding.editTextText.text.toString()
            val peopleText = binding.peopleSpinner.selectedItem.toString()
            val dateText = binding.dateSpinner.selectedItem.toString()
            val priceText = binding.priceSpinner.selectedItem.toString()

            if(text.isNotEmpty()){
                Log.d("TAG", "Строка поиска не пустая")
                val intent = Intent(this, ListTripsActivity::class.java)
                intent.putExtra("text", text)
                intent.putExtra("peopleText", peopleText)
                intent.putExtra("dateText", dateText)
                intent.putExtra("priceText", priceText)
                Log.d("TAG", "Был получен запрос $text")
                intent.putExtra("isSearch", true)

                startActivity(intent)
                Log.d("TAG", "Данный запрос вида отдыха найден")
            }
        }
        binding.basketButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
            Log.d("TAG", "Пользователь нажал на кнопку корзины")
        }
    }

    private fun initBestTrips() { //метод отображения лучших поездок
        val myRef = database.reference.child("Trips")
        binding.progressBarBestTrip.visibility = View.VISIBLE
        val list = ArrayList<Trips>()
        val query = myRef.orderByChild("BestTrips").equalTo(true)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val count = snapshot.childrenCount
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(Trips::class.java)!!)
                    }
                    if (list.isNotEmpty()) {
                        binding.bestTripView.layoutManager = LinearLayoutManager(
                            this@MainActivity, LinearLayoutManager.HORIZONTAL, false
                        )
                        val adapter = BestTripsAdapter(this@MainActivity, list)
                        binding.bestTripView.adapter = adapter
                    }
                    binding.progressBarBestTrip.visibility = View.GONE
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun initCategory() { //метод отображения категорий
        val myRef = database.reference.child("Category")
        binding.progressBarCategory.visibility = View.VISIBLE
        val list = ArrayList<Category>()
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(Category::class.java)!!)
                    }
                    if (list.isNotEmpty()) {
                        binding.categoryView.layoutManager = GridLayoutManager(this@MainActivity, 3)
                        val adapter = CategoryAdapter(this@MainActivity, list)
                        binding.categoryView.adapter = adapter
                    }
                    binding.progressBarCategory.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun initPeople() {
        val myRef = database.reference.child("People")
        val list = ArrayList<People>()
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(People::class.java)!!)
                    }
                    val adapter = ArrayAdapter(this@MainActivity, R.layout.sp_item, list)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.peopleSpinner.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    private fun initDate(){
        val myRef = database.reference.child("Date")
        val list = ArrayList<Date>()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(issue in snapshot.children){
                        list.add(issue.getValue(Date::class.java)!!)
                    }
                    val adapter = ArrayAdapter(this@MainActivity, R.layout.sp_item, list)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.dateSpinner.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    private fun initPrice(){
        val myRef = database.reference.child("Price")
        val list = ArrayList<Price>()
        myRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(issue in snapshot.children){
                        list.add(issue.getValue(Price::class.java)!!)
                    }
                    val adapter = ArrayAdapter(this@MainActivity, R.layout.sp_item, list)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.priceSpinner.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}