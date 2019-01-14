package com.objectfab.todo.components

import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.TextDecorationLine
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import styled.css
import styled.styledLi

interface TodoProps : RProps {
    var onClick: (Event) -> Unit
    var completed: Boolean
    var text: String
}

class Todo(props: TodoProps) : RComponent<TodoProps, RState>(props) {
    override fun RBuilder.render() {
        styledLi {
            css {
                textDecoration = if (props.completed) TextDecoration(setOf(TextDecorationLine.lineThrough)) else TextDecoration(setOf())
            }
            attrs {
                onClickFunction = props.onClick

            }
            +props.text
        }
    }
}

fun RBuilder.todo(id: Int, text: String, completed: Boolean = false, block: (Event) -> Unit = {}) = child(Todo::class) {
    attrs.key = id.toString()
    attrs.completed = completed
    attrs.text = text
    attrs.onClick = block
}