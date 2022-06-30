package com.example.recyclerviewexample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.R
import com.example.recyclerviewexample.database.ExerciseDetail

class ExampleAdapter(private val listener: MainActivity) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    val allExercises = ArrayList<ExerciseDetail>()

    inner class ExampleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.example_item,
            parent,
            false
        )
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
       val currentItem = allExercises[position]
        holder.textView1.text = currentItem.nameOfExercise
       /* if (position == 0) {
            holder.textView1.setBackgroundColor(Color.YELLOW)
        } else {
            holder.textView1.setBackgroundColor(Color.WHITE)
        } */
    }

    override fun getItemCount() = allExercises.size

    fun updateData(newData: List<ExerciseDetail>) {
        allExercises.clear()
        allExercises.addAll(newData)
        notifyDataSetChanged()
    }

//   inner class ExampleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
//    View.OnClickListener{
//        val imageView: ImageView = itemView.findViewById(R.id.image_view)
//        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
//        val textView2: TextView = itemView.findViewById(R.id.text_view_2)
//
//        init {
//            itemView.setOnClickListener(this)
//        }
//
//        override fun onClick(v: View?) {
//            val position: Int = absoluteAdapterPosition
//            if(position != RecyclerView.NO_POSITION){
//                listener.onItemClick(position)
//            }
//        }
//    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}