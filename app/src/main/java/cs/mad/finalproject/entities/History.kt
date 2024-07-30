package cs.mad.finalproject.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class History(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    var decisionMade: String,
    var setTitle: String,
    var optionsSnapshot: List<String>
)

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getAll(): Flow<List<History>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(history: History)

    @Delete
    suspend fun delete(history: History)

    @Query("DELETE FROM history")
    suspend fun deleteAll()
}