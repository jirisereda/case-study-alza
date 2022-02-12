package com.case_study_alza.screen.categories

import androidx.lifecycle.viewModelScope
import com.case_study_alza.MainGraphDirections
import com.case_study_alza.core.EmptyArgs
import com.case_study_alza.core.Event
import com.case_study_alza.core.ScreenViewModel
import com.case_study_alza.services.ApiService
import com.case_study_alza.services.CategoryItem
import com.case_study_alza.services.FlowCategoriesApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import timber.log.Timber

data class CategoriesState(
    val categoryItems: List<CategoryItem> = emptyList(),
    val result: Int
)

class CategoriesViewModel : ScreenViewModel<CategoriesState, EmptyArgs>(
    CategoriesState(result = 9999)
) {
    object HelloEvent : Event

    override fun onArgumentsSet(screenArguments: EmptyArgs) {

        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        val service = Retrofit.Builder()
            .baseUrl("https://www.alza.cz/Services/RestService.svc/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ApiService::class.java)

        FlowCategoriesApi(service).getCategories()
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