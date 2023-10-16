package com.ncs.assignment.UI.Auth.SignUp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.ncs.assignment.UI.Main.MainActivity
import com.ncs.assignment.R
import com.ncs.assignment.databinding.FragmentSignUpBinding


class SignUpScreenFragment: Fragment() {

    companion object {
        const val TAG = "SignUpScreenFragment"
        fun newInstance() = SignUpScreenFragment()
    }

    lateinit var binding: FragmentSignUpBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignup.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            } else {
                binding.btnSignup.visibility=View.GONE
                binding.progressCircular.visibility=View.VISIBLE
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "SignUp successful!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(requireContext(), MainActivity::class.java)
                            requireContext().startActivity(intent)
                            requireActivity().finish()
                        } else {
                            binding.btnSignup.visibility = View.VISIBLE
                            binding.progressCircular.visibility = View.GONE
                            val errorMessage = task.exception?.message
                            if (errorMessage != null) {
                                Toast.makeText(requireContext(), "SignUp failed: $errorMessage", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(requireContext(), "SignUp failed. Please try again.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            }
        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpScreenFragment_to_loginScreenFragment)
        }
        binding.skip.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
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
            findNavController().navigate(R.id.action_signUpScreenFragment_to_chooserFragment)
        }
    }


}