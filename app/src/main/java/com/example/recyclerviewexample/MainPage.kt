package com.example.recyclerviewexample

import android.app.ActionBar.DISPLAY_SHOW_CUSTOM
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewexample.HistoryDatabase.HistoryDetail
import com.example.recyclerviewexample.TodoDatabase.TodoDetail
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//var  WORKOUT_HISTORY_INFO : List<TodoDetail>? = null
var  WORKOUT_HISTORY_INFO : HistoryDetail? = null

class MainPage : AppCompatActivity(), ExampleAdapter.OnItemClickListener {

    private val adapter = MainPageAdapter(this)
    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        supportActionBar?.title = "Create Workout!"

        recyclerView.adapter = adapter // adapter for recycler view
        recyclerView.layoutManager = LinearLayoutManager(this) // defines the horizontal layout
        (recyclerView.layoutManager as LinearLayoutManager).setStackFromEnd(true);
        (recyclerView.layoutManager as LinearLayoutManager).setReverseLayout(true);

        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        viewModel.allHistoryDetail.observe(this, Observer {
            adapter.updateData(it)
        })
    }
    override fun onBackPressed() {
//        super.onBackPressed()
    }

    override fun onItemClick(position: Int) {
        val currentWorkoutDate: String = adapter.allHistory[position].date
//        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        lifecycleScope.launch(Dispatchers.IO) {
            WORKOUT_HISTORY_INFO = viewModel.getHistoryDetail(currentWorkoutDate)
        }
        val intent = Intent(this, WorkoutHistoryInfoActivity::class.java)
        startActivity(intent)
        adapter.notifyItemChanged(position)
    }

    fun createNewWorkout(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_page_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent_logout = Intent(this, MainActivity2::class.java)
        when (item.itemId) {
            R.id.logout -> startActivity(intent_logout)
        }
        return super.onOptionsItemSelected(item)
    }

}