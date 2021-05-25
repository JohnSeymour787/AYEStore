
import io.ktor.util.date.*

class Receipt(val purchases: List<CartItem>, val paymentMethod: PaymentMethod, val date: GMTDate)
{
    val totalPrice = purchases.fold(0F) { acc, cartItem -> acc + (cartItem.quantitiy * cartItem.product.price) }
}