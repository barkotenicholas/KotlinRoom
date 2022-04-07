package com.example.room.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
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

        setHasOptionsMenu(true)
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder  = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete ${args.currentUser.firstName}")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            mainActivityViewModel.deleteUser(args.currentUser)
            val text = "User successfully deleted ${args.currentUser.firstName}"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
            findNavController().navigate(R.id.action_update_to_listFragment2)
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            val text = "Deletion Operation cancelled"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }

        builder.show()
    }
}