package com.example.marsphotos.network
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.model.NotaTarea
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
//Esta constante almacena la URL base del servidor al que se accede para obtener datos relacionados
// con fotos de Marte. En este caso, la URL base es "https://android-kotlin-fun-mars-server.appspot.com".
private const val BASE_URL = "https://4bff-2806-266-2400-9055-2ced-2b41-37f6-e4e9.ngrok-free.app/"


//Esta variable crea una instancia de Retrofit, que es una biblioteca ampliamente utilizada en Android para realizar solicitudes HTTP a servicios web. Retrofit simplifica la comunicación con servicios web y la conversión de respuestas en objetos utilizables.
//
//.addConverterFactory(...): Agrega un convertidor de datos al objeto Retrofit. En este caso, se está utilizando Json.asConverterFactory("application/json".toMediaType()) para convertir los datos JSON en objetos Kotlin usando la biblioteca de serialización kotlinx.serialization. Esto permite que las respuestas JSON se conviertan automáticamente en objetos MarsPhoto y viceversa.
//.baseUrl(BASE_URL): Establece la URL base que se utilizará para todas las solicitudes HTTP realizadas a través de Retrofit.
//.build(): Finaliza la configuración de Retrofit y crea una instancia de Retrofit que se utilizará para realizar solicitudes HTTP.

private val retrofit = Retrofit.Builder()
                        .addConverterFactory(
                            Json.asConverterFactory("application/json".toMediaType())
                        )
                        .baseUrl(BASE_URL)
                        .build()
//Esta es una interfaz de Retrofit que define los puntos finales (endpoints) para realizar solicitudes HTTP al servidor. En este caso, define un método llamado getPhotos() que se utiliza para obtener una lista de fotos de Marte. La anotación @GET("photos") indica que esta solicitud HTTP GET se realizará a la ruta "/photos" en la URL base.
//
//suspend fun getPhotos(): List<MarsPhoto>: Este método es suspendido, lo que significa que puede ser llamado de manera asincrónica y se utilizará para obtener una lista de objetos MarsPhoto del servidor.

interface MarsApiService {
    //@GET("photos")
    //suspend fun getPhotos() : List<MarsPhoto>
    @GET("api/NotasTareas")
    suspend fun getNotasTareas(): List<NotaTarea>
}

/*
object MarsApi {
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}
*/
