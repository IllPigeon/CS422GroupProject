package cs.mad.week3lab.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cs.mad.week3lab.entities.Decision
import cs.mad.week3lab.entities.DecisionDao

@Database(entities = [Decision::class], version=1)
abstract class DecisionDatabase : RoomDatabase() {

    abstract fun decisionDao(): DecisionDao

    companion object {
        @Volatile
        private var INSTANCE: DecisionDatabase? = null

        fun getDatabase(context: Context): DecisionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DecisionDatabase::class.java,
                    "decision_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}