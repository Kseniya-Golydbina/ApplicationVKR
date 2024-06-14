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

class BestTripsAdapter(private val context: Context,private val items: ArrayList<Trips>): RecyclerView.Adapter<BestTripsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_best_deal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trip = items[position]
        holder.bind(trip)

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
       val titleTxt: TextView = itemView.findViewById(R.id.titleText)
       val priceTxt: TextView = itemView.findViewById(R.id.priceTxt)
       val starTxt: TextView = itemView.findViewById(R.id.starText)
       val dateTxt: TextView = itemView.findViewById(R.id.datx)
       val townTxt: TextView = itemView.findViewById(R.id.townText)
       val pic: ImageView = itemView.findViewById(R.id.pic)

       fun bind(trips: Trips){
           titleTxt.text = trips.Title
           townTxt.text = trips.Name
           priceTxt.text = "%.3f".format(trips.Price)+ "₽"
           dateTxt.text = trips.DateValue
           starTxt.text = trips.Star.toString()

           Glide.with(context).load(trips.ImagePath).transform(CenterCrop(), RoundedCorners(30)).into(pic)
       }
   }
}