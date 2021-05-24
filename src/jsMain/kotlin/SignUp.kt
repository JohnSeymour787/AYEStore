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
            InputComponent,
            props = jsObject()
            {
                onSubmit =
                { customer ->
                    println(customer.toString())
                    //val user = Customer(input.replace("!", ""), "lastname", 4, "test1", "test2", "test3", "test4")
                    scope.launch()
                    {
                        addUser(customer)
                        //setUserList(getUserList())
                    }
                }
            }
        )
    }
}