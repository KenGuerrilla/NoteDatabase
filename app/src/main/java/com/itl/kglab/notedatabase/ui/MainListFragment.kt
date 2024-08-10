package com.itl.kglab.notedatabase.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.itl.kglab.notedatabase.NoteData
import com.itl.kglab.notedatabase.R
import com.itl.kglab.notedatabase.databinding.FragmentMainListBinding

class MainListFragment : Fragment() {

    private var _binding: FragmentMainListBinding? = null
    private val binding: FragmentMainListBinding get() = _binding!!

    private val noteListAdapter: NoteListAdapter = NoteListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        initView()
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        binding.noteList.apply {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(), DividerItemDecoration.VERTICAL
                    )
                )
            }
            adapter = noteListAdapter
        }
    }

    private fun initView() {
        binding.btCreateNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainListFragment_to_editNoteFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        val list = mutableListOf<NoteData>().apply {
            repeat(10) { index ->
                add(
                    NoteData("Note Title $index", "2023-01-01", "Note Content $index")
                )
            }
        }
        noteListAdapter.addNoteList(list)
    }

}