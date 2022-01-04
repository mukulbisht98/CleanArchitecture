package com.xxmukulxx.notes.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    private lateinit var binding: ViewDataBinding
    private var activity: BaseActivity? = null

    /**
     * Called from onViewCreated () Function
     */
    abstract fun onCreateView()

    abstract val layoutResId: Int

    protected fun getBinding(): ViewDataBinding {
        return binding
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as BaseActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    protected fun showLoading() {
        activity?.showProgress()
    }

    protected fun hideLoading() {
        activity?.hideProgress()
    }

    protected fun showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
        activity?.showToast(message, length)
    }


    protected fun handleApiError(errorCode: Int?, errorMessage: String?) {
    }
}