package com.case_study_alza.screen.categories

import androidx.lifecycle.viewModelScope
import com.case_study_alza.MainGraphDirections
import com.case_study_alza.core.EmptyArgs
import com.case_study_alza.core.Event
import com.case_study_alza.core.ScreenViewModel
import com.case_study_alza.services.ApiService
import com.case_study_alza.services.CategoryItem
import com.case_study_alza.services.FlowApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

data class CategoriesState(
    val categoryItems: List<CategoryItem> = emptyList()
)

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val apiService: ApiService
) : ScreenViewModel<CategoriesState, EmptyArgs>(
    CategoriesState()
) {
    object HelloEvent : Event

    override fun onArgumentsSet(screenArguments: EmptyArgs) {

        FlowApi(apiService).getCategories()
            .onEach {
                Timber.d("DEBUG FlowCategoriesApi $it")
                currentState.next { copy(categoryItems = it) }
                Timber.d("DEBUG categoryItems $state.categoryItems")
            }
            .catch {
                Timber.e("DEBUG FlowCategoriesApi Error $it.message")
            }
            .launchIn(viewModelScope)
    }

    fun selectCategory(id: Long) {
        //nextEvent(HelloEvent)
        MainGraphDirections.actionProductsScreen(id).navigate()
    }
}