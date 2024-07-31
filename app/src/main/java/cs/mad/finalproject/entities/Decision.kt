package cs.mad.finalproject.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Entity
data class Decision(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    var title: String,
    var options: List<String>
)

@Dao
interface DecisionDao {
    @Query("Select * From decision")
    fun getAll(): Flow<List<Decision>>

    @Query("SELECT * FROM decision")
    suspend fun getSnapshot(): List<Decision>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(decision: Decision)

    @Update
    suspend fun update(decision: Decision)

    @Delete
    suspend fun delete(decision: Decision)

    @Query("DELETE FROM decision")
    suspend fun deleteAll()
}