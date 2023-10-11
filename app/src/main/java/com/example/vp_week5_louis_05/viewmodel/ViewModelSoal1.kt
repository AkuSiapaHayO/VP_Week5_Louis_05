package com.example.vp_week5_louis_05.viewmodel

import android.adservices.appsetid.AppSetIdManager.get
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.vp_week5_louis_05.model.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ViewModelSoal1 : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    private fun makeRandomNumber(): Int {
        val random = (1..10).random()
        _uiState.update { data ->
            data.copy(number = random)
        }
        return _uiState.value.number
    }

    private fun guessPlus() {
        val guess = _uiState.value.guess + 1
        _uiState.update { data ->
            data.copy(guess = guess)
        }
    }

    private fun scorePlus() {
        val score = _uiState.value.score + 1
        _uiState.update { data ->
            data.copy(score = score)
        }
    }

    private fun checkGuess(guess: String): Boolean {
        val check = guess.toIntOrNull()
        return check == _uiState.value.number
    }

    fun isGameOver(guess: String): Boolean {
        if (checkGuess(guess)) {
            scorePlus()
            makeRandomNumber()
            _uiState.update { data ->
                data.copy(guess = 0)
            }
            return if (_uiState.value.score >= 3) {
                _uiState.update { data ->
                    data.copy(score = 3)
                }
                true
            } else {
                false
            }
        } else {
            guessPlus()
            return if (_uiState.value.guess >= 3) {
                _uiState.update { data ->
                    data.copy(guess = 3)
                }
                true
            } else {
                false
            }
        }
    }

    fun reset() {
        makeRandomNumber()
        _uiState.update { data ->
            data.copy(guess = 0, score = 0)
        }
    }


}