package com.ncs.assignment.UI.Auth.Chooser

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ncs.assignment.UI.Main.MainActivity
import com.ncs.assignment.R
import com.ncs.assignment.UI.database.SharedPrefHelper
import com.ncs.assignment.databinding.FragmentChooserBinding
class ChooserFragment: Fragment() {

    companion object {
        fun newInstance() = ChooserFragment()
    }

    lateinit var binding: FragmentChooserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        val sharedPreferencesHelper = SharedPrefHelper(requireContext())
        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_chooserFragment_to_loginScreenFragment)
        }
        binding.signup.setOnClickListener {
            findNavController().navigate(R.id.action_chooserFragment_to_signUpScreenFragment)
        }
        binding.skip.setOnClickListener {
            sharedPreferencesHelper.setpref(true)
            requireContext().startActivity(Intent(requireContext(), MainActivity::class.java))
        }

    }


}
