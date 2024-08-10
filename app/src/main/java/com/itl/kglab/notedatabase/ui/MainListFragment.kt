package com.itl.kglab.notedatabase.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itl.kglab.notedatabase.R
import com.itl.kglab.notedatabase.databinding.FragmentMainListBinding

class MainListFragment : Fragment() {

    private var _binding: FragmentMainListBinding? = null
    private val binding: FragmentMainListBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.btCreateNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainListFragment_to_editNoteFragment)
        }
    }

}