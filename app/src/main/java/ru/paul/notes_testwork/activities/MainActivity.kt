package ru.paul.notes_testwork.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.paul.notes_testwork.R
import ru.paul.notes_testwork.adapter.RecyclerViewAdapter
import ru.paul.notes_testwork.model.Note
import ru.paul.notes_testwork.utils.noteListExample
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


class MainActivity : AppCompatActivity() {

    private lateinit var mNotes: MutableList<Note>
    private lateinit var mAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initApp()
    }

    override fun onPause() {
        saveNoteList()
        super.onPause()
    }

    private fun initApp() {
        initAddButton()
        initRecyclerView()
        initBroadcastReceiver()
    }

    private fun saveNoteList() {
        val writer = ObjectOutputStream(openFileOutput("NoteList.txt", MODE_PRIVATE))
        writer.writeObject(mNotes)
        writer.close()
    }

    private fun initNoteList(): MutableList<Note> {
        return try {
            val reader = ObjectInputStream(openFileInput("NoteList.txt"))
            reader.readObject() as MutableList<Note>
        } catch (e: Exception) {
            Log.i("NoteList", "File not found, used new file" + e.localizedMessage)
            noteListExample()
        }
    }

    private fun initAddButton() {
        val addButton: FloatingActionButton = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            startActivity(Intent(this, NoteActivity::class.java))
        }
    }

    private fun initRecyclerView() {
        mNotes = initNoteList()
        mAdapter = RecyclerViewAdapter(this, mNotes)
        val mRecyclerView: RecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        ItemTouchHelper(mAdapter.itemTouchHelperCallback).attachToRecyclerView(mRecyclerView)
    }

    private fun initBroadcastReceiver() {
        val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val action = intent.action
                if (action == "create") {
                    mNotes.add(
                            intent.getSerializableExtra("note") as Note
                    )
                    mAdapter.notifyDataSetChanged()
                }
            }
        }
        registerReceiver(broadcastReceiver, IntentFilter("create"))
    }
}