package com.example.takingnotes.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.takingnotes.data.Note
import com.example.takingnotes.databinding.ItemNoteBinding

class NoteAdapter(private val onEditClick: (Note) -> Unit) :
    ListAdapter<Note, NoteAdapter.NoteViewHolder>(NotesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.apply {
                textViewTitle.text = note.title
                textViewDescription.text = note.description
                imageViewEdit.setOnClickListener {
                    onEditClick(note)
                }
            }
        }
    }

    class NotesComparator : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }
    }
}