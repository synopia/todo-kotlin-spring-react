package com.objectfab.todo

import kotlinx.coroutines.await
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.list
import org.w3c.fetch.RequestInit
import redux.MiddlewareApi
import redux.RAction
import redux.WrapperAction
import kotlin.browser.window
import kotlin.js.json

enum class VisibilityFilter {
    ShowAll,
    ShowCompleted,
    ShowActive
}

data class AddTodo(val text: String) : RAction
data class ToggleTodo(val id: Int) : RAction
data class SetVisibilityFilter(val filter: VisibilityFilter) : RAction
data class TodoItems(val todos: List<TodoItem>) : RAction


class LoadTodos : AsyncAction<AppState, RAction, WrapperAction> {
    override suspend fun run(api: MiddlewareApi<AppState, RAction, WrapperAction>): WrapperAction {
        val todos = request("get", "http://localhost:8080/todos", null, TodoItem.serializer().list)
        return api.dispatch(TodoItems(todos))
    }
}


fun toggleTodoItem(id: Int) {
    val action = ToggleTodo(id)
    store.dispatch(action)
}

suspend fun <T> request(method: String, url: String, body: dynamic, serializer: KSerializer<T>): T {
    val response = window.fetch(url, object : RequestInit {
        override var method: String? = method
        override var body: dynamic = body
        override var headers: dynamic = json("Accept" to "application/json")
    }).await()

    val text = response.text().await()
    return JSON.parse(serializer, text)
}