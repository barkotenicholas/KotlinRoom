package com.example.room.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.room.R
import com.example.room.databinding.FragmentUpdateBinding

class Update : Fragment() {


    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding  = FragmentUpdateBinding.inflate(inflater,container,false)

        binding.updatelastname.setText(args.currentUser.lastName)
        binding.updatefirstname.setText(args.currentUser.firstName)
        binding.updateage.setText(args.currentUser.age.toString())

        return binding.root
    }

}