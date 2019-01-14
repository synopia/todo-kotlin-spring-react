package com.objectfab.todo

import com.objectfab.todo.components.todoFooter
import com.objectfab.todo.containers.visibleTodoList
import kotlinext.js.require
import kotlinext.js.requireAll
import react.dom.render
import react.redux.provider
import kotlin.browser.document

fun main(args: Array<String>) {
    requireAll(require.context(".", true, js("/\\.css$/")))

    store.dispatch(LoadTodos())
    render(document.getElementById("root")) {
        provider(store) {
            visibleTodoList()
            todoFooter()
        }
    }

}
