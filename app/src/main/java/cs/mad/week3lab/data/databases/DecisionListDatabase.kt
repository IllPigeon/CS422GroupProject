package cs.mad.week3lab.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cs.mad.week3lab.data.daos.DecisionListDao
import cs.mad.week3lab.data.entities.DecisionList

@Database(entities = [DecisionList::class], version = 1)
abstract class DecisionListDatabase : RoomDatabase() {
    abstract fun decisionListDao(): DecisionListDao

    //Singleton pattern for instantiating the database
    companion object{
        @Volatile
        private var INSTANCE: DecisionListDatabase? = null
        fun getDatabase(context: Context): DecisionListDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
               val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DecisionListDatabase::class.java,
                    "decision_list_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}