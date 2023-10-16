package com.ncs.assignment.UI.Main.AllWorkshops

import com.ncs.assignment.UI.database.SharedPrefHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ncs.assignment.UI.Main.Adapters.AllWorkShopsAdapter
import com.ncs.assignment.UI.database.DatabseHelper
import com.ncs.assignment.databinding.FragmentAllWorkshopsBinding


class AllWorkshopsFragment : Fragment() {
    lateinit var binding: FragmentAllWorkshopsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllWorkshopsBinding.inflate(inflater, container, false)
        val recyclerView:RecyclerView=binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val sharedPreferencesHelper = SharedPrefHelper(requireContext())
        val dbHelper = DatabseHelper(requireContext())
        val workshops = dbHelper.getallWorkshops()
        val adapter = AllWorkShopsAdapter(requireContext(),workshops,sharedPreferencesHelper)
        recyclerView.adapter = adapter
        return binding.root
    }


}