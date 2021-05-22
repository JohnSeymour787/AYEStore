import kotlinx.serialization.Serializable

@Serializable
abstract class User
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