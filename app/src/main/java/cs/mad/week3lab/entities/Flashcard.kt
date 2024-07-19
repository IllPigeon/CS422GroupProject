package cs.mad.week3lab.entities

data class Flashcard(val term: String, val definition: String)

fun getFlashcards(): List<Flashcard> {
    val flashcards = mutableListOf<Flashcard>()
    for (i in 1..10) {
        flashcards.add(Flashcard("Term $i", "Definition $i"))
    }
    return flashcards
}