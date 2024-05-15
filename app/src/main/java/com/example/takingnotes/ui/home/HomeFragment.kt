package com.example.takingnotes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.takingnotes.R
import com.example.takingnotes.data.Note
import com.example.takingnotes.databinding.FragmentHomeBinding
import com.example.takingnotes.ui.note.AddEditNoteDialog
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NoteAdapter { note -> showEditDialog(note) }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        homeViewModel.allNotes.observe(viewLifecycleOwner, Observer { notes ->
            notes?.let {
                if (it.isEmpty()) {
                    binding.textViewEmpty.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.textViewEmpty.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    adapter.submitList(it)
                }
            }
        })

        binding.fabAdd.setOnClickListener {
            AddEditNoteDialog(null) { note -> homeViewModel.add(note) }
                .show(childFragmentManager, "AddEditNoteDialog")
        }
    }

    private fun showEditDialog(note: Note) {
        AddEditNoteDialog(note) { updatedNote -> homeViewModel.update(updatedNote) }
            .show(childFragmentManager, "AddEditNoteDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}