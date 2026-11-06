package com.example.unscramble

const val MAX_NO_OF_WORDS = 10
const val SCORE_INCREASE = 20

val allWords: Set<String> =
    setOf(
        "animal",
        "auto",
        "anecdote",
        "alphabet",
        "all",
        "awesome",
        "actor",
        "alarm",
        "adventure",
        "afternoon",
        "apple",
        "attitude",
        "balloon",
        "basket",
        "bench",
        "bicycle",
        "book",
        "brave",
        "bottle",
        "board",
        "brown",
        "basketball",
        "bag",
        "bake",
        "bike",
        "biography",
        "biology",
        "border",
        "bus",
        "button",
        "box",
        "breakfast",
        "bed",
        "beach",
        "bee",
        "beef",
        "beggar"
    )

// Функция для перемешивания букв
fun String.shuffleLetters(): String {
    val chars = toCharArray()
    do {
        chars.shuffle()
    } while (String(chars) == this)
    return String(chars)
}