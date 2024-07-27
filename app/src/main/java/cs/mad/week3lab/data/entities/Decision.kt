package cs.mad.week3lab.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Decision(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String?,
    //necessary for keeping track of which activities belong to the correct parent or activity list
    //ie if looking at DATE IDEAS activity list, then activities under that listid will be displayed
    @ColumnInfo(name = "listId") var listId: Int?
)