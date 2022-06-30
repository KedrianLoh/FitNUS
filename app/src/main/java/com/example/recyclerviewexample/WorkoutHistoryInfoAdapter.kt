package com.example.recyclerviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.HistoryDatabase.HistoryDetail
import com.example.recyclerviewexample.TodoDatabase.TodoDetail

class WorkoutHistoryInfoAdapter(private val listener: WorkoutHistoryInfoActivity) :
    RecyclerView.Adapter<WorkoutHistoryInfoAdapter.FinalViewHolder>() {
    private val allHistory = WORKOUT_HISTORY_INFO!!.arrayList


    class FinalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_2)
        val textView3: TextView = itemView.findViewById(R.id.text_view_3)
        val textView4: TextView = itemView.findViewById(R.id.text_view_4)
        val textView8: TextView = itemView.findViewById(R.id.text_view_8)
        val textView9: TextView = itemView.findViewById(R.id.text_view_9)
        val textView10: TextView = itemView.findViewById(R.id.text_view_10)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinalViewHolder {
        val viewHolder = FinalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.history_example_item, parent, false)
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: FinalViewHolder, position: Int) {
        val currentItem = allHistory!![position]
        holder.textView1.text = currentItem.name
        holder.textView2.text = currentItem.sets
        holder.textView3.text = currentItem.reps
        holder.textView4.text = currentItem.time
        holder.textView8.text = currentItem.weight
    }

    override fun getItemCount(): Int {
       return allHistory!!.size
    }
}
