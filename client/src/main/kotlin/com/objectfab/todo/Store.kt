package com.objectfab.todo

import react.RState
import redux.*

data class AppState(val visibilityFilter: VisibilityFilter, val todos: List<TodoItem>) : RState

/**
 * Maps (old state + action) -> new state
 * this code runs in sync (blocking)
 */
fun todoReducer(state: AppState, action: RAction) = when (action) {
    is SetVisibilityFilter -> {
        state.copy(visibilityFilter = action.filter)
    }
    is ToggleTodo -> {
        state.copy(todos = state.todos.map {
            if (action.id == it.id) {
                it.copy(completed = !it.completed)
            } else {
                it
            }
        })
    }
    is TodoItems -> {
        state.copy(todos = action.todos)
    }
    else -> state
}


val store = createStore(::todoReducer, AppState(VisibilityFilter.ShowAll, emptyList()), compose(applyMiddleware(::asyncRedux), rEnhancer()))
