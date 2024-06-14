package ru.example.appvkr.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.example.appvkr.Activity.DetailHotelActivity
import ru.example.appvkr.Domain.Hotel
import ru.example.appvkr.R

class HotelListAdapter (private val items: ArrayList<Hotel>):
    RecyclerView.Adapter<HotelListAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        context = parent.context
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_hotel_cart, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTxt?.text = items[position].Name
        holder.mText?.text = items[position].Distance.toString() + "м. до склона"
        holder.dateTxt?.text = items[position].DateValue
        holder.priceTxt?.text = "%.3f₽/чел.".format(items[position].Price)
        holder.starTxt?.text = items[position].Star.toString()

        Glide.with(context).load(items[position].ImagePath)
            .transform(CenterCrop(), RoundedCorners(30))
            .into(holder.pic)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, DetailHotelActivity::class.java)
            intent.putExtra("object", items[position])
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameTxt: TextView = itemView.findViewById(R.id.nameHotelText)
        val mText: TextView = itemView.findViewById(R.id.mText)
        val starTxt: TextView = itemView.findViewById(R.id.starTxt)
        val dateTxt: TextView = itemView.findViewById(R.id.dateTxt)
        val priceTxt: TextView = itemView.findViewById(R.id.priceText)
        val pic: ImageView = itemView.findViewById(R.id.img)
    }
}