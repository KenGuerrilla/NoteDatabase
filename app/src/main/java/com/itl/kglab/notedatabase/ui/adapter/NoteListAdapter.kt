package com.itl.kglab.notedatabase.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itl.kglab.notedatabase.db.NoteData
import com.itl.kglab.notedatabase.databinding.ListNoteItemBinding

class NoteListAdapter(
    private var noteList: List<NoteData> = mutableListOf()
) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    fun updateNoteList(list: List<NoteData>) {
        noteList = list
        notifyDataSetChanged()
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