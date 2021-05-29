import kotlinx.serialization.Serializable

@Serializable
data class Product(var ID: Int,
              var stockQuantity: Int = 0,
              var name: String,
              var price: Float,
              var description: String,
              var manufacturer: String)
{
    var inStock = stockQuantity > 0
        get() = stockQuantity > 0
        private set
}