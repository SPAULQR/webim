package ru.paul.notes_testwork.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import ru.paul.notes_testwork.R
import ru.paul.notes_testwork.model.Note

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_note_activity)

        init()
    }

    private fun init() {
        val descriptionText: AppCompatEditText = findViewById(R.id.edit_description)
        val headerText: AppCompatEditText = findViewById(R.id.edit_header)

        val createButton: Button = findViewById(R.id.create_button)
        createButton.setOnClickListener {
            val newNote = Note(headerText.text.toString(), descriptionText.text.toString())
            val intent = Intent("create")
            intent.putExtra("note", newNote)
            sendBroadcast(intent)
            Toast.makeText(applicationContext,
                    "Заметка успешно создана",
                    Toast.LENGTH_SHORT).show()
            finish()
        }

        val cancelButton: Button = findViewById(R.id.cancel_button)
        cancelButton.setOnClickListener {
            finish()
        }
    }
}