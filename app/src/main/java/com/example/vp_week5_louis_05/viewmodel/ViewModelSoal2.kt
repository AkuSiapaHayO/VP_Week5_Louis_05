package com.example.vp_week5_louis_05.viewmodel

import com.example.vp_week5_louis_05.model.UIState2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelSoal2 {
    private val _uiState = MutableStateFlow<List<UIState2>>(emptyList())
    val uiState: StateFlow<List<UIState2>> = _uiState.asStateFlow()

    fun add(sks: String, score:String, nama:String){
        val newsks = sks.toInt()
        val newscore = score.toDouble()

        val new = UIState2(newsks, newscore, nama)

        _uiState.value = _uiState.value + new
    }

    fun delete(model: UIState2){
        _uiState.value = _uiState.value - model
    }

    fun totalSKS(): Int {
        return _uiState.value.sumOf { it.sks }
    }

    fun totalIPK() : Double {
        val totalSKS = _uiState.value.sumOf { it.sks }
        val totalNilai = _uiState.value.sumOf { it.sks  * it.score}

        val total = totalNilai/totalSKS

        val totalFormat = if(total !=0.0) {
            return %2f
        }else {
            return 0.00
        }

        return total
    }
}