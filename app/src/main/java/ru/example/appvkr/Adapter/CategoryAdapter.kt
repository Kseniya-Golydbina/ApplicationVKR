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
import ru.example.appvkr.Activity.ListTripsActivity
import ru.example.appvkr.Domain.Category
import ru.example.appvkr.R

class CategoryAdapter(private val context: Context, private val items: ArrayList<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTxt.text = items[position].Name

        when(position){
            0 -> holder.pic.setBackgroundResource(R.drawable.background_1)
            1 -> holder.pic.setBackgroundResource(R.drawable.background_0)
            2 -> holder.pic.setBackgroundResource(R.drawable.background_2)
            3 -> holder.pic.setBackgroundResource(R.drawable.background_3)
            4 -> holder.pic.setBackgroundResource(R.drawable.background_4)
            5 -> holder.pic.setBackgroundResource(R.drawable.background_5)
            6 -> holder.pic.setBackgroundResource(R.drawable.background_6)
            7 -> holder.pic.setBackgroundResource(R.drawable.background_7)
        }
        val drawableResourceId = context.resources.getIdentifier(
            items[position].ImagePath,
            "drawable",
            holder.itemView.context.packageName
        )
        Glide.with(context).load(drawableResourceId).into(holder.pic)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ListTripsActivity::class.java)
            intent.putExtra("CategoryId", items[position].Id)
            intent.putExtra("CategoryName", items[position].Name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameTxt: TextView = itemView.findViewById(R.id.nameCategory)
        val pic: ImageView = itemView.findViewById(R.id.imageCategory)
    }
}