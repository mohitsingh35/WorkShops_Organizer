package com.ncs.assignment.UI.Main.Adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ncs.assignment.R
import com.ncs.assignment.UI.models.WorkshopItem

class MyWorkshopsAdapter(private val context: Context, private val data: List<WorkshopItem>) : RecyclerView.Adapter<MyWorkshopsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.name)
        val img:ImageView=itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.each_item2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.itemText.text = item.name
        Glide.with(context)
            .load(item.imageUrl)
            .into(holder.img)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}