package ru.example.appvkr.Activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import ru.example.appvkr.Adapter.TripListAdapter
import ru.example.appvkr.Domain.Trips
import ru.example.appvkr.databinding.ActivityListTripsBinding

class ListTripsActivity : BaseActivity() {
    private lateinit var binding: ActivityListTripsBinding
    private lateinit var adapterListTrip: RecyclerView.Adapter<*>
    private var categoryId: Int = 0
    private var categoryName: String? = null
    private var searchText: String? = null
    private var peopleText: String? = null
    private var dateText: String? = null
    private var priceText: String? = null
    private var isSearch: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListTripsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentExtra()
        initList()

    }
    private fun getIntentExtra() {
        categoryId = intent.getIntExtra("CategoryId", 0)
        categoryName = intent.getStringExtra("CategoryName")
        searchText = intent.getStringExtra("text")
        peopleText = intent.getStringExtra("peopleText")
        dateText = intent.getStringExtra("dateText")
        priceText = intent.getStringExtra("priceText")
        isSearch = intent.getBooleanExtra("isSearch", false)

        binding.titleTxt.text = categoryName
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun initList() {
        val myRef = database.getReference("Trips")
        binding.progressBar.visibility = View.VISIBLE
        val list = ArrayList<Trips>()
        val query: ChildEventListener = if (isSearch) {
            val peopleTextNumeric = peopleText?.replace("[^0-9]".toRegex(), "") ?: ""
            val peopleTextValue = peopleTextNumeric.toDoubleOrNull() ?: 0.0
            val priceTextValue = priceText?.toDoubleOrNull() ?: 0.0

            myRef.orderByChild("Title")
                .startAt(searchText ?: "")
                .endAt("${searchText ?: ""}\uf8ff")
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        val trip = snapshot.getValue(Trips::class.java)
                        if (trip != null && trip.PeopleId == peopleText && trip.Month == dateText && trip.Price!! <= priceTextValue) {
                            list.add(trip)
                            adapterListTrip?.notifyDataSetChanged()
                        }
                    }

                    override fun onChildChanged(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {}
                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                    override fun onCancelled(error: DatabaseError) {}
                })
        } else {
            myRef.orderByChild("CategoryId")
                .equalTo(categoryId.toDouble())
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        val trip = snapshot.getValue(Trips::class.java)
                        if (trip != null) {
                            list.add(trip)
                            adapterListTrip?.notifyDataSetChanged()
                        }
                    }

                    override fun onChildChanged(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {}
                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                    override fun onCancelled(error: DatabaseError) {}
                })
        }
        adapterListTrip = TripListAdapter(list)
        binding.tripListView.layoutManager = GridLayoutManager(this@ListTripsActivity, 2)
        binding.tripListView.adapter = adapterListTrip
        binding.progressBar.visibility = View.GONE

    }
}