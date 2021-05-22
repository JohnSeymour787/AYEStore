import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.*
import react.dom.*
import kotlinext.js.*
import kotlinx.html.js.*

private val scope = MainScope()

val App = functionalComponent<RProps>
{ _ ->
    val (userList, setUserList) = useState(emptyList<Customer>())

    useEffect(dependencies = listOf())
    {
        scope.launch()
        {
            println("Getting user list")
            setUserList(getUserList())
        }
    }

    h1()
    {
        + "This is my H1"
    }

    ul()
    {
        println("User list size ${userList.size}")
        userList.sortedByDescending(Customer::firstName).forEach()
        { user ->
            li()
            {
                key = user.toString()
                + "[${user.firstName}] ${user.lastName}"
            }
        }
    }

    child(
        InputComponent,
        props = jsObject()
        {
            onSubmit =
            { input ->
                val user = Customer(input.replace("!", ""), "lastname", 4, "test1", "test2", "test3", "test4")
                scope.launch()
                {
                    addUser(user)
                    setUserList(getUserList())
                }
            }
        }
    )
}