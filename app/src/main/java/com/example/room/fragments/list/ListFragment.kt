package com.example.room.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.MainActivityViewModel
import com.example.room.R
import com.example.room.databinding.FragmentListBinding
import com.example.room.fragments.list.adapter.ListAdapter


class ListFragment : Fragment() {

    private lateinit var binding :FragmentListBinding
    var adapter = ListAdapter()
    private lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(inflater,container,false)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager =  LinearLayoutManager(requireContext())

        mainActivityViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        return binding.root
    }


}