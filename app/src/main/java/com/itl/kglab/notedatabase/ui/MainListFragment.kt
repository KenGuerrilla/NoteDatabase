package com.itl.kglab.notedatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.itl.kglab.notedatabase.MainListViewModel
import com.itl.kglab.notedatabase.databinding.FragmentMainListBinding
import com.itl.kglab.notedatabase.ui.adapter.NoteListAdapter
import com.itl.kglab.notedatabase.ui.state.MainListViewState

class MainListFragment : BaseFragment() {

    private var _binding: FragmentMainListBinding? = null
    private val binding: FragmentMainListBinding get() = _binding!!

    private val noteListAdapter: NoteListAdapter = NoteListAdapter()

    private val viewModel: MainListViewModel by viewModels {
        MainListViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        initView()
        initAdapter()
        initLiveData()
        return binding.root
    }

    private fun initLiveData() {
        viewModel.viewStateLiveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                MainListViewState.Loading -> {
                    showLoading()
                }
                is MainListViewState.UpdateView -> {
                    val list = state.noteList
                    noteListAdapter.updateNoteList(list)
                    dismissLoading()
                }
            }
        }
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

        noteListAdapter.setItemClickedListener { id ->
            val act = MainListFragmentDirections
                .actionMainListFragmentToEditNoteFragment(id)
            findNavController().navigate(act)
        }
    }

    private fun initView() {
        binding.btCreateNote.setOnClickListener {
            val act = MainListFragmentDirections
                .actionMainListFragmentToEditNoteFragment()
            findNavController().navigate(act)
        }
    }



    override fun onResume() {
        super.onResume()
        viewModel.getNoteList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}