import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    window.onload={
        render(document.getElementById("header")) {
            child(Welcome::class) {
                attrs {
                    name="Kotlin/JS"
                }
            }
        }
        render(document.getElementById("question")){
            child(Question::class){
                attrs{
                    name="押してみて"
                }
            }
            child(app)
        }
    }
}
