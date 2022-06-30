package com.example.recyclerviewexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

var REUSE_WORKOUT = 0

class WorkoutHistoryInfoActivity : AppCompatActivity() {

    private lateinit var viewModel: HistoryViewModel
    private lateinit var viewModel1: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_history_info)
        supportActionBar?.title = "${WORKOUT_HISTORY_INFO!!.date}"

        val recyclerView: RecyclerView = findViewById(R.id.workout_history_recyclerview)
        val adapter = WorkoutHistoryInfoAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        viewModel1 = ViewModelProvider(this).get(TodoViewModel::class.java)
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }

    fun deleteHistory(view: View) {
        viewModel.deleteHistory(WORKOUT_HISTORY_INFO!!)
        val intent = Intent(this, MainPage::class.java)
        startActivity(intent)
    }

    fun reuseWorkout(view: View) {
        lifecycleScope.launch (Dispatchers.IO) {
            val array = WORKOUT_HISTORY_INFO!!.arrayList
            for (i in 0 until array.size) {
                viewModel1.insertTodo(array[i])
            }
            REUSE_WORKOUT = 1
        }
        val intent = Intent(this, FinalPage::class.java)
        startActivity(intent)
        finish()
//        Toast.makeText(this, "${WORKOUT_HISTORY_INFO!!.arrayList!!.size}", Toast.LENGTH_SHORT).show()
    }
}