package com.objectfab.todo.components

import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import react.dom.span

interface LinkProps : RProps {
    var active: Boolean
    var text: String
    var onClick: () -> Unit
}

class Link(props: LinkProps) : RComponent<LinkProps, RState>(props) {
    override fun RBuilder.render() {
        if (props.active) {
            span {
                +props.text
            }
        } else {
            a("") {
                attrs {
                    onClickFunction = {
                        it.preventDefault()
                        props.onClick()
                    }
                }
                +props.text
            }
        }
    }
}

