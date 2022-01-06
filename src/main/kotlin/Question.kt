import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.ButtonType
import react.dom.InputType
import react.dom.ReactHTML.button
import react.dom.ReactHTML.div
import react.dom.ReactHTML.form
import react.dom.ReactHTML.h1
import react.dom.ReactHTML.input
import react.dom.label
import react.functionComponent
import react.useState

external interface QuestionProps: Props {
    var name: String
}
data class QuestionState(var name: String): State

class Question(props: QuestionProps): RComponent<QuestionProps, QuestionState>(props){
    init{
        state=QuestionState(props.name)
    }

    override fun RBuilder.render(){
        button{
            attrs{
                type=ButtonType.button
                label {
                    + state.name
                }
                className="btn btn-primary"
                onClick={ _ ->
                    setState(
                        QuestionState(name="押されたわ...")
                    )
                }
            }
        }
    }
}

data class Poem(
    var id: Long=0,
    var topContent: String = "",
    var underContent: String = "",
    var author: String = ""
)

var mockPoemList = listOf(Poem(topContent = "かくとだに"), Poem(topContent = "あけぬれば"))

val app = functionComponent<Props> {
    val (poemList, setPoemList) = useState(mockPoemList)
    val (inputText, setInputText) = useState("")

    div{
        form{
            attrs{
                onSubmit={
                    setPoemList(poemList.toMutableList().apply {
                        add(Poem(topContent = inputText))
                    })
                    setInputText("")
                    it.preventDefault()
                }
            }
            input{
                attrs{
                    type=InputType.text
                    value=inputText
                    onChange={
                        setInputText(it.target.value)
                    }
                }
            }
            input{
                attrs{
                    type=InputType.submit
                    value="提出"
                    disabled=inputText.isBlank()
                }
            }
        }
    }
}