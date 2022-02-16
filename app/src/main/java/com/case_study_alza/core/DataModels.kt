package com.case_study_alza.core

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavArgs
import androidx.navigation.NavDirections

object EmptyArgs : NavArgs

interface Event

data class NavigationEvent(@IdRes val action: Int, val args: Bundle? = null) : Event

fun NavDirections.toNavEvent(): NavigationEvent = NavigationEvent(actionId, arguments)
