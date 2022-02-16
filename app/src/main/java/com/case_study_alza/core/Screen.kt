package com.case_study_alza.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.ViewDataBinding

import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs

abstract class Screen<
        SM : ScreenViewModel<*, ScreenArgs>, B : ViewDataBinding, ScreenArgs : NavArgs
        > : Fragment() {
    abstract val viewModel: SM
    protected abstract val layoutId: Int
    protected abstract val dataBindViewModel: B.(SM) -> Unit
    protected abstract val screenArgs: ScreenArgs

    private var binding: B? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflate<B>(inflater, layoutId, container, false).apply {
        viewModel.setArguments(screenArgs)
        setupDefaultEventHandler(viewModel, ::handleEvent)

        binding = this
        lifecycleOwner = viewLifecycleOwner
        dataBindViewModel(viewModel)
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    protected open fun setupViews() {}

    protected open fun handleEvent(event: Event) {}

    protected fun requireBinding(): B = requireNotNull(binding)
}
