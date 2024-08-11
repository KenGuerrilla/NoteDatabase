package com.itl.kglab.notedatabase.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.itl.kglab.notedatabase.EditNoteViewModel
import com.itl.kglab.notedatabase.databinding.FragmentEditNoteBinding
import com.itl.kglab.notedatabase.db.NoteData
import com.itl.kglab.notedatabase.ui.state.EditNoteViewState

class EditNoteFragment : BaseFragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding: FragmentEditNoteBinding get() = _binding!!

    private val viewModel: EditNoteViewModel by viewModels {
        EditNoteViewModel.Factory
    }

    private val args: EditNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        initView()
        initLiveData()
        return binding.root
    }

    private fun initLiveData() {
        viewModel.viewStateLiveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                EditNoteViewState.Loading -> {
                    showLoading()
                }
                EditNoteViewState.Confirm -> {
                    viewModel.saveNote()
                }

                is EditNoteViewState.InitView -> {
                    updateTable(state.noteData)
                    dismissLoading()
                }
            }

        }
    }

    private fun updateTable(noteData: NoteData) {
        binding.apply {
            etTitleInput.setText(noteData.title)
            etDateInput.setText(noteData.date)
            etNoteInput.setText(noteData.note)
        }
    }

    private fun initView() {
        binding.btSave.setOnClickListener {
            viewModel.saveNote()
            findNavController().popBackStack()
        }
        binding.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.etTitleInput.addTextChangedListener(getTextWatcher())
        binding.etDateInput.addTextChangedListener(getTextWatcher())
        binding.etNoteInput.addTextChangedListener(getTextWatcher())

        viewModel.getNoteDataById(args.noteId)
    }

    private fun getTableInput() {
        val title = binding.etTitleInput.text.toString()
        val date = binding.etDateInput.text.toString()
        val note = binding.etNoteInput.text.toString()

        viewModel.updateNote(title, date, note)
    }

    private fun getTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                getTableInput()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}