class CartItem(val product: Product)
{
    var quantitiy = 0
        private set

    val productID = product.ID

    var price = 0F
        get() = quantitiy * product.price
        private set

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