package hr.fer.tel.ruazosa.lecture6.notes

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class EnterNewNote : AppCompatActivity() {

    private var noteTitle: EditText? = null
    private var noteDescription: EditText? = null
    private var storeButton: Button? = null
    private var cancelButton: Button? = null
    private var forEdit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_new_note)

        noteTitle = findViewById(R.id.note_title)
        noteDescription = findViewById(R.id.note_description)
        storeButton = findViewById(R.id.store_button)
        cancelButton = findViewById(R.id.cancel_button)

        var id: Long? = null
        var title: String? = null
        var description: String? = null

        val extras = intent.extras

        if (extras != null) {
            id = extras.getLong("noteId")
            title = extras.getString("noteTitle")
            description = extras.getString("noteDescription")

            (noteTitle as EditText).setText(title)
            (noteDescription as EditText).setText(description)

            forEdit = true
        }

        storeButton?.setOnClickListener({
            val tableDao = DatabaseHelper(this).getDao(NotesModel.Note::class.java)
            val note = NotesModel.Note(noteTitle = noteTitle?.text.toString(),
                    noteDescription = noteDescription?.text.toString(), noteTime = Date())

            if (note.noteTitle.isNullOrBlank() || note.noteDescription.isNullOrBlank()) {
                Toast.makeText(this,"Please provide both title and description!", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            if (forEdit) {
                note.noteId = id

                tableDao.update(note)
            } else {
                tableDao.create(note)
            }

            NotesModel.notesList.removeAll { true }
            NotesModel.notesList.addAll(tableDao.queryForAll())

            setResult(Activity.RESULT_OK)

            finish()
        })

        cancelButton?.setOnClickListener({
            finish()
        })
    }
}