package cs.mad.finalproject

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runOnIO(lambda: suspend () -> Unit) {
    runBlocking {
        launch(Dispatchers.IO) {
            lambda()
        }
    }
}