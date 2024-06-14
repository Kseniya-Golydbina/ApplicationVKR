package ru.example.appvkr.Activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import ru.example.appvkr.Domain.Hotel
import ru.example.appvkr.Helper.ManagmentCartHotel
import ru.example.appvkr.databinding.ActivityDetailHotelBinding

class DetailHotelActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailHotelBinding
    private lateinit var databaseReference: DatabaseReference

    private var hotelObject: Hotel? = null
    private var num = 1

    private lateinit var managmentCartHotel: ManagmentCartHotel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentExtra()
        setVariable()
    }

    private fun setVariable(){
        managmentCartHotel = ManagmentCartHotel(this)
        binding.back.setOnClickListener{finish()}

        Glide.with(this@DetailHotelActivity)
            .load(hotelObject?.ImagePath)
            .into(binding.image)

        binding.price.text = "%.3f₽".format(hotelObject?.Price)
        binding.title.text = hotelObject?.Name
        binding.mText.text = hotelObject?.Distance.toString() + "м. до склона"
        binding.star.text = "${hotelObject?.Star}"
        binding.ratingBar.rating = hotelObject?.Star?.toFloat()?: 0f
        binding.dateTxt.text = hotelObject?.DateValue
        binding.placeText.text = hotelObject?.Place
        binding.service1.text = hotelObject?.Service1
        binding.service2.text = hotelObject?.Service2
        binding.service3.text = hotelObject?.Service3
        binding.service4.text = hotelObject?.Service4
        binding.service5.text = hotelObject?.Service5
        binding.totalTxt.text = "%.3f₽".format(num * (hotelObject?.Price ?: 0.0))

        binding.addBtn.setOnClickListener{
            hotelObject?.numberInCart = num
            managmentCartHotel.insertHotel(hotelObject!!)
        }
    }
    private fun getIntentExtra(){
        hotelObject = intent.getSerializableExtra("object") as? Hotel
    }
}