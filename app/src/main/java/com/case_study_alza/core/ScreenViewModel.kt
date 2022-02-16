package com.case_study_alza.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class ScreenViewModel<State, ScreenArgs : NavArgs>(initialState: State) :
    ViewModel() {
    private val mutableState = MutableStateFlow(initialState)

    val currentState: State
        get() = mutableState.value

    val state: LiveData<State> = mutableState.asLiveData(viewModelScope.coroutineContext)

    private val _events = MutableSharedFlow<Event>()
    val events: Flow<Event> = _events.asSharedFlow()

    private lateinit var screenArguments: ScreenArgs

    protected fun State.next(nextState: State.() -> State) {
        mutableState.value = nextState()
    }

    protected fun nextEvent(event: Event) {
        viewModelScope.launch {
            _events.emit(event)
        }
    }

    protected fun NavDirections.navigate(): Unit = nextEvent(toNavEvent())

    fun setArguments(screenArguments: ScreenArgs) {
        if (!this::screenArguments.isInitialized) {
            this.screenArguments = screenArguments
            onArgumentsSet(screenArguments)
        }
    }

    protected open fun onArgumentsSet(screenArguments: ScreenArgs) {}
}