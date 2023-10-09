package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


// Esta es una interfaz que define un contrato para proporcionar una instancia de
// MarsPhotosRepository. Las interfaces son una forma de definir un conjunto de métodos que deben ser implementados por las clases que las utilicen.
interface  AppContainer{
    val marsPhotosRepository: MarsPhotosRepository
}

//Esta es una clase que implementa la interfaz AppContainer. En su constructor, inicializa Retrofit para comunicarse con un servidor remoto.
class DefaultAppContainer : AppContainer {

    private val BASE_URL = "https://3994-2806-370-608b-6100-75cb-d1ce-eadb-5cc3.ngrok-free.app"
        //https://android-kotlin-fun-mars-server.appspot.com


    //BASE_URL define la URL base del servidor al que se accede para obtener datos.
    //retrofit es una instancia de Retrofit configurada con un convertidor JSON y la URL base.

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    //Esto crea una instancia de la interfaz MarsApiService utilizando Retrofit. La palabra clave by lazy significa que esta
    // instancia se creará solo cuando se acceda por primera vez, lo que mejora el rendimiento al evitar la creación innecesaria de objetos.

    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    // Esto proporciona una implementación concreta de MarsPhotosRepository, que utiliza NetworkMarsPhotosRepository.
    // Esta implementación utiliza retrofitService para comunicarse con el servidor y obtener fotos de Marte.

    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }


}
