package ru.example.appvkr.Activity

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import ru.example.appvkr.Domain.Trips
import ru.example.appvkr.Helper.ManagmentCart
import ru.example.appvkr.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var databaseReference: DatabaseReference

    private var tripObject: Trips? = null
    private var num = 1

    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentExtra()
        setVariable()
    }

    private fun setVariable() {
        managmentCart = ManagmentCart(this)
        binding.back.setOnClickListener{
            finish()
        }
        Glide.with(this@DetailActivity)
            .load(tripObject?.ImagePath)
            .into(binding.image)

        binding.price.text = "%.3f₽".format(tripObject?.Price)
        binding.title.text = tripObject?.Title
        binding.town.text = tripObject?.Name
        binding.description.text = tripObject?.Description
        binding.date.text = tripObject?.DateValue
        binding.star.text = "${tripObject?.Star} Rating"
        binding.ratingBar.rating = tripObject?.Star?.toFloat()?: 0f
        binding.totalTxt.text = "%.3f₽".format(num * (tripObject?.Price ?: 0.0))

        /*binding.plus.setOnClickListener{
            num +=1
            binding.num.text = "$num "
            binding.totalTxt.text = "%.3f₽".format(num * tripObject?.Price!!)
        }
        binding.minus.setOnClickListener{
            if(num>1){
                num -= 1
                binding.num.text = "$num "
                binding.totalTxt.text = "%.3f₽".format(num * tripObject?.Price!!)
            }
        }*/
        binding.addBtn.setOnClickListener{
            tripObject?.numberInCart = num
            managmentCart.insertTrip(tripObject!!)
        }
        binding.hotelButton.setOnClickListener{
            val townText = binding.town.text.toString()
            val intent = Intent(this, ListHotelActivity::class.java)
            intent.putExtra("Title", townText)
            startActivity(intent)
        }
        binding.carButton.setOnClickListener{
            val townText = binding.town.text.toString()
            val intent = Intent(this, ListPathActivity::class.java)
            intent.putExtra("Title", townText)
            startActivity(intent)
        }
    }

    private fun getIntentExtra() {
        tripObject = intent.getSerializableExtra("object") as? Trips
    }
}