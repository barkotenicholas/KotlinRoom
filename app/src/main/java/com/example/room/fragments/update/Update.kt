package com.example.room.fragments.update

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
import androidx.navigation.fragment.navArgs
import com.example.room.MainActivityViewModel
import com.example.room.R
import com.example.room.database.models.User
import com.example.room.databinding.FragmentUpdateBinding

class Update : Fragment() {


    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateArgs>()
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding  = FragmentUpdateBinding.inflate(inflater,container,false)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.updatelastname.setText(args.currentUser.lastName)
        binding.updatefirstname.setText(args.currentUser.firstName)
        binding.updateage.setText(args.currentUser.age.toString())


        binding.btnUpdate.setOnClickListener{
            updateUser()
        }

        return binding.root
    }

    fun updateUser(){


        val fname = binding.updatefirstname.text.toString()
        val lname = binding.updatelastname.text.toString()
        val age = binding.updateage.text

        if (validateInput(fname, lname, age)) {
            val update = User(args.currentUser.id, fname, lname, Integer.parseInt(age.toString()))

            mainActivityViewModel.updateUser(update)
            val text = "User successfully added"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()

            findNavController().navigate(R.id.action_update_to_listFragment2)

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