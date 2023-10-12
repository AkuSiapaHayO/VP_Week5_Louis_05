package com.example.vp_week5_louis_05.model

data class UIState(
    var number: Int = (1..10).random(),
    var guess: Int = 0,
    var score: Int = 0,
    var isGameOver: Boolean = false
) {

}