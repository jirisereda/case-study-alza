package com.case_study_alza.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Sets up default event handler for View Model events
 */
fun <SM : ScreenViewModel<*, *>> Fragment.setupDefaultEventHandler(
    viewModel: SM, otherEventsHandler: (Event) -> Unit
) {
    parentFragmentManager.registerFragmentLifecycleCallbacks(
        object : FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                if (f != this@setupDefaultEventHandler) {
                    return
                }

                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.events.collect { event ->
                            when (event) {
                                is NavigationEvent -> findNavController().navigate(
                                    event.action,
                                    event.args
                                )
                                else -> otherEventsHandler(event)
                            }
                        }
                    }
                }
            }

            override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                super.onFragmentViewDestroyed(fm, f)

                if (f == this@setupDefaultEventHandler) {
                    parentFragmentManager.unregisterFragmentLifecycleCallbacks(this)
                }
            }
        },
        false
    )
}