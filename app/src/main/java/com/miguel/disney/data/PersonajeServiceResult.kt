package com.miguel.disney.data

import com.miguel.disney.data.response.ListPersonajeResponse

sealed class PersonajeServiceResult<T>(data: T? = null, error: Exception? = null){
    data class Success<T>(val data: T): PersonajeServiceResult<T>(data,null)
    data class Error<T>(val error:Exception): PersonajeServiceResult<T>(null, error)
}
