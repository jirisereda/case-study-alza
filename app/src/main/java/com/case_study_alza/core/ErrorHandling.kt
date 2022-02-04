package com.case_study_alza.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import timber.log.Timber

public fun Throwable.toErrorEvent(): ErrorEvent = ErrorEvent("", localizedMessage ?: "")

public fun <T> Flow<T>.handleErrors(action: suspend FlowCollector<T>.(Throwable) -> Unit): Flow<T> =
    catch {
        Timber.e(it)
        action(it)
    }
public fun <T> Flow<T>.reportErrors(): Flow<T> = handleErrors { /* Nothing to do */ }