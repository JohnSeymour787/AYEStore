import kotlinx.browser.document
import react.dom.render
import react.child
import react.useState

fun main()
{
    document.bgColor = "8BC34A"

    document.getElementById("root")?.let()
    {
        render(it)
        {
            child(App)
            {
                attrs.storeState = "home"
            }
        }
    }

    document.getElementById("signup")?.let()
    {
        render(it)
        {
            child(SignUp::class) { }
        }
    }

    document.getElementById("browseCatalogue")?.let()
    {
        render(it)
        {
            child(SignUp::class) { }
        }
    }

    document.getElementById("manageProducts")?.let()
    {
        render(it)
        {
            child(ManageProducts) { }
        }
    }
}