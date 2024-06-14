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
import ru.example.appvkr.Activity.DetailPathActivity
import ru.example.appvkr.Domain.Path
import ru.example.appvkr.R

class PathListAdapter(private val items: ArrayList<Path>):
    RecyclerView.Adapter<PathListAdapter.ViewHolder>(){
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_transport_cart, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTxt?.text = items[position].Place
        holder.companyTxt?.text = "ООО" + " '"+items[position].Company+"'"
        holder.timeTxt?.text = items[position].Time.toString()
        holder.dateTxt?.text = items[position].DateValue
        holder.priceTxt?.text = "%.3f₽/чел.".format(items[position].Price)
        holder.nameTxt?.text = items[position].Place

        Glide.with(context).load(items[position].ImagePath)
            .transform(CenterCrop(), RoundedCorners(30))
            .into(holder.pic)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, DetailPathActivity::class.java)
            intent.putExtra("object", items[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameTxt: TextView = itemView.findViewById(R.id.pathText)
        val companyTxt: TextView = itemView.findViewById(R.id.companyText)
        val timeTxt: TextView = itemView.findViewById(R.id.timeTxt)
        val dateTxt: TextView = itemView.findViewById(R.id.dateTxt)
        val priceTxt: TextView = itemView.findViewById(R.id.priceText)
        val pic: ImageView = itemView.findViewById(R.id.img)
    }
}