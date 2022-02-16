package com.case_study_alza.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.loadingCall(
    loadingStart: suspend () -> Unit,
    loadingFinish: suspend () -> Unit
): Flow<T> =
    this
        .onStart { loadingStart() }
        .onCompletion { loadingFinish() }