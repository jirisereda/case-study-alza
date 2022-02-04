package com.case_study_alza.core

import android.app.AlertDialog
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
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
public fun <SM : ScreenViewModel<*, *>> Fragment.setupDefaultEventHandler(
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
                                is OpenUrlEvent -> startActivity(
                                    Intent(
                                        ACTION_VIEW,
                                        Uri.parse(event.url)
                                    )
                                )
                                is BackEvent -> findNavController().popBackStack()
                                is ErrorEvent -> handleError(event)
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

            private fun handleError(errorEvent: ErrorEvent) {
                AlertDialog.Builder(requireContext())
                    .setTitle(errorEvent.title)
                    .setMessage(errorEvent.message)
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
            }
        },
        false
    )
}