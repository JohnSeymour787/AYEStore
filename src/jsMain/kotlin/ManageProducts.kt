import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.html.ButtonFormEncType
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onSubmitFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLLIElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*

private val scope = MainScope()


external interface ManageState
{
    var userList: List<User>
}

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
        println(product.toString())

        scope.launch()
        {
            addProduct(product)
            setProductList(getProductList())
        }
    }


    val submitHandler: (Event) -> Unit =
        {
            it.preventDefault()
            val errors = listOf<String>()//validateFields(state.ID, state.name, state.description, state.manufacturer, state.stockQuantity, state.price)

            if (errors.isNotEmpty())
            {
                document.getElementById("messageDiv")?.let()
                { div ->
                    render(div)
                    {
                        ul()
                        {
                            errors.forEach()
                            { err ->
                                key = err
                                li()
                                {
                                    +err
                                }
                                br { }
                            }
                        }
                    }
                }
            }
            //If no validation errors
            else
            {
                formSubmitted(Product(ID = ID, name = name, description = description, manufacturer = manufacturer, stockQuantity = stockQuantity, price = price))

                //TODO() If successful display message
                //       document.getElementById("messageDiv")?.let()

                setID(-1)
                setName("")
                setDescription("")
                setManufacturer("")
                setStockQuantity(0)
                setPrice(-1F)
            }
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



/*        child(productInput)
        {
            attrs.onSubmit =
                { product ->
                    println(product.toString())
                    //val user = Customer(input.replace("!", ""), "lastname", 4, "test1", "test2", "test3", "test4")
                        scope.launch()
                    {
                        // addProduct(product)
                        //setUserList(getUserList())
                    }
                }
        }*/


/*            props = jsObject()
            {
                onSubmit =
                    { product ->
                        println(product.toString())
                        //val user = Customer(input.replace("!", ""), "lastname", 4, "test1", "test2", "test3", "test4")
*//*                        scope.launch()
                        {
                           // addProduct(product)
                            //setUserList(getUserList())
                        }*//*
                    }
            }*/



    useEffect(dependencies = listOf())
    {
        scope.launch()
        {
            setProductList(getProductList())
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