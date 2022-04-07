package com.example.room.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
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
    ): View {

        binding = FragmentListBinding.inflate(inflater,container,false)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        binding.recycler.adapter = adapter
        binding.recycler.layoutManager =  LinearLayoutManager(requireContext())

        mainActivityViewModel.readAllData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }


        adapter.onItemClick = {
            val action = ListFragmentDirections.actionListFragmentToUpdate2(it)
            findNavController().navigate(action)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {val builder  = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete All")
        builder.setMessage("Are you sure you want to delete All users")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            mainActivityViewModel.deleteAll()
            val text = "All User successfully deleted "
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
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