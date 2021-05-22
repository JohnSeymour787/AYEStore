import kotlinx.serialization.Serializable

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