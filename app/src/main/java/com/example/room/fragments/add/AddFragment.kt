package com.example.room.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.MainActivityViewModel
import com.example.room.R
import com.example.room.database.models.User
import com.example.room.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            insertIntoDatabase()
        }

        return binding.root
    }

    private fun insertIntoDatabase() {

        val fname = binding.firstname.text.toString()
        val lname = binding.lastname.text.toString()
        val age = binding.age.text

        if (validateInput(fname, lname, age)) {
            val user = User(0, fname, lname, Integer.parseInt(age.toString()))

            mainActivityViewModel.addUser(user)

            val text = "User successfully added"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {

            val text = "Please fill required fields"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
    }

    private fun validateInput(fname: String, lname: String, age: Editable?): Boolean {

        return if (age != null) {
            !(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname) && age.isEmpty())
        } else {
            false
        }
    }


}