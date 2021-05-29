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
    val catalogCollection = database.getCollection<Product>("Catalogue")

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

            get("/browseCatalogue")
            {
                call.respondText(this::class.java.classLoader.getResource("browseCatalogue.html")!!.readText(), ContentType.Text.Html)
            }

            get("/manageProducts")
            {
                call.respondText(this::class.java.classLoader.getResource("manageProducts.html")!!.readText(), ContentType.Text.Html)
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
                    //TODO() Checking if email does not already exist
                    //    if (userCollection.)
                    userCollection.insertOne(received)
                    call.respond("Cake")
                }
            }

            route(Catalogue.PATH)
            {
                get()
                {
                    call.respond(catalogCollection.find().toList())
                }
                post()
                {
                    val received = call.receive<Product>()

                    catalogCollection.findOne("{ID:'${received.ID}'}")?.let()
                    {
                        catalogCollection.updateOne("{ID:'${received.ID}'}", setValue(Product::name, received.name))
                        catalogCollection.updateOne("{ID:'${received.ID}'}", setValue(Product::manufacturer, received.manufacturer))
                        catalogCollection.updateOne("{ID:'${received.ID}'}", setValue(Product::stockQuantity, received.stockQuantity))
                        catalogCollection.updateOne("{ID:'${received.ID}'}", setValue(Product::price, received.price))
                        catalogCollection.updateOne("{ID:'${received.ID}'}", setValue(Product::description, received.description))

                        call.respond("Updated new catalogue")
                    }?: run()
                    {
                        catalogCollection.insertOne(received)
                        call.respond("Inserted new catalogue")
                    }
                }
            }

            static("/")
            {
                resources("")
            }
        }
    }.start(wait = true)
}