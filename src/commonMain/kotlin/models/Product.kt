package models

data class Product(var ID: Int,
              var stockQuantity: Int = 0,
              var name: String,
              var price: Float,
              var description: String,
              var manufacturer: String)
{
    val inStock = stockQuantity > 0
}