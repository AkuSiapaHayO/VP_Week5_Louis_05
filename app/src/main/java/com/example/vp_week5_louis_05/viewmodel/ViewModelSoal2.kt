package com.example.vp_week5_louis_05.viewmodel

import com.example.vp_week5_louis_05.model.UIState2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelSoal2 {
    private val _uiState = MutableStateFlow<List<UIState2>>(emptyList())
    val uiState: StateFlow<List<UIState2>> = _uiState.asStateFlow()

    fun addCourse(sks: String, score:String, name:String){
        val intSKS = sks.toInt()
        val doubleScore = score.toDouble()
        val course = UIState2(intSKS, doubleScore, name)
        _uiState.value = _uiState.value + course
    }

    fun deleteCourse(course: UIState2){
        _uiState.value = _uiState.value - course
    }

    fun totalSKS(): Int {
        return _uiState.value.sumOf { data ->
            data.sks
        }
    }

    fun totalIPK(): Double {
        val courses = _uiState.value
        if (courses.isEmpty()) {
            return 0.0
        }
        val totalSKS = _uiState.value.sumOf { data -> data.sks }
        val totalScore = _uiState.value.sumOf {data -> data.sks * data.score }
        val doubleTotalSKS = totalSKS.toDouble()
        return totalScore / doubleTotalSKS
    }
}