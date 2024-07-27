package cs.mad.week3lab.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cs.mad.week3lab.data.entities.DecisionList

@Dao
interface DecisionListDao {
    @Query("SELECT * FROM DecisionList ORDER BY id ASC")
    fun getAll(): List<DecisionList>
    @Insert
    fun insert(decisionList: DecisionList): Long
    @Update
    fun update(decisionList: DecisionList)
    @Delete
    fun delete(decisionList: DecisionList)
}