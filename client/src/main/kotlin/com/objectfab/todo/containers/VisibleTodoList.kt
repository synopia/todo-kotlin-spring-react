package com.objectfab.todo.containers

import com.objectfab.todo.AppState
import com.objectfab.todo.TodoItem
import com.objectfab.todo.VisibilityFilter
import com.objectfab.todo.components.TodoList
import com.objectfab.todo.components.TodoListProps
import com.objectfab.todo.toggleTodoItem
import react.*
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface TodoListStateProps : RProps {
    var todos: List<TodoItem>
}

interface TodoListDispatchProps : RProps {
    var onTodoClick: (Int) -> Unit
}

private val mapStateToProps: TodoListProps.(AppState, TodoListStateProps) -> Unit = { state, props ->
    todos = when (state.visibilityFilter) {
        VisibilityFilter.ShowAll -> state.todos
        VisibilityFilter.ShowCompleted -> state.todos.filter { it.completed }
        VisibilityFilter.ShowActive -> state.todos.filter { !it.completed }
    }
}
private val mapDispatchToProps: TodoListDispatchProps.((RAction) -> WrapperAction, TodoListStateProps) -> Unit = { dispatch, props ->
    onTodoClick = { id -> toggleTodoItem(id) }
}

private val c = rConnect<AppState, RAction, WrapperAction, TodoListStateProps, TodoListProps, TodoListDispatchProps, TodoListProps>(mapStateToProps, mapDispatchToProps)(TodoList::class.js as RClass<TodoListProps>)

class VisibleTodoList(props: TodoListStateProps) : RComponent<TodoListStateProps, RState>(props) {
    override fun RBuilder.render() {
        c {
            attrs.todos = props.todos
        }
    }
}

fun RBuilder.visibleTodoList() = child(VisibleTodoList::class) {

}