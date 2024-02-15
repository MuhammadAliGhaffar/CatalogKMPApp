import apiClient.httpClient
import data.Product
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow

class HomeRepository {

    suspend fun getProductsApi(): List<Product> {
        val response = httpClient.get("https://run.mocky.io/v3/d91bdef5-5e78-492f-8df2-49ffaa96613c")
        return response.body()
    }

    fun getProducts() = flow {
        emit(getProductsApi())
    }
}