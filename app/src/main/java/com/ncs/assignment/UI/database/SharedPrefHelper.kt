package com.ncs.assignment.UI.database

import android.content.Context
import com.google.gson.Gson
import com.ncs.assignment.UI.models.WorkshopItem

class SharedPrefHelper(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("Workshops", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun applyWorkshop(workshopItem: WorkshopItem) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(workshopItem)
        editor.putString("workshop_${workshopItem.id}", json)
        editor.apply()
    }

    fun isWorkshopApplied(workshopId: Int): Boolean {
        return sharedPreferences.contains("workshop_$workshopId")
    }

    fun getAppliedWorkshop(workshopId: Int): WorkshopItem? {
        val json = sharedPreferences.getString("workshop_$workshopId", null)
        return if (json != null) {
            gson.fromJson(json, WorkshopItem::class.java)
        } else {
            null
        }
    }
    fun getAllAppliedWorkshops(): List<WorkshopItem> {
        val workshops = ArrayList<WorkshopItem>()
        val workshopKeys = sharedPreferences.all.keys
        for (key in workshopKeys) {
            if (key.startsWith("workshop_")) {
                val json = sharedPreferences.getString(key, null)
                if (json != null) {
                    val workshopItem = gson.fromJson(json, WorkshopItem::class.java)
                    workshops.add(workshopItem)
                }
            }
        }

        return workshops
    }
}
