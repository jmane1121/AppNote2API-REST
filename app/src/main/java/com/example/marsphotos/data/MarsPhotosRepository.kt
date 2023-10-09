package com.example.marsphotos.data

import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.model.NotaTarea

import com.example.marsphotos.network.MarsApiService

//Esta interfaz define un contrato para acceder a fotos de Marte. Contiene un solo método llamado
// getMarsPhotos() que es suspendido, lo que significa que puede ser llamado de manera asincrónica.
interface MarsPhotosRepository {
   // suspend fun getMarsPhotos(): List<MarsPhoto>
    suspend fun getNotasTarea(): List<NotaTarea>

}

//Esta clase implementa la interfaz MarsPhotosRepository, lo que significa que debe proporcionar una implementación concreta del método getMarsPhotos().
//Recibe una instancia de MarsApiService como dependencia en su constructor. Esto indica que esta implementación utilizará un servicio de API para obtener fotos de Marte.
class NetworkMarsPhotosRepository(private val marsApiService: MarsApiService) : MarsPhotosRepository{
    /*override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return  MarsApi.retrofitService.getPhotos()
    }*/

    //utiliza el servicio marsApiService para obtener fotos de Marte. La llamada a marsApiService.getPhotos() es suspendida y espera una respuesta, lo que significa
    // que se utiliza de manera asincrónica para no bloquear el hilo principal de la aplicación.
    //override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
    override suspend fun getNotasTarea(): List<NotaTarea> = marsApiService.getNotasTareas()
}



