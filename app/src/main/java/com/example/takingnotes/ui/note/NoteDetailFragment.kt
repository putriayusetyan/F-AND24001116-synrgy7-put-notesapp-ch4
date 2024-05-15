package com.example.takingnotes.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.takingnotes.databinding.FragmentNoteDetailBinding

class NoteDetailFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!
    private val args: NoteDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewTitle.text = args.note.title
        binding.textViewDescription.text = args.note.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}