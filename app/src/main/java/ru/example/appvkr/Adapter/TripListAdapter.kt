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
import ru.example.appvkr.Activity.DetailActivity
import ru.example.appvkr.Domain.Trips
import ru.example.appvkr.R

class TripListAdapter(private val items: ArrayList<Trips>):
    RecyclerView.Adapter<TripListAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_list_trip, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleTxt?.text = item.Title
        holder.townTxt?.text = item.Name
        holder.dateTxt?.text = item.DateValue
        holder.priceTxt?.text = "%.3f₽".format(item.Price)
        holder.starTxt?.text = item.Star.toString()

        Glide.with(context).load(item.ImagePath)
            .transform(CenterCrop(), RoundedCorners(30))
            .into(holder.pic)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            context.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTxt: TextView? = itemView.findViewById(R.id.titleTxt)
        val townTxt: TextView? = itemView.findViewById(R.id.townTxt)
        val priceTxt: TextView? = itemView.findViewById(R.id.priceText)
        val starTxt: TextView? = itemView.findViewById(R.id.starTxt)
        val dateTxt: TextView? = itemView.findViewById(R.id.dateTxt)
        val pic: ImageView = itemView.findViewById(R.id.img)
    }
}