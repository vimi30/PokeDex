package com.example.pokedex.data.network.response

import com.example.pokedex.data.network.error.Error

sealed interface Response<out D, out E: com.example.pokedex.data.network.error.Error> {
    data class Success<out D>(val data: D):
        com.example.pokedex.data.network.response.Response<D, Nothing>
    data class Error<out E: com.example.pokedex.data.network.error.Error>(val error: E):
        com.example.pokedex.data.network.response.Response<Nothing, E>
}

inline fun <T, E: com.example.pokedex.data.network.error.Error, R> com.example.pokedex.data.network.response.Response<T, E>.map(map: (T) -> R): com.example.pokedex.data.network.response.Response<R, E> {
    return when(this) {
        is com.example.pokedex.data.network.response.Response.Error -> _root_ide_package_.com.example.pokedex.data.network.response.Response.Error(
            error
        )
        is _root_ide_package_.com.example.pokedex.data.network.response.Response.Success -> _root_ide_package_.com.example.pokedex.data.network.response.Response.Success(
            map(data)
        )
    }
}

fun <T, E: com.example.pokedex.data.network.error.Error> _root_ide_package_.com.example.pokedex.data.network.response.Response<T, E>.asEmptyDataResult(): _root_ide_package_.com.example.pokedex.data.network.response.EmptyResponse<E> {
    return map {  }
}

inline fun <T, E: com.example.pokedex.data.network.error.Error> _root_ide_package_.com.example.pokedex.data.network.response.Response<T, E>.onSuccess(action: (T) -> Unit): _root_ide_package_.com.example.pokedex.data.network.response.Response<T, E> {
    return when(this) {
        is _root_ide_package_.com.example.pokedex.data.network.response.Response.Error -> this
        is _root_ide_package_.com.example.pokedex.data.network.response.Response.Success -> {
            action(data)
            this
        }
    }
}
inline fun <T, E: com.example.pokedex.data.network.error.Error> _root_ide_package_.com.example.pokedex.data.network.response.Response<T, E>.onError(action: (E) -> Unit): _root_ide_package_.com.example.pokedex.data.network.response.Response<T, E> {
    return when(this) {
        is _root_ide_package_.com.example.pokedex.data.network.response.Response.Error -> {
            action(error)
            this
        }
        is _root_ide_package_.com.example.pokedex.data.network.response.Response.Success -> this
    }
}

typealias EmptyResponse<E> = _root_ide_package_.com.example.pokedex.data.network.response.Response<Unit, E>