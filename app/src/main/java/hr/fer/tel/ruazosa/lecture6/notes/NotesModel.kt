package hr.fer.tel.ruazosa.lecture6.notes

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.util.*

/**
 * Created by dejannovak on 09/04/2018.
 */
object NotesModel {

    @DatabaseTable(tableName = "table")
    data class Note (
            @DatabaseField(generatedId = true)
            var noteId: Long? = null,
            @DatabaseField
            var noteTitle: String? = null,
            @DatabaseField
            var noteDescription: String? = null,
            @DatabaseField
            var noteTime: Date? = null
    )

    var notesList: MutableList<Note> = mutableListOf()
}