package com.example.recyclerviewexample.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_data_table")
data class ExerciseDetail(
    @PrimaryKey
    @ColumnInfo(name = "exercise_name")
    var nameOfExercise: String,

    @ColumnInfo(name = "sets_count")
    var sets: String,

    @ColumnInfo(name = "reps_count")
    var reps: String,

    @ColumnInfo(name = "rest_count")
    var restInterval: String,

//    @ColumnInfo(name = "exercise_id")
//    @PrimaryKey(autoGenerate = true)
//    var id: Long = 31
) {}


