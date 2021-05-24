import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.br
import react.dom.button
import react.dom.div
import react.dom.link
import react.router.dom.browserRouter

external interface HomeProps: RProps
{
    var signUp: (Event) -> Unit
    var catalogue: (Event) -> Unit
    var manageProducts: (Event) -> Unit
}


@JsExport
class Home: RComponent<RProps, RState>()
{
    override fun RBuilder.render()
    {
        div()
        {
            button()
            {
                +"Sign Up"
                //  attrs.onClickFunction = props.signUp
                link("/") {}
            }
        }




        br { }

        button()
        {
            + "Browse Catalogue"
   //         attrs.onClickFunction = props.catalogue
        }

        br { }

        button()
        {
            + "Manage Products"
    //        attrs.onClickFunction = props.manageProducts
        }
    }
}