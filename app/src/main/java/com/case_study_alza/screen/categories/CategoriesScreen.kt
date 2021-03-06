package com.case_study_alza.screen.categories

import androidx.fragment.app.viewModels
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import com.case_study_alza.R
import com.case_study_alza.core.EmptyArgs
import com.case_study_alza.core.Screen
import com.case_study_alza.databinding.CategoriesScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesScreen : Screen<CategoriesViewModel, CategoriesScreenBinding, EmptyArgs>() {
    override val viewModel: CategoriesViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.categories_screen
    override val dataBindViewModel: CategoriesScreenBinding.(CategoriesViewModel) -> Unit = {
        adapter = CategoriesAdapter(
            this@CategoriesScreen.viewLifecycleOwner,
            it.state.map { state -> state.categoryItems }.asFlow(),
            it
        )
        viewModel = it
    }
    override val screenArgs = EmptyArgs
}


