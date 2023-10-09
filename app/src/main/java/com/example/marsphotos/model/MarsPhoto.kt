package com.example.marsphotos.model

import kotlinx.serialization.Serializable

//Esta anotación proviene de la biblioteca kotlinx.serialization y se utiliza para indicar que la clase
// MarsPhoto es serializable. En otras palabras, esta clase se puede convertir fácilmente en formato JSON u otros formatos seri
// alizables y viceversa. Esta anotación es útil cuando se trabaja con bibliotecas de serialización en Kotlin.
@Serializable
data class MarsPhoto(
    val id: String,  val img_src: String
)

//Esta línea define una clase de datos llamada MarsPhoto. Las clases de datos en Kotlin están diseñadas principalmente para contener datos y proporcionan automáticamente funcionalidad relacionada con la igualdad, el hash y la copia. En este caso, MarsPhoto tiene dos propiedades:
//
//id: Una cadena que representa el identificador único de la foto de Marte.
//img_src: Una cadena que representa la URL de la imagen de la foto de Marte.

