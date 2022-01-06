import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.ReactHTML.div
import react.dom.ReactHTML.h1

fun main() {
    window.onload={
        render(document.getElementById("question")){
//            div{
//                attrs{
//                    className="row justify-content-md-center"
//                }
//                div{
//                    attrs{
//                        className="col-lg-5"
//                    }
//                    h1{
//                        +"OneHundred Poems"
//                    }
//                    child(Question::class){
//                        attrs{
//                            name="乱数を再生成"
//                        }
//                    }
//                }
//            }
            div{
                attrs{
                    className="row justify-content-md-center"
                }
                div{
                    attrs{
                        className="col-lg-5"
                    }
                    child(questionApp)
                }
            }
        }
    }
}
