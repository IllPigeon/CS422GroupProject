package cs.mad.finalproject.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cs.mad.finalproject.converters.Converters
import cs.mad.finalproject.entities.Decision
import cs.mad.finalproject.entities.DecisionDao

@Database(entities = [Decision::class], version=1)
@TypeConverters(Converters::class)
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