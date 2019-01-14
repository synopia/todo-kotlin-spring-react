package com.objectfab.todo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import redux.MiddlewareApi
import redux.RAction
import redux.WrapperAction

/**
 * Async actions, wraps code that runs an action and calls api.dispatch when ready
 */

interface AsyncAction<S, A, R> : RAction {
    suspend fun run(api: MiddlewareApi<S, A, R>): R
}

fun asyncRedux(api: MiddlewareApi<AppState, RAction, WrapperAction>): ((RAction) -> WrapperAction) -> (RAction) -> WrapperAction {
    return { next ->
        {
            if (it is AsyncAction<*, *, *>) {
                GlobalScope.launch {
                    (it as AsyncAction<AppState, RAction, WrapperAction>).run(api)
                }
            }
            next(it)
        }
    }
}
