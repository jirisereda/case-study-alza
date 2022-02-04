package com.case_study_alza.core

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavArgs
import androidx.navigation.NavDirections

public object EmptyArgs : NavArgs

public interface Event

public object EmptyEvent : Event

public data class NavigationEvent(@IdRes val action: Int, val args: Bundle? = null) : Event

public fun NavDirections.toNavEvent(): NavigationEvent = NavigationEvent(actionId, arguments)

public data class ErrorEvent(val title: String, val message: String) : Event

public object RetryEvent : Event
public object BackEvent : Event

public data class ScrollEvent(val positionX: Int, val positionY: Int) : Event

public data class OpenUrlEvent(val url: String) : Event

public data class ApiErrorEvent(
    val message: String
) : Event
