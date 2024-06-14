package ru.example.appvkr.Activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import ru.example.appvkr.Domain.Path
import ru.example.appvkr.Helper.ManagmentCartPath
import ru.example.appvkr.databinding.ActivityDetailPathBinding

class DetailPathActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailPathBinding
    private lateinit var databaseReference: DatabaseReference

    private var pathObject: Path? = null
    private var num = 1

    private lateinit var managmentCartPath: ManagmentCartPath

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPathBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentExtra()
        setVariable()
    }
    private fun setVariable(){
        managmentCartPath = ManagmentCartPath(this)
        binding.back.setOnClickListener{finish()}

        Glide.with(this@DetailPathActivity)
            .load(pathObject?.ImagePath)
            .into(binding.image)

        binding.price.text = "%.3f₽".format(pathObject?.Price)
        binding.title1.text = pathObject?.NameWhere
        binding.title2.text = pathObject?.NameFrom
        binding.raceText.text = pathObject?.Name
        binding.timeText.text = pathObject?.Time
        binding.dateTxt.text = pathObject?.Time
        binding.placeText.text = pathObject?.Tarif
        binding.service1.text = pathObject?.Service1
        binding.service2.text = pathObject?.Service2
        binding.service3.text = pathObject?.Service3
        binding.service4.text = pathObject?.Service4
        binding.totalTxt.text = "%.3f₽".format(num * (pathObject?.Price ?: 0.0))

        binding.addBtn.setOnClickListener{
            pathObject?.numberInCart = num
            managmentCartPath.insertPath(pathObject!!)
        }
    }
    private fun getIntentExtra(){
        pathObject = intent.getSerializableExtra("object") as? Path
    }
}