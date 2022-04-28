package com.example.hw4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.hw4.R
import com.example.hw4.data.Note
import com.example.hw4.databinding.FragmentAddNoteBinding
import com.example.hw4.databinding.FragmentNoteBinding
import com.example.hw4.room.NoteDatabase

class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val database: NoteDatabase by lazy {
        Room.databaseBuilder(
            requireContext().applicationContext,
            NoteDatabase::class.java,
            "notes-db"
        )
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAddNoteBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonAdd.setOnClickListener {
                val title = editTitle.text.toString()
                val description = editDescription.text.toString()
                val date = editDate.text.toString()

                database.noteDao()
                    .insertAll(Note(title = title, description = description, date = date))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}