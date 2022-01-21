package com.example.afinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class ToDoAdapter(
    val context: Context,
    val titlesArr: ArrayList<String>
//    val descriptionsArr: String,
) :
    RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val llTodoMainItem = view.ll_item_main
        val tvTitle = view.tvTitle
        val tvDescription = view.tvDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        for (i in 0..titlesArr.length) {
//            val title: String = titlesArr[position].toString()
//        }
        val title: String = titlesArr[position].toString()
//        val description: String = descriptionsArr.get(position)

        holder.tvTitle.text = title
//        holder.tvDescription.text = description
    }

    override fun getItemCount(): Int {
        return titlesArr.size
    }
}