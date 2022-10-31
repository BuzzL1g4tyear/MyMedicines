package com.valentin.domain.model

data class Medicine(
    var id: Int = 0,
    var name: String,
    var date: String,
    var isLiked: Boolean = false
) : Item