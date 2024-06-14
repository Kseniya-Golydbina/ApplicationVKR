package ru.example.appvkr.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.example.appvkr.Adapter.CartAdapter
import ru.example.appvkr.Adapter.CartHotelAdapter
import ru.example.appvkr.Adapter.CartPathAdapter
import ru.example.appvkr.Helper.ManagmentCart
import ru.example.appvkr.Helper.ManagmentCartHotel
import ru.example.appvkr.Helper.ManagmentCartPath
import ru.example.appvkr.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var managmentCart: ManagmentCart
    private lateinit var managmentCartHotel: ManagmentCartHotel
    private lateinit var managmentCartPath: ManagmentCartPath
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("TAG", "Экран корзины пользователя открыт")

        managmentCart = ManagmentCart(this)
        managmentCartHotel = ManagmentCartHotel(this)
        managmentCartPath = ManagmentCartPath(this)

        setVariable()
        calculateCartTrips()
        calculateCartHotel()
        calculateCartPath()
        initListTrips()
        initListHotel()
        initListPath()
    }

    private fun initListTrips() {
        if (managmentCart.getListCart().isEmpty()) {
            binding.emptyTxt.visibility = View.VISIBLE
            binding.scrollViewCart.visibility = View.GONE
        } else {
            binding.emptyTxt.visibility = View.GONE
            binding.scrollViewCart.visibility = View.VISIBLE
        }

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cartView.layoutManager = linearLayoutManager
        adapter = CartAdapter(managmentCart.getListCart(), this) { calculateCartTrips() }
        binding.cartView.adapter = adapter
    }
    private fun initListHotel(){
        if(managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.visibility = View.VISIBLE
            binding.scrollViewCart.visibility = View.GONE
        }else{
            binding.emptyTxt.visibility = View.GONE
            binding.scrollViewCart.visibility = View.VISIBLE
        }
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cartView.layoutManager = linearLayoutManager
        adapter = CartHotelAdapter(managmentCartHotel.getListCart(), this) { calculateCartHotel() }
        binding.cartView.adapter = adapter
    }
    private fun initListPath(){
        if(managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.visibility = View.VISIBLE
            binding.scrollViewCart.visibility = View.GONE
        }else{
            binding.emptyTxt.visibility = View.GONE
            binding.scrollViewCart.visibility = View.VISIBLE
        }
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cartView.layoutManager = linearLayoutManager
        adapter = CartPathAdapter(managmentCartPath.getListCart(), this) { calculateCartPath() }
        binding.cartView.adapter = adapter
    }

    private fun calculateCartTrips() {
        val itemTotal = managmentCart.getTotalFee()
        binding.totalTxt.text = "%.3f₽".format(itemTotal)
    }

    private fun calculateCartHotel() {
        val itemTotal = managmentCartHotel.getTotalFee()
        binding.totalTxt.text = "%.3f₽".format(itemTotal)
    }

    private fun calculateCartPath(){
        val itemTotal = managmentCartPath.getTotalFee()
        binding.totalTxt.text = "%.3f₽".format(itemTotal)
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener{finish()}
    }
}