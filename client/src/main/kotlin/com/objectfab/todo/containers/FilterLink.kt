package com.objectfab.todo.containers

import com.objectfab.todo.AppState
import com.objectfab.todo.SetVisibilityFilter
import com.objectfab.todo.VisibilityFilter
import com.objectfab.todo.components.Link
import com.objectfab.todo.components.LinkProps
import react.*
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface StateProps : RProps {
    var filter: VisibilityFilter
    var text: String

}

interface DispatchProps : RProps {
    var onClick: () -> Unit
}

private val mapStateToProps: LinkProps.(AppState, StateProps) -> Unit = { state, props ->
    active = props.filter == state.visibilityFilter
    text = props.text
}
private val mapDispatchToProps: DispatchProps.((RAction) -> WrapperAction, StateProps) -> Unit = { dispatch, props ->
    onClick = { dispatch(SetVisibilityFilter(props.filter)) }
}
private val c = rConnect<AppState, RAction, WrapperAction, StateProps, LinkProps, DispatchProps, LinkProps>(mapStateToProps, mapDispatchToProps)(Link::class.js as RClass<LinkProps>)

class FilterLink(props: StateProps) : RComponent<StateProps, RState>(props) {
    override fun RBuilder.render() {
        c() {
            attrs.filter = props.filter
            attrs.text = props.text
        }
    }
}

fun RBuilder.filterLink(filter: VisibilityFilter, text: String) = child(FilterLink::class) {
    attrs.filter = filter
    attrs.text = text
}