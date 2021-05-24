import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.*
import react.dom.*
import kotlinext.js.*
import kotlinx.html.js.*
import org.w3c.dom.events.Event
import react.router.dom.*
import react.router.dom.navLink

private val scope = MainScope()

external interface AppProps: RProps
{
    var storeState: String
}


val App = functionalComponent<AppProps>
{ props ->

        val (userList, setUserList) = useState(emptyList<Customer>())
        val (storeState, setStoreState) = useState(props.storeState)



/*        useEffect(dependencies = listOf())
        {
            scope.launch()
            {
              //  setUserList(getUserList())
            }
        }*/


        h1()
        {
            + "Welcome to All Your Electronics"
        }


        div()
        {
            a(href = "/signup")
            {
                +"Register"
            }

            br { }

            a(href = "/browse")
            {
                +"Browse Catalogue"
            }

            br { }

            a(href = "/manageProducts")
            {
                +"Manage Products"
            }
        }



/*        browserRouter()
        {
            switch()
            {
                route("/", Home::class, exact = true)

                route("/signup", SignUp::class)
            }
        }*/


/*        ul()
        {
           userList.sortedByDescending(Customer::firstName).forEach()
            { user ->
                li()
                {
                    key = user.toString()
                    + "[${user.firstName}] ${user.lastName}"
                }
            }*//*
        }*/
}