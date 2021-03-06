import kotlinx.browser.document
import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onSubmitFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.RProps
import react.dom.*
import react.functionalComponent
import react.useState

external interface InputProps: RProps
{
    var onSubmit: (Customer) -> Unit
}

private fun validateFields(firstName: String, lastName: String, age: Int, address: String, phone: String, email: String, password: String): List<String>
{
    val errorMessages = mutableListOf<String>()

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
    }

    if (password.isEmpty())
    {
        errorMessages.add("Password cannot be empty")
    }

    return errorMessages
}

val RegisterInput = functionalComponent<InputProps>
{   props ->

    val (firstName, setFirstName) = useState("")
    val (lastName, setLastName) = useState("")
    val (age, setAge) = useState(-1)
    val (address, setAddress) = useState("")
    val (phone, setPhone) = useState("")
    val (email, setEmail) = useState("")
    val (password, setPassword) = useState("")

    val submitHandler: (Event) -> Unit =
    {
        it.preventDefault()
        val errors = validateFields(firstName, lastName, age, address, phone, email, password)

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
            props.onSubmit(Customer(firstName, lastName, age, address, phone, email, password))

            setFirstName("")
            setLastName("")
            setAge(0)
            setAddress("")
            setPhone("")
            setEmail("")
            setPassword("")
        }
    }


    form()
    {
        attrs.onSubmitFunction = submitHandler

        label()
        {
            attrs.htmlFor = "firstName"
            + "First Name:"
        }

        input(InputType.text)
        {
            attrs.id = "firstName"
            attrs.onChangeFunction =
            { event ->
                val newValue = (event.target as? HTMLInputElement)?.value ?: ""
                setFirstName(newValue)
            }

            attrs.value = firstName
        }

        br { }

        label()
        {
            attrs.htmlFor = "lastName"
            + "Last Name:"
        }

        input(InputType.text)
        {
            attrs.id = "lastName"
            attrs.onChangeFunction =
            { event ->
                val newValue = (event.target as? HTMLInputElement)?.value ?: ""
                setLastName(newValue)
            }
            attrs.value = lastName
        }

        br { }

        label()
        {
            attrs.htmlFor = "age"
            + "Age:"
        }

        input(InputType.number)
        {
            attrs.id = "age"
            attrs.onChangeFunction =
            { event ->
                val newValue = (event.target as? HTMLInputElement)?.value?.toIntOrNull() ?: -1
                setAge(newValue)
            }
            attrs.value = age.toString()
        }

        br { }

        label()
        {
            attrs.htmlFor = "address"
            + "Address:"
        }

        input(InputType.text)
        {
            attrs.id = "address"
            attrs.onChangeFunction =
            { event ->
                val newValue = (event.target as? HTMLInputElement)?.value ?: ""
                setAddress(newValue)
            }
            attrs.value = address
        }

        br { }

        label()
        {
            attrs.htmlFor = "phone"
            + "Phone Number:"
        }

        input(InputType.text)
        {
            attrs.id = "phone"
            attrs.onChangeFunction =
            { event ->
                val newValue = (event.target as? HTMLInputElement)?.value ?: ""
                setPhone(newValue)
            }
            attrs.value = phone
        }

        br { }

        label()
        {
            attrs.htmlFor = "email"
            + "Email:"
        }

        input(InputType.email)
        {
            attrs.id = "email"
            attrs.onChangeFunction =
            { event ->
                val newValue = (event.target as? HTMLInputElement)?.value ?: ""
                setEmail(newValue)
            }
            attrs.value = email
        }

        br { }

        label()
        {
            attrs.htmlFor = "new-password"
            + "Password:"
        }

        input(InputType.password)
        {
            attrs.id = "new-password"
            attrs.onChangeFunction =
            { event ->
                val newValue = (event.target as? HTMLInputElement)?.value ?: ""
                setPassword(newValue)
            }
            attrs.value = password
        }

        br { }

        button(ButtonFormEncType.multipartFormData)
        {
            +"Create Account"
        }
    }

    div()
    {
        attrs.id = "messageDiv"
    }
}