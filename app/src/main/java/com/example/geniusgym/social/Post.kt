package com.example.geniusgym.social

data class Post(
    val postId: Int,
    val profileImage: Int,
    val username: String,
    val postContent: String,
    val postImage: Int?,
    val likeCount: Int,
    val commentCount: Int,
    val postTime: String,
)