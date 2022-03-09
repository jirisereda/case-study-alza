package com.case_study_alza.screen.categories

import androidx.lifecycle.viewModelScope
import com.case_study_alza.MainGraphDirections
import com.case_study_alza.core.EmptyArgs
import com.case_study_alza.core.ScreenViewModel
import com.case_study_alza.core.loadingCall
import com.case_study_alza.services.ApiService
import com.case_study_alza.services.FlowApi
import com.case_study_alza.services.models.CategoryItem
import com.case_study_alza.ui.views.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

data class CategoriesState(
    val loadingState: LoadingState = LoadingState.Loading,
    val categoryItems: List<CategoryItem> = emptyList()
)

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val apiService: ApiService
) : ScreenViewModel<CategoriesState, EmptyArgs>(
    CategoriesState()
) {
    override fun onArgumentsSet(screenArguments: EmptyArgs) {

        FlowApi(apiService).getCategories()
            .loadingCall(
                { currentState.next { copy(loadingState = LoadingState.Loading) } },
                { currentState.next { copy(loadingState = LoadingState.Data) } }
            )
            .onEach {
                currentState.next { copy(categoryItems = it) }
            }
            .catch {
                Timber.e("$it")
                currentState.next { copy(loadingState = LoadingState.Error) }
            }
            .launchIn(viewModelScope)
    }

    fun selectCategory(id: Long) {
        MainGraphDirections.actionProductsScreen(id).navigate()
    }
}