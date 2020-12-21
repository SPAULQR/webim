package ru.paul.notes_testwork.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.paul.notes_testwork.R
import ru.paul.notes_testwork.model.Note

class RecyclerViewAdapter(private val mContext: Context,
                          private val mNotes: MutableList<Note>)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    class ViewHolder(itemView: View,
                     val mHeader: TextView = itemView.findViewById(R.id.header),
                     val mDescription: TextView = itemView.findViewById(R.id.description))
        : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.layout_listitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mHeader.text = mNotes[position].header
        holder.mDescription.text = mNotes[position].description
    }

    override fun getItemCount(): Int {
        return mNotes.size
    }


    var itemTouchHelperCallback:
            ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
        override fun onMove(recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val adapterPosition = viewHolder.adapterPosition
            Toast.makeText(mContext,
                    mNotes[adapterPosition].header + " было удалено",
                    Toast.LENGTH_SHORT).show()
            mNotes.removeAt(adapterPosition)
            notifyDataSetChanged()
        }
    }
}