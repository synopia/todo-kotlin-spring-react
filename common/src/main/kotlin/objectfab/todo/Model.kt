package com.objectfab.todo

import kotlinx.serialization.Serializable

@Serializable
data class TodoItem(val id: Int, val completed: Boolean, val text: String)