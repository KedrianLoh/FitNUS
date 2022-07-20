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
        val textView2: TextView = itemView.findViewById(R.id.textMuscle)
        val textView3: TextView = itemView.findViewById(R.id.inputNumber)
        val imageView: ImageView = itemView.findViewById(R.id.displayMuscle)
        val deleteButton: ImageView = itemView.findViewById(R.id.image_view)
        val editButton: ImageView = itemView.findViewById(R.id.imageView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinalViewHolder {
        val viewHolder = FinalViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.final_example_item, parent, false)
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
        holder.textView2.text =
            " Sets: ${currentItem.sets} | Reps: ${currentItem.reps} | Rest: ${currentItem.time}"
        holder.textView3.text = currentItem.weight
        when (currentItem.muscle) {
            "chest" -> holder.imageView.setImageResource(R.drawable.ic_chest)
            "back" -> holder.imageView.setImageResource(R.drawable.ic_back)
            "shoulders" -> holder.imageView.setImageResource(R.drawable.ic_shoulder)
            "abs" -> holder.imageView.setImageResource(R.drawable.ic_abs)
            "arms" -> holder.imageView.setImageResource(R.drawable.ic_arms)
            "legs" -> holder.imageView.setImageResource(R.drawable.ic_legs)
        }
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

