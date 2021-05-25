
class ShoppingCart(val shoppingList: MutableList<CartItem> = mutableListOf())
{
    val totalCost = shoppingList.fold(0F) { acc, cartItem -> acc + cartItem.price }
}