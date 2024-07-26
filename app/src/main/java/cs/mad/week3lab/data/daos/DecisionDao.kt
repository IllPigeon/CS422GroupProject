package cs.mad.week3lab.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cs.mad.week3lab.data.entities.Decision

@Dao
interface DecisionDao {
    @Query("SELECT * FROM decision")
    fun getAll(): List<Decision>
    @Query("SELECT * FROM decision WHERE id = :id")
    fun getById(id: Int): Decision
    @Query("SELECT * FROM decision WHERE name = :name")
    fun getByName(name: String): Decision
    @Query("SELECT * FROM decision WHERE listId = :listId")
    fun getByListId(listId: Int): List<Decision>

    @Insert
    fun insert(decision: Decision)
    @Update
    fun update(decision: Decision)
    @Delete
    fun delete(decision: Decision)

}