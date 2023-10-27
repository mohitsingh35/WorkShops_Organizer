package com.ncs.assignment.UI.Main.Adapters

import com.ncs.assignment.UI.database.SharedPrefHelper
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.ncs.assignment.R
import com.ncs.assignment.UI.Auth.AuthActivity
import com.ncs.assignment.UI.models.WorkshopItem

class AllWorkShopsAdapter(private val context: Context, private val data: List<WorkshopItem>, private val sharedPreferencesHelper: SharedPrefHelper) :
    RecyclerView.Adapter<AllWorkShopsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.name)
        val img:ImageView=itemView.findViewById(R.id.image)
        val button:Button=itemView.findViewById(R.id.apply)
        init {
            button.setOnClickListener {
                if (FirebaseAuth.getInstance().currentUser!=null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = data[position]
                        handleButtonClick(item)
                    }
                }
                else{
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Login Required")
                    builder.setMessage("Can't Apply to Workshops, You have to be logged in")
                    builder.setPositiveButton("Go to Login Page") { dialog, which ->
                        val intent = Intent(context, AuthActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(intent)
                    }
                    val dialog = builder.create()
                    dialog.show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        if (sharedPreferencesHelper.isWorkshopApplied(item.id)) {
            holder.button.text="Already Applied"
        }
        holder.itemText.text = item.name
        Glide.with(context)
            .load(item.imageUrl)
            .into(holder.img)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    private fun handleButtonClick(item: WorkshopItem) {
        if (sharedPreferencesHelper.isWorkshopApplied(item.id)) {
            val appliedWorkshop = sharedPreferencesHelper.getAppliedWorkshop(item.id)
            Toast.makeText(
                context,
                "Workshop is already applied!\n${appliedWorkshop?.name}",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            sharedPreferencesHelper.applyWorkshop(item)
            Toast.makeText(context, "Workshop applied!", Toast.LENGTH_SHORT).show()
        }
    }

}