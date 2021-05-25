package models

class CartItem(val product: Product)
{
    var quantitiy = 0

    val productID = product.ID

    val price = quantitiy * product.price

    fun setQuantity(newAmount: Int): Boolean
    {
        return if (newAmount > product.stockQuantity)
        {
            false
        }
        else
        {
            quantitiy = newAmount
            product.stockQuantity -= newAmount
            true
        }
    }
}