package com.example.room.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.database.models.User
import com.example.room.databinding.CustomRowBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHodler>() {

    private var userList = emptyList<User>()

    class ViewHodler(private val itemViewa: CustomRowBinding) :
        RecyclerView.ViewHolder(itemViewa.root) {
        fun bind(user: User) {
            itemViewa.textView2.text = user.id.toString()
            itemViewa.fname.text = user.firstName
            itemViewa.lname.text = user.lastName
            itemViewa.ageShow.text = user.age.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHodler {
        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHodler(binding)
    }

    override fun onBindViewHolder(holder: ViewHodler, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = userList.size

    fun setData(user : List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}