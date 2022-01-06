import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.ButtonType
import react.dom.ReactHTML.button
import react.dom.ReactHTML.table
import react.dom.ReactHTML.tbody
import react.dom.ReactHTML.td
import react.dom.ReactHTML.th
import react.dom.ReactHTML.thead
import react.dom.ReactHTML.tr
import react.dom.label
import react.functionComponent
import kotlin.math.abs
import kotlin.random.Random

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
//                    setState(
//                        QuestionState(name="押されたわ...")
//                    )

                }
            }
        }
    }
}

val test = functionComponent<Props> {
    table {
        attrs{
            className="table table-sm table-dark table-striped container-sm table-bordered"
        }
        thead{
            tr{
                th{
                    attrs{
                        scope="col-sm-1"
                    }
                    +"#"
                }
                th{
                    attrs{
                        scope="col-sm-auto"
                    }
                    +"Generated Value"
                }
            }
        }
        tbody{
            val numbers = getRandomList()
            for(i in numbers.indices){
                tr{
                    th{
                        attrs{
                            scope="row text-center"
                        }
                        +"${i + 1}"
                    }
                    td{
                        +"${numbers[i]}"
                    }
                }
            }
        }
    }
}

fun getRandomList(count: Int=10, duplicate: Boolean=true): IntArray{
//    val poemList: ArrayList<Poem> = arrayListOf()
//    var data: Poem
//
//    for(p in Poems.values()){
//        data = p.toPoem()
//        poemList.add(data)
//    }
    // リスト作成
    val randomList = arrayListOf<Int>()

    // 繰り返し処理
    repeat(count){
        randomList.add(abs(Random.nextInt()) % 10 + 1)
    }

    if(!duplicate){
        randomList.distinct()
    }

    return randomList.toIntArray()

}

//var mockPoemList = listOf(Poem(topContent = "かくとだに"), Poem(topContent = "あけぬれば"))
//
//val app = functionComponent<Props> {
//    val (poemList, setPoemList) = useState(mockPoemList)
//    val (inputText, setInputText) = useState("")
//
//    div{
//        form{
//            attrs{
//                onSubmit={
//                    setPoemList(poemList.toMutableList().apply {
//                        add(Poem(topContent = inputText))
//                    })
//                    setInputText("")
//                    it.preventDefault()
//                }
//            }
//            input{
//                attrs{
//                    type=InputType.text
//                    value=inputText
//                    onChange={
//                        setInputText(it.target.value)
//                    }
//                }
//            }
//            input{
//                attrs{
//                    type=InputType.submit
//                    value="提出"
//                    disabled=inputText.isBlank()
//                }
//            }
//
//            poemList.forEachIndexed{ _, poem ->
//                div{
//                    + poem.topContent
//                }
//            }
//        }
//    }
//}