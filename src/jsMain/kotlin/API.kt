import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.browser.window
import io.ktor.http.*

object API
{
    private val endpoint = window.location.origin

    private val jsonClient = HttpClient()
    {
        install(JsonFeature) { serializer = KotlinxSerializer() }
    }

    suspend fun getUserList(): List<Customer>
    {
        return jsonClient.get(endpoint + User.PATH)
    }

    suspend fun getProductList(): List<Product>
    {
        return jsonClient.get(endpoint + Catalogue.PATH)
    }

    suspend fun addUser(toAdd: User): String
    {
        return jsonClient.post(endpoint + User.PATH)
        {
            contentType(ContentType.Application.Json)
            body = toAdd
        }
    }

    suspend fun addProduct(toAdd: Product): String
    {
        return jsonClient.post(endpoint + Catalogue.PATH)
        {
            contentType(ContentType.Application.Json)
            body = toAdd
        }
    }
}
