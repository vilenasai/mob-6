package com.example.unscramble

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {

    private val viewModel = GameViewModel()

    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        assertFalse(currentGameUiState.isGuessedWordWrong)
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
    }

    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        val incorrectPlayerWord = "wrong"
        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()

        val currentGameUiState = viewModel.uiState.value
        assertTrue(currentGameUiState.isGuessedWordWrong)
        assertEquals(0, currentGameUiState.score)
    }

    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val gameUiState = viewModel.uiState.value
        val unScrambledWord = getUnscrambledWord(gameUiState.currentScrambledWord)

        assertTrue(unScrambledWord in allWords)
        assertEquals(1, gameUiState.currentWordCount)
        assertEquals(0, gameUiState.score)
        assertFalse(gameUiState.isGuessedWordWrong)
        assertFalse(gameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        val currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        val updatedGameUiState = viewModel.uiState.value
        val lastWordCount = updatedGameUiState.currentWordCount

        viewModel.skipWord()

        val skippedGameUiState = viewModel.uiState.value
        assertEquals(lastWordCount + 1, skippedGameUiState.currentWordCount)
        assertEquals(updatedGameUiState.score, skippedGameUiState.score)
    }

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
    }
}

fun getUnscrambledWord(scrambledWord: String): String {
    val possibleWords = allWords.filter {
        it.length == scrambledWord.length &&
                it.toCharArray().sorted() == scrambledWord.toCharArray().sorted()
    }
    return if (possibleWords.isNotEmpty()) possibleWords.first() else ""
}