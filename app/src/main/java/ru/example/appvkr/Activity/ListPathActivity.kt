package ru.example.appvkr.Activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import ru.example.appvkr.Adapter.PathListAdapter
import ru.example.appvkr.Domain.Path
import ru.example.appvkr.databinding.ActivityListPathBinding

class ListPathActivity : BaseActivity() {
    private lateinit var binding: ActivityListPathBinding
    private lateinit var adapterListPath: RecyclerView.Adapter<*>
    private var townId: Int = 0
    private var townName: String? = null
    private var searchText: String? = null
    private var isSearch: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPathBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentExtra()
        val townName = intent.getStringExtra("Title")
        if(townName!=null){
            initList(townName)
        }
    }
    private fun getIntentExtra(){
        townId = intent.getIntExtra("TownId", 0)
        townName = intent.getStringExtra("TownValue")
        searchText = intent.getStringExtra("text")
        isSearch = intent.getBooleanExtra("isSearch", false)

        binding.titleTxt.text = townName
        binding.backBtn.setOnClickListener{finish()}
    }
    private fun initList(townName: String){
        val pathRef = database.getReference("Path")
        binding.progressBar.visibility = View.VISIBLE

        pathRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(pathSnapshot: DataSnapshot) {
                if(pathSnapshot.exists()){
                    val filteredPaths = ArrayList<Path>()
                    for(path in pathSnapshot.children){
                        val pathTitle = path.child("Title").value.toString()
                        if(pathTitle == townName){
                            filteredPaths.add(path.getValue(Path::class.java)!!)
                        }
                    }
                    if(filteredPaths.isNotEmpty()){
                        binding.tripListView.layoutManager = GridLayoutManager(this@ListPathActivity, 1)
                        adapterListPath = PathListAdapter(filteredPaths)
                        binding.tripListView.adapter = adapterListPath
                    }
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}