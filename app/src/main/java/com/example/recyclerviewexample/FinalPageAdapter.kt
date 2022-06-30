package com.example.recyclerviewexample

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import android.widget.ListAdapter
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.TodoDatabase.TodoDetail
import com.example.recyclerviewexample.database.ExerciseDetail

class FinalPageAdapter(private val listener: FinalPage) :
    RecyclerView.Adapter<FinalPageAdapter.FinalViewHolder>() {

    private val allTodos = ArrayList<TodoDetail>()

    class FinalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_2)
        val textView3: TextView = itemView.findViewById(R.id.text_view_3)
        val textView4: TextView = itemView.findViewById(R.id.text_view_4)
        val textView8: TextView = itemView.findViewById(R.id.text_view_8)
        val textView9: TextView = itemView.findViewById(R.id.text_view_9)
        val textView10: TextView = itemView.findViewById(R.id.text_view_10)
        val deleteButton: ImageView = itemView.findViewById(R.id.image_view)
        val editButton: ImageView = itemView.findViewById(R.id.imageView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinalViewHolder {
        val viewHolder = FinalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.final_example_item, parent, false)
        )
        viewHolder.deleteButton.setOnClickListener {
            listener.onIconClick(allTodos[viewHolder.absoluteAdapterPosition])
        }
        viewHolder.editButton.setOnClickListener {
            listener.onEditIconClick((allTodos[viewHolder.absoluteAdapterPosition]))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FinalViewHolder, position: Int) {
            val currentItem = allTodos[position]
            holder.textView1.text = currentItem.name
            holder.textView2.text = currentItem.sets
            holder.textView3.text = currentItem.reps
            holder.textView4.text = currentItem.time
            holder.textView8.text = currentItem.weight
    }

    override fun getItemCount() = allTodos.size

    // update our items
    fun updateData(newData: List<TodoDetail>) {
        allTodos.clear()
        allTodos.addAll(newData)
        notifyDataSetChanged()
    }

    interface IAdapter {
        fun onIconClick(todoDetail: TodoDetail)
        fun onEditIconClick(todoDetail: TodoDetail)
    }
}

