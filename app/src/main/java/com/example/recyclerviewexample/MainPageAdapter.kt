package com.example.recyclerviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.HistoryDatabase.HistoryDetail

class MainPageAdapter(
    private val listener: MainPage
) : RecyclerView.Adapter<MainPageAdapter.ExampleViewHolder>() {

    val allHistory = ArrayList<HistoryDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.example_item,
            parent,
            false
        )
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = allHistory[position]
//        holder.imageView.setImageResource(R.drawable.ic_android)
        holder.textView1.text = "${currentItem.date}"
    }

    override fun getItemCount() = allHistory.size

    fun updateData(newData: List<HistoryDetail>) {
        allHistory.clear()
        allHistory.addAll(newData)
        notifyDataSetChanged()
    }

    inner class ExampleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
//        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textView1: TextView = itemView.findViewById(R.id.text_view_1)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}