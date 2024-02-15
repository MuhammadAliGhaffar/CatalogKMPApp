import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.lighthousegames.logging.logging

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        AppContent(homeViewModel = HomeViewModel())

    }
}

@Composable
fun AppContent(homeViewModel: HomeViewModel) {
    val products = homeViewModel.products.collectAsState()

    logging().d("AliTag") { "products.value.toString()" }
    BoxWithConstraints {
        val scope = this
        val maxWidth = scope.maxWidth

        var cols = 2
        var modifier = Modifier.fillMaxWidth()
        if (maxWidth > 840.dp) {
            cols = 3
            modifier = Modifier.widthIn(max = 1000.dp)
        }


        val scrollState = rememberLazyGridState()

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(cols),
                state = scrollState,
                contentPadding = PaddingValues(16.dp)
            ) {
                items(items = products.value, key = { product -> product.id.toString() }) {product->
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        elevation = 2.dp
                    ) {
                        Column(verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            val painter = rememberImagePainter(url = product.image.toString())
                            Image(painter, modifier = Modifier.height(130.dp).padding(8.dp), contentDescription = product.desc)
                            Text(product.name, maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(16.dp).heightIn(min = 40.dp))
                        }

                    }
                }
            }

        }
    }
}