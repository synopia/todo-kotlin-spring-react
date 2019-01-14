package com.objectfab.todo.components

import com.objectfab.todo.VisibilityFilter
import com.objectfab.todo.containers.filterLink
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.p

class Footer : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        p {
            +"Show:"
            filterLink(VisibilityFilter.ShowAll, "All")
            filterLink(VisibilityFilter.ShowActive, "Active")
            filterLink(VisibilityFilter.ShowCompleted, "Completed")
        }
    }
}

fun RBuilder.todoFooter() = child(Footer::class) {}