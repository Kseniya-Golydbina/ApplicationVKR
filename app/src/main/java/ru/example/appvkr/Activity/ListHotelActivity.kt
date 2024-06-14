package ru.example.appvkr.Activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import ru.example.appvkr.Adapter.HotelListAdapter
import ru.example.appvkr.Domain.Hotel
import ru.example.appvkr.databinding.ActivityListHotelBinding

class ListHotelActivity : BaseActivity() {
    private lateinit var binding: ActivityListHotelBinding
    private lateinit var adapterListHotel: RecyclerView.Adapter<*>
    private var townId: Int = 0
    private var townName: String? = null
    private var searchText: String? = null
    private var isSearch: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentExtra()
        val townName = intent.getStringExtra("Title")
        if(townName != null){
            initList(townName)
        }
    }
    private fun getIntentExtra(){
        townId = intent.getIntExtra("TownId", 0)
        townName = intent.getStringExtra("Title")
        searchText = intent.getStringExtra("text")
        isSearch = intent.getBooleanExtra("isSearch", false)

        binding.titleTxt.text = townName
        binding.backBtn.setOnClickListener{finish()}
    }
    private fun initList(townName: String){
        val hotelRef = database.getReference("Hotel")
        binding.progressBar.visibility = View.VISIBLE

        hotelRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(hotelSnapshot: DataSnapshot) {
                if (hotelSnapshot.exists()) {
                    val filteredHotels = ArrayList<Hotel>()
                    for (hotel in hotelSnapshot.children) {
                        val hotelTitle = hotel.child("Title").value.toString()
                        if (hotelTitle == townName) {
                            filteredHotels.add(hotel.getValue(Hotel::class.java)!!)
                        }
                    }
                    if (filteredHotels.isNotEmpty()) {
                        binding.tripListView.layoutManager = GridLayoutManager(this@ListHotelActivity, 1)
                        adapterListHotel = HotelListAdapter(filteredHotels)
                        binding.tripListView.adapter = adapterListHotel
                    }
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}

