import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*

external interface ProductInterface: RProps
{
    var product: Product
    var onClickCallBack: (Product) -> Unit
}

class ProductDisplay: RComponent<ProductInterface, RState>()
{
    override fun RBuilder.render()
    {
        div()
        {
            table()
            {
                attrs.onClickFunction =
                {
                    props.onClickCallBack(props.product)
                }
                th()
                {
                    +"ID: ${props.product.ID} - ${props.product.name}"
                }

                tr()
                {
                    td()
                    {
                        +props.product.description
                    }

                    td()
                    {
                        +"Manufacturer: ${props.product.manufacturer}"
                    }
                }

                tr()
                {
                    td()
                    {
                        +"$${props.product.price}"
                    }

                    td()
                    {
                        +"Stock: ${props.product.stockQuantity}"
                    }
                }
            }
        }
    }
}