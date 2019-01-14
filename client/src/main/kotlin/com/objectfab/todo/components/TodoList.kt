package com.objectfab.todo.components

import com.objectfab.todo.TodoItem
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.ul


interface TodoListProps : RProps {
    var onTodoClick: (Int) -> Unit
    var todos: List<TodoItem>
}

class TodoList(props: TodoListProps) : RComponent<TodoListProps, RState>(props) {
    override fun RBuilder.render() {
        ul {
            props.todos.forEachIndexed { index, todoItem ->
                todo(todoItem.id, todoItem.text, todoItem.completed) {
                    props.onTodoClick(todoItem.id)
                }
            }
        }
    }
}

fun RBuilder.todoList(todos: List<TodoItem>, block: (Int) -> Unit) = child(TodoList::class) {
    attrs.todos = todos
    attrs.onTodoClick = block
}