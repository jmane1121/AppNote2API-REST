/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.data.AppContainer
import com.example.marsphotos.data.DefaultAppContainer
import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.data.NetworkMarsPhotosRepository
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.model.NotaTarea
import kotlinx.coroutines.launch
import java.io.IOException

//Esta es una interfaz sellada que define diferentes estados posibles para la interfaz de usuario de la aplicación relacionada con las fotos de Marte. Los estados incluyen Success (éxito), Error (error) y Loading (carga). Cada estado puede llevar datos adicionales, como una lista de fotos en el caso de Success.
sealed interface MarsUiState {
    data class Success(val photos: List< NotaTarea>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

//Esta es una clase ViewModel de Android que gestiona la lógica y el estado de la pantalla relacionada con las fotos de Marte. Algunas de las funcionalidades clave incluyen:
//
//marsUiState: Una propiedad mutableStateOf que almacena y actualiza el estado actual de la interfaz de usuario. Inicialmente, se establece en Loading.
//init: El constructor de la clase ViewModel que llama automáticamente a getMarsPhotos() cuando se crea una instancia del ViewModel.
//getMarsPhotos(): Una función que utiliza un viewModelScope para realizar una solicitud asincrónica para obtener fotos de Marte utilizando marsPhotosRepository. Dependiendo de si la solicitud tiene éxito o falla, actualiza marsUiState con los datos correspondientes (Success o Error).
class MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    /*var marsUiState: String by mutableStateOf("")
        private set*/


    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set


    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        //Editar el metodo
        getNotaTarea()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getNotaTarea() {
            //marsUiState = "Set the Mars API status response here!"
        viewModelScope.launch {
            marsUiState =  try {
                /*val listResult = MarsApi.retrofitService.getPhotos()
                listResult.forEach { Log.d("${it.id}", "${it.img_src}" ) }*/
                //val marsPhotosRepository = NetworkMarsPhotosRepository(retrofitService)
                //val marsPhotosRepository = DefaultAppContainer().marsPhotosRepository

                //Aqui se cambia el metodo
                val listResult = marsPhotosRepository.getNotasTarea()
                MarsUiState.Success(
                    listResult
                )
            } catch (e: IOException) {
                MarsUiState.Error
            }

        }

    }
    //Define un objeto compañero llamado Factory que proporciona una fábrica (ViewModelProvider.Factory) para
    // crear instancias de MarsViewModel. Utiliza viewModelFactory para inicializar el ViewModel con una instancia
    // de marsPhotosRepository.

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }

}
