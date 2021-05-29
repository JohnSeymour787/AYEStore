import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.html.ButtonFormEncType
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onSubmitFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*

private val scope = MainScope()


val ManageProducts = functionalComponent<RProps>
{ props ->
    val (productList, setProductList) = useState(emptyList<Product>())

    h2()
    {
        +"Manage Products"
    }

    val (ID, setID) = useState(-1)
    val (name, setName) = useState("")
    val (description, setDescription) = useState("")
    val (manufacturer, setManufacturer) = useState("")
    val (stockQuantity, setStockQuantity) = useState(0)
    val (price, setPrice) = useState(-1F)


    fun formSubmitted(product: Product)
    {
        scope.launch()
        {
            API.addProduct(product)
            setProductList(API.getProductList())
        }
    }


    val submitHandler: (Event) -> Unit =
        {
            it.preventDefault()

            formSubmitted(Product(ID = ID, name = name, description = description, manufacturer = manufacturer, stockQuantity = stockQuantity, price = price))

            setID(-1)
            setName("")
            setDescription("")
            setManufacturer("")
            setStockQuantity(0)
            setPrice(-1F)
        }


    form()
    {
        attrs.onSubmitFunction = submitHandler

        label()
        {
            attrs.htmlFor = "id"
            + "Unique Product ID:"
        }

        input(InputType.number)
        {
            attrs.id = "id"
            attrs.onChangeFunction =
                { event ->
                    val newValue = (event.target as? HTMLInputElement)?.value?.toIntOrNull() ?: -1
                    setID(newValue)
                }
            attrs.value = ID.toString()
        }

        br { }

        label()
        {
            attrs.htmlFor = "name"
            + "Product Name:"
        }

        input(InputType.text)
        {
            attrs.id = "name"
            attrs.onChangeFunction =
                { event ->
                    val newValue = (event.target as? HTMLInputElement)?.value ?: ""
                    setName(newValue)
                }

            attrs.value = name
        }

        br { }


        label()
        {
            attrs.htmlFor = "description"
            + "Description:"
        }

        input(InputType.text)
        {
            attrs.id = "description"
            attrs.onChangeFunction =
                { event ->
                    val newValue = (event.target as? HTMLInputElement)?.value ?: ""
                    setDescription(newValue)
                }
            attrs.value = description
        }

        br { }

        label()
        {
            attrs.htmlFor = "manufacturer"
            + "Product Manufacturer:"
        }

        input(InputType.text)
        {
            attrs.id = "manufacturer"
            attrs.onChangeFunction =
                { event ->
                    val newValue = (event.target as? HTMLInputElement)?.value ?: ""
                    setManufacturer(newValue)
                }
            attrs.value = manufacturer
        }

        br { }


        label()
        {
            attrs.htmlFor = "stockQuantity"
            + "Items in Stock:"
        }

        input(InputType.number)
        {
            attrs.id = "stockQuantity"
            attrs.onChangeFunction =
                { event ->
                    val newValue = (event.target as? HTMLInputElement)?.value?.toIntOrNull() ?: 0
                    setStockQuantity(newValue)
                }
            attrs.value = stockQuantity.toString()
        }

        br { }

        label()
        {
            attrs.htmlFor = "price"
            + "Current Price:"
        }

        input(InputType.number)
        {
            attrs.id = "price"
            attrs.onChangeFunction =
                { event ->
                    val newValue = (event.target as? HTMLInputElement)?.value?.toFloatOrNull() ?: 0F
                    setPrice(newValue)
                }
            attrs.value = price.toString()
        }

        br { }

        button(ButtonFormEncType.multipartFormData)
        {
            +"Create/Modify Product"
        }
    }

    div()
    {
        attrs.id = "messageDiv"
    }


    useEffect(dependencies = listOf())
    {
        scope.launch()
        {
            setProductList(API.getProductList())
        }
    }


    fun loadProductDetails(product: Product)
    {
        setID(product.ID)
        setName(product.name)
        setDescription(product.description)
        setManufacturer(product.manufacturer)
        setStockQuantity(product.stockQuantity)
        setPrice(product.price)
    }

    productList.sortedBy(Product::ID).forEach()
    { product ->
        child(ProductDisplay::class)
        {
            attrs.product = product
            attrs.onClickCallBack = ::loadProductDetails
        }

        br {  }
    }
}