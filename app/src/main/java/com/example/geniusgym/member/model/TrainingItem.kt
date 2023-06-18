package com.example.geniusgym.member.model
import java.io.Serializable

class TrainingItem (
    val memberId: String,
    val workoutName: String,
    val workoutUpdateTime:String,
    val workoutWeight: String,
    val workoutFreq : String,
    val workoutSet: String
    ):Serializable