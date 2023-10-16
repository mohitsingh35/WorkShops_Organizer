package com.ncs.assignment.UI.Main.MyWorkshops

import com.ncs.assignment.UI.database.SharedPrefHelper
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.ncs.assignment.UI.Auth.AuthActivity
import com.ncs.assignment.UI.Main.Adapters.MyWorkshopsAdapter
import com.ncs.assignment.databinding.FragmentMyWorkshopsBinding


class MyWorkshopsFragment : Fragment() {
    lateinit var binding: FragmentMyWorkshopsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyWorkshopsBinding.inflate(inflater, container, false)
        val sharedPreferencesHelper = SharedPrefHelper(requireContext())
        if (FirebaseAuth.getInstance().currentUser!=null){
            val recyclerView: RecyclerView =binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val workshops = sharedPreferencesHelper.getAllAppliedWorkshops()
            val adapter = MyWorkshopsAdapter(requireContext(),workshops)
            recyclerView.adapter = adapter
        }
        if (FirebaseAuth.getInstance().currentUser==null){
            binding.recyclerView.visibility=View.GONE
            binding.noworkshops.visibility=View.GONE
            binding.layout.visibility=View.VISIBLE
            binding.loginBtn.setOnClickListener {
                val intent = Intent(requireContext(), AuthActivity::class.java)
                requireContext().startActivity(intent)
                requireActivity().finish()
            }

        }
        if (sharedPreferencesHelper.getAllAppliedWorkshops().isEmpty() && FirebaseAuth.getInstance().currentUser==null){
            binding.recyclerView.visibility=View.GONE
            binding.layout.visibility=View.VISIBLE
            binding.noworkshops.visibility=View.GONE
        }
        if (sharedPreferencesHelper.getAllAppliedWorkshops().isEmpty() && FirebaseAuth.getInstance().currentUser!=null){
            binding.recyclerView.visibility=View.GONE
            binding.layout.visibility=View.GONE
            binding.noworkshops.visibility=View.VISIBLE
        }


        return binding.root
    }


}