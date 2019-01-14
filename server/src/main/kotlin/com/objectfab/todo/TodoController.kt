package com.objectfab.todo

import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
class TodoController {
    var todos = listOf<TodoItem>(
            TodoItem(1, false, "ABC"),
            TodoItem(2, false, "xyz")
    )

    @GetMapping("/todos")
    fun all(): List<TodoItem> {
        return todos
    }

    @PostMapping("/todos")
    fun addTodo(@RequestBody todoItem: TodoItem) {
        todos += todoItem
    }

    @GetMapping("/todos/{id}")
    fun one(@PathVariable id: Int): TodoItem {
        return todos.first { it.id == id }
    }

    @PutMapping("/todos/{id}")
    fun replace(@RequestBody newTodoItem: TodoItem, @PathVariable id: Int): TodoItem {
        var replaced = false
        todos = todos.map {
            if (it.id == id) {
                replaced = true
                newTodoItem.copy(id = id)
            } else {
                it
            }
        }
        if (!replaced) {
            todos += newTodoItem.copy(id = id)
        }
        return newTodoItem
    }

    @DeleteMapping("/todos/{id}")
    fun delete(@PathVariable id: Int) {
        todos = todos.filter { it.id != id }
    }
}