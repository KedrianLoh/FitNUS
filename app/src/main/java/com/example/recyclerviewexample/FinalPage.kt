package com.example.recyclerviewexample

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.TodoDatabase.TodoDetail
import kotlinx.android.synthetic.main.activity_final_page.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

var INDEX = 9

class FinalPage : AppCompatActivity(), FinalPageAdapter.IAdapter {

    // we have done this the previous time
    // now let us define view model in our main activity
    private lateinit var viewModel: TodoViewModel
    private lateinit var viewModel1: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_page)
        supportActionBar?.title = "Confirm Workout!"

        val recyclerView: RecyclerView = findViewById(R.id.final_page_recyclerview)
        val adapter = FinalPageAdapter(this)
        recyclerView.adapter = adapter // adapter for recycler view
        recyclerView.layoutManager = LinearLayoutManager(this) // defines the horizontal layout
        // final_page_recyclerview.setHasFixedSize(true) // if we know our recycler view has fixed width and height, we can optimise


//        initialize viewmodel
        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        viewModel1 = ViewModelProvider(this).get(HistoryViewModel::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            val allTodos = viewModel.getListTodos()
            if (allTodos.isNotEmpty()) {
                INDEX = 0
            } else {
                INDEX = 9
            }
        }
//         set the viewmodel to update our recyclerview adapter
        viewModel.allTodoDetail.observe(this, Observer {
            adapter.updateData(it)
        })
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (REUSE_WORKOUT == 1) {
//                    Toast.makeText(this, "1", Toast.LENGTH_SHORT).show()
                    lifecycleScope.launch(Dispatchers.IO) {
                        val allTodos = viewModel.getListTodos()
                        for (element in allTodos) {
                            viewModel.deleteTodo(element)
                        }
                    }
                    REUSE_WORKOUT = 0
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun startWorkout(view: View) {
        if (INDEX == 0) {
            val intent = Intent(this@FinalPage, PrepExercise::class.java)
            startActivity(intent)
        }
    }

    override fun onIconClick(todoDetail: TodoDetail) {
        viewModel.deleteTodo(todoDetail)
    }

    override fun onEditIconClick(todoDetail: TodoDetail) {
        val itemNameToPass: String = todoDetail.name
        DETAIL_COUNT = 2
        val intent = Intent(this, DetailExerciseActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, itemNameToPass)
        }
        startActivity(intent)
    }

//    suspend fun addHistory() {
//        val allTodos = viewModel.getListTodos() // Gets the list of Todos at Final Page
//        val date = Date()
//        if (allTodos.isNotEmpty()) {
//            viewModel1.insertHistory(
//                HistoryDetail(
//                    allTodos as ArrayList<TodoDetail>,
//                    0,
//                    "${sdf.format(date)}"
//                )
//            )
//        }
//        val abc : ArrayList<TodoDetail> = ArrayList()
//        val x : TodoDetail = TodoDetail("x", "1", "1", "1")
//        abc.add(x)
//         abc.add(x)
//        viewModel1.insertHistory(HistoryDetail(abc, 0))
//    }

}