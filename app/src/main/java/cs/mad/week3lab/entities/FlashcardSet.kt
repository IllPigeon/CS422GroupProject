package cs.mad.week3lab.entities

data class FlashcardSet(val title: String)

fun getFlashcardSets(): List<FlashcardSet> {
    val sets = mutableListOf<FlashcardSet>()
    for (i in 1..10) {
        sets.add(FlashcardSet("Set $i"))
    }
    return sets
}