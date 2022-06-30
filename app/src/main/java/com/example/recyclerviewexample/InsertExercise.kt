package com.example.recyclerviewexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewexample.R
import com.example.recyclerviewexample.database.ExerciseDetail
import kotlinx.android.synthetic.main.activity_insert_exercise.*

class InsertExercise : AppCompatActivity() {

    private lateinit var viewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_exercise)

        viewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)

        insertNewItem.setOnClickListener {
            val exerciseName = insertExerciseName.text.toString()
            if (exerciseName.isNotEmpty()) {
                viewModel.insertExercise(ExerciseDetail(exerciseName, "0", "0", "0"))
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }

}