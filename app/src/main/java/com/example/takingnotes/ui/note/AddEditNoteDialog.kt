package com.example.takingnotes.ui.note

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.takingnotes.data.Note
import com.example.takingnotes.databinding.DialogAddEditNoteBinding

class AddEditNoteDialog(
    private val note: Note?,
    private val onSave: (Note) -> Unit
) : DialogFragment() {

    private lateinit var binding: DialogAddEditNoteBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddEditNoteBinding.inflate(LayoutInflater.from(context))

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setTitle(if (note == null) "Add Note" else "Edit Note")
            .setPositiveButton("Save") { _, _ ->
                val title = binding.editTextTitle.text.toString()
                val description = binding.editTextDescription.text.toString()
                if (note == null) {
                    onSave(Note(0, title, description))
                } else {
                    onSave(note.copy(title = title, description = description))
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }

    override fun onStart() {
        super.onStart()
        note?.let {
            binding.editTextTitle.setText(it.title)
            binding.editTextDescription.setText(it.description)
        }
    }
}