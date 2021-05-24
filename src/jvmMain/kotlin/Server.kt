import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.*
import org.litote.kmongo.reactivestreams.KMongo
import com.mongodb.ConnectionString

fun main()
{
    //Server and DB setup
    val port = System.getenv("PORT")?.toInt() ?: 9090
    val connectionString: ConnectionString? = System.getenv("MONGODB_URI")?.let()
    {
        ConnectionString("$it?retryWrites=false")
    }

    val client = if (connectionString != null) KMongo.createClient(connectionString).coroutine else KMongo.createClient().coroutine
    val database = client.getDatabase(connectionString?.database ?: "AllYourElectronics")
    val userCollection = database.getCollection<Customer>("Users")

    //Run server
    embeddedServer(Netty, port)
    {
        install(ContentNegotiation)
        {
            json()
        }
        install(CORS)
        {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Delete)
            anyHost()
        }
        install(Compression)
        {
            gzip()
        }

        routing()
        {
            get("/")
            {
                call.respondText(this::class.java.classLoader.getResource("index.html")!!.readText(), ContentType.Text.Html)
            }

            get("/signup")
            {
                call.respondText(this::class.java.classLoader.getResource("signup.html")!!.readText(), ContentType.Text.Html)
            }

            route("/register")
            {
                get()
                {
                    call.respond(userCollection.find().toList())
                }
                post()
                {
                    val received = call.receive<Customer>()

                //    if (userCollection.)

                    userCollection.insertOne(received)
                    call.respond(HttpStatusCode.OK)
                }
            }

            static("/")
            {
                resources("")
            }
        }
    }.start(wait = true)
}