import react.*
import react.dom.*

external interface AppProps: RProps
{
    var storeState: String
}


val App = functionalComponent<AppProps>
{ props ->

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

        a(href = "/browseCatalogue")
        {
            +"Browse Catalogue"
        }

        br { }

        a(href = "/manageProducts")
        {
            +"Manage Products"
        }
    }
}