package cs.mad.week3lab.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(decision: Decision)

    @Delete
    suspend fun delete(decision: Decision)

    @Query("DELETE FROM decision")
    suspend fun deleteAll()
}