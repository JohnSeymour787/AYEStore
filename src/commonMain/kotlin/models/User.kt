import kotlinx.serialization.Serializable

@Serializable
sealed class User
{
    abstract val firstName: String
    abstract val lastName: String
    abstract val age: Int
    abstract val address: String
    abstract val phone: String
    abstract val email: String
    abstract val password: String

    companion object
    {
        const val path = "/register"
    }
}

@Serializable
class Customer(override val firstName: String,
               override val lastName: String,
               override val age: Int,
               override val address: String,
               override val phone: String,
               override val email: String,
               override val password: String) : User()
{
}