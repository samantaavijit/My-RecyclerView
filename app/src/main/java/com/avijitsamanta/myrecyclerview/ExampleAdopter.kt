package com.avijitsamanta.myrecyclerview


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*

class ExampleAdopter(
    private val list: List<Modal>,
    private val context: Context,
    private val listener: OnModalClick
) :
    RecyclerView.Adapter<ExampleAdopter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.example_item, parent, false)
        return ExampleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.imageView.setImageResource(list[position].imageResource)
        holder.text1.text = list[position].text1
        holder.text2.text = list[position].text2

    }

    override fun getItemCount() = list.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.image_view
        val text1: TextView = itemView.text_view_1
        val text2: TextView = itemView.text_view_2

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(adapterPosition)
        }
    }

    interface OnModalClick {
        fun onItemClick(position: Int)
    }

}