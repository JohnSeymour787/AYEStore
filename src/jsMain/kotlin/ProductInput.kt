import kotlinx.browser.document
import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onSubmitFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*

external interface ProductProps: RProps
{
    var onSubmit: (Product) -> Unit
}

external interface ProductState: RState
{
    var toUpdate: Product?

    var ID: Int
    var name: String
    var description: String
    var manufacturer: String
    var stockQuantity: Int
    var price: Float

}

private fun validateFields(ID: Int, name: String, description: String, manufacturer: String, stockQuantity: Int, price: Float) : List<String>
{
    val errorMessages = mutableListOf<String>()
/*
    if (firstName.isEmpty())
    {
        errorMessages.add("First name cannot be empty")
    }

    if (lastName.isEmpty())
    {
        errorMessages.add("Last name cannot be empty")
    }

    if (age < 1)
    {
        errorMessages.add("Must be older than 1")
    }

    if (address.isEmpty())
    {
        errorMessages.add("Address cannot be empty")
    }

    if (phone.isEmpty())
    {
        errorMessages.add("Phone cannot be empty")
    }

    if (email.isEmpty())
    {
        errorMessages.add("Email cannot be empty")
    }*/


    return errorMessages
}

class ProductInput: RComponent<ProductProps, ProductState>()
{
    override fun RBuilder.render()
    {
        state.ID = -1
        state.name = ""
        state.description = ""
        state.manufacturer = ""
        state.stockQuantity = 0
        state.price = -1F

        state.toUpdate?.let()
        {
            state.ID = it.ID
            state.name = it.name
            state.description = it.description
            state.manufacturer = it.manufacturer
            state.stockQuantity = it.stockQuantity
            state.price = it.price
        }

        val submitHandler: (Event) -> Unit =
            {
                it.preventDefault()
                val errors = validateFields(state.ID, state.name, state.description, state.manufacturer, state.stockQuantity, state.price)

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
                    props.onSubmit(Product(ID = state.ID, name = state.name, description = state.description, manufacturer = state.manufacturer, stockQuantity = state.stockQuantity, price = state.price))

                    //TODO() If successful display message
                    //       document.getElementById("messageDiv")?.let()

                    state.ID = -1
                    state.name = ""
                    state.description = ""
                    state.manufacturer = ""
                    state.stockQuantity = 0
                    state.price = -1F
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
                        state.ID = (newValue)
                        setState(state)
                    }
                attrs.value = state.ID.toString()
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
                        state.name = (newValue)
                    }

                attrs.value = state.name
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
                        state.description = (newValue)
                    }
                attrs.value = state.description
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
                        state.manufacturer = (newValue)
                    }
                attrs.value = state.manufacturer
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
                        state.stockQuantity = (newValue)
                    }
                attrs.value = state.stockQuantity.toString()
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
                        state.price = (newValue)
                    }
                attrs.value = state.price.toString()
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

    }
}