class ShoppingCart(val shoppingList: MutableList<CartItem> = mutableListOf())
{
    var totalCost = 0F
        get() = shoppingList.fold(0F) { acc, cartItem -> acc + cartItem.price }
        private set

    fun addToCart(toAdd: CartItem)
    {
        shoppingList.find { o -> o.productID == toAdd.productID }?.setQuantity(toAdd.quantitiy)
        ?: run() //Does not exist in cart
        {
            shoppingList.add(toAdd)
        }
  }
}