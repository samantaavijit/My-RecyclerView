package com.avijitsamanta.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ExampleAdopter.OnModalClick {
    private val list = generateDummyList(100)
    private val adopter = ExampleAdopter(list, this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = adopter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): ArrayList<Modal> {
        val list = ArrayList<Modal>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_add
                1 -> R.drawable.ic_car
                else -> R.drawable.ic_child
            }
            val item = Modal(drawable, "My Item $i", "Line $i")
            list.add(item)
        }
        return list
    }

    fun insertItem(view: View) {
        val index: Int = Random.nextInt(8)
        val newItem = Modal(R.mipmap.ic_launcher, "New Item at position $index", "Line $index")
        list.add(index, newItem)
        adopter.notifyItemInserted(index)
    }

    fun removeItem(view: View) {
        val index: Int = Random.nextInt(8)
        list.removeAt(index)
        adopter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
        val clickedItem: Modal = list[position]
        clickedItem.text1 = "Clicked"
        adopter.notifyItemChanged(position)
    }


}