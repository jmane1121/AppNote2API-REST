package com.example.marsphotos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

import com.example.marsphotos.R
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.model.NotaTarea
import com.example.marsphotos.ui.theme.MarsPhotosTheme


//Esta función @Composable representa la pantalla principal de la aplicación. Dependiendo del estado proporcionado (marsUiState), muestra una de las siguientes pantallas:
//
//LoadingScreen: Pantalla de carga cuando los datos están siendo cargados.
//PhotosGridScreen: Pantalla que muestra una cuadrícula de fotos de Marte cuando los datos se cargan con éxito.
//ErrorScreen: Pantalla de error que muestra un mensaje de error y un botón de reintento cuando ocurre un error al cargar los datos.


@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())

        is MarsUiState.Success -> PhotosGridScreen(photos = marsUiState.photos, modifier)

        is MarsUiState.Error -> ErrorScreen( retryAction,   modifier = modifier.fillMaxSize())
    }

}


//Esta función @Composable muestra una cuadrícula de fotos de Marte utilizando LazyVerticalGrid. Cada foto se muestra en un MarsPhotoCard. Se le pasan dos argumentos: photos, que es la lista de fotos de Marte para mostrar, y modifier, que se utiliza para modificar la apariencia del componente.


@Composable
fun PhotosGridScreen(photos: List<NotaTarea>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        //Revisar el Key
        items(items = photos /*, key = {it.id}*/ ) {
            photo ->
            NotaTareaCard(photo = photo,
                modifier = modifier.
                padding(4.dp).fillMaxWidth().aspectRatio(1.5f)
                )
        }
    }

}


//Esta función @Composable muestra una tarjeta que contiene una imagen de una foto de Marte.
// Utiliza la biblioteca Coil para cargar y mostrar la imagen de manera asíncrona. Se le pasa un objeto
// MarsPhoto y un modifier para personalizar la apariencia de la tarjeta.
//
//LoadingScreen: Esta función @Composable muestra una imagen de carga cuando los datos están siendo cargados.
//
//ErrorScreen: Esta función @Composable muestra una pantalla de error con un mensaje de error y un botón de reintento. Se le pasa una función retryAction que se ejecuta cuando se hace clic en el botón de reintento.
//
//ResultScreen: Esta función @Composable muestra una pantalla de resultados que muestra un texto, que puede ser el número de fotos recuperadas.


@Composable
fun NotaTareaCard(photo: NotaTarea, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = photo.titulo,
                //text = photo.fecha,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
    //    AsyncImage(
     //       model = coil.request.ImageRequest.Builder(context = LocalContext.current)
     //           .data(photo.img_src)
    //            .crossfade(true)
    //            .build(),
     //       error = painterResource(id = R.drawable.ic_broken_image),
    //        placeholder = painterResource(id = R.drawable.loading_img),
     //       contentDescription = stringResource(R.string.mars_photo),
     //       modifier = Modifier.fillMaxWidth()
    //    )




@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }

    }
}


/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    MarsPhotosTheme {
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}
