import kotlinext.js.jsObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.*
import react.dom.*

private val scope = MainScope()

@JsExport
class SignUp: RComponent<RProps, RState>()
{
    override fun RBuilder.render()
    {
        h2()
        {
            +"Sign Up"
        }

        child(
            RegisterInput,
            props = jsObject()
            {
                onSubmit =
                { customer ->
                    scope.launch()
                    {
                        API.addUser(customer)
                    }
                }
            }
        )
    }
}