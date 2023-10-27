package com.ncs.assignment.UI.Auth.Login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.ncs.assignment.UI.Main.MainActivity
import com.ncs.assignment.R
import com.ncs.assignment.UI.Auth.AuthActivity
import com.ncs.assignment.databinding.FragmentLoginScreenBinding


class LoginScreenFragment : Fragment() {

    companion object {
        fun newInstance() = LoginScreenFragment()
    }

    lateinit var binding: FragmentLoginScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            } else {
                binding.btnLogin.visibility=View.GONE
                binding.progressCircular.visibility=View.VISIBLE
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(requireContext(), MainActivity::class.java)
                            requireContext().startActivity(intent)
                            requireActivity().finish()
                        } else {
                            binding.btnLogin.visibility=View.VISIBLE
                            binding.progressCircular.visibility=View.GONE
                            Toast.makeText(requireContext(), "Login failed. Please try again.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginScreenFragment_to_signUpScreenFragment)
        }
        binding.skip.setOnClickListener {
            val intent = Intent(requireContext(), AuthActivity::class.java)
            requireContext().startActivity(intent)
            requireActivity().finish()
        }
    }






    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpBackPress()
    }

    private fun setUpBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().navigate(R.id.action_loginScreenFragment_to_chooserFragment)
        }
    }



}