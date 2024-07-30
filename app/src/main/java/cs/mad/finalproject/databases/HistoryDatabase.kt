package cs.mad.finalproject.databases

import android.content.Context
import cs.mad.finalproject.entities.History
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cs.mad.finalproject.entities.HistoryDao

@Database(entities = [History::class], version=1)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        fun getDatabase(context: Context): HistoryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDatabase::class.java,
                    "history_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}