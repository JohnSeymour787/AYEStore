import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.browser.window
import io.ktor.http.*

val endpoint = window.location.origin

val jsonClient = HttpClient()
{
    install(JsonFeature) { serializer = KotlinxSerializer() }
}

suspend fun getUserList(): List<Customer>
{
    return jsonClient.get(endpoint + User.path)
}

suspend fun addUser(toAdd: User): String
{
    return jsonClient.post(endpoint + User.path)
    {
        contentType(ContentType.Application.Json)
        body = toAdd
    }
}