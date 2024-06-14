package ru.example.appvkr.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.example.appvkr.Domain.Hotel
import ru.example.appvkr.Helper.ManagmentCartHotel
import ru.example.appvkr.R

class CartHotelAdapter (
    private val list: ArrayList<Hotel>,
    private val context: Context,
    private val changeNumberItemsListener: () -> Unit
): RecyclerView.Adapter<CartHotelAdapter.ViewHolder>(){

    private val managmentCartHotel = ManagmentCartHotel(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart, parent, false)
        return ViewHolder(inflate)
    }

    private fun updateCart(){
        notifyDataSetChanged()
        changeNumberItemsListener.invoke()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hotel = list[position]
        holder.apply {
            title.text = hotel.Name
            town.text = hotel.Title
            feeEachItem.text = "%.3f₽".format(hotel.numberInCart!! * hotel.Price!!)
            totalEachItem.text = "${hotel.numberInCart} * %.3f₽".format(hotel.Price)
            num.text = hotel.numberInCart.toString()

            Glide.with(itemView.context)
                .load(hotel.ImagePath)
                .transform(CenterCrop(), RoundedCorners(30))
                .into(pic)
        }

        holder.plusItem.setOnClickListener{
            managmentCartHotel.plusNumberItem(list, position, {updateCart()})
        }
        holder.minusItem.setOnClickListener{
            managmentCartHotel.minusNumberItem(list, position, {updateCart()})
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.titleTxt)
        val town: TextView = itemView.findViewById(R.id.townTxt)
        val pic: ImageView = itemView.findViewById(R.id.pic)
        val feeEachItem: TextView = itemView.findViewById(R.id.feeEachItem)
        val plusItem: TextView = itemView.findViewById(R.id.plusCartBtn)
        val minusItem: TextView = itemView.findViewById(R.id.minusCartBtn)
        val totalEachItem: TextView = itemView.findViewById(R.id.totalEachItem)
        val num: TextView = itemView.findViewById(R.id.numberItemTxt)
    }
}