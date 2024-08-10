package com.itl.kglab.notedatabase.ui

import androidx.fragment.app.Fragment
import com.itl.kglab.notedatabase.ui.dialog.LoadingDialog

open class BaseFragment : Fragment() {

    private var loadingDialog: LoadingDialog? = null

    fun showLoading() {
        loadingDialog?.dismiss()
        loadingDialog = LoadingDialog()
        loadingDialog?.show(childFragmentManager, "loading")
    }

    fun dismissLoading() {
        loadingDialog?.dismiss()
    }
}