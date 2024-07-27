package cs.mad.week3lab.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cs.mad.week3lab.data.daos.DecisionDao
import cs.mad.week3lab.data.entities.Decision

@Database(entities = [Decision::class], version = 1)
abstract class DecisionDatabase : RoomDatabase() {
    abstract fun DecisionDao(): DecisionDao

    //Singleton pattern for instantiating the database
    companion object{
        @Volatile
        private var INSTANCE: DecisionDatabase? = null
        fun getDatabase(context: Context): DecisionDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
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