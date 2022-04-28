package com.example.hw4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.room.Room

import com.example.hw4.databinding.FragmentDialogChangeBinding
import com.example.hw4.room.NoteDatabase

class DialogChangeFragment : DialogFragment() {

    private var _binding: FragmentDialogChangeBinding? = null
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDialogChangeBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            dialogButtonSave.setOnClickListener {
                database.noteDao() ///
            }

            dialogButtonCancel.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}