package com.itl.kglab.notedatabase.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itl.kglab.notedatabase.NoteData
import com.itl.kglab.notedatabase.databinding.ListNoteItemBinding

class NoteListAdapter(
    private val noteList: MutableList<NoteData> = mutableListOf()
) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    fun addNoteList(list: List<NoteData>) {
        noteList.addAll(list)
//        notifyItemRangeInserted(noteList.size -list.size, noteList.size)
        notifyDataSetChanged()
    }

    fun addNote(note: NoteData) {
        noteList.add(note)
        notifyItemInserted(noteList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListNoteItemBinding.inflate(layoutInflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = noteList[position].title
            tvDate.text = noteList[position].date
        }
    }

    inner class NoteViewHolder(
        val binding: ListNoteItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

}