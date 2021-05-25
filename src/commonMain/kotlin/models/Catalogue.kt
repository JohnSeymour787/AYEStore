
class Catalogue(productTemp: Set<Product> = setOf(),
                var catalogueName: String,
                var catalogueDescription: String)
{
    val productList: MutableMap<Int, Product> = mutableMapOf()

    init
    {
        productTemp.forEach()
        {
            productList[it.ID] = it
        }
    }

    fun getProduct(ID: Int): Product? = productList[ID]

    companion object
    {
        const val path = "/catalogue"
    }
}