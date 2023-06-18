package com.example.geniusgym.member.model
import java.io.Serializable

class WorkoutMainItem(
    var sb_id: String,
    var sb_name: String
    )

class WorkoutSmallItem(
    var sb_id: String,
    var sc_id: String,
    var sc_name: String
    ):Serializable