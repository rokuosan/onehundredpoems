import react.PropsWithChildren
import react.dom.InputType
import react.dom.ReactHTML.div
import react.dom.ReactHTML.form
import react.dom.ReactHTML.input
import react.dom.ReactHTML.table
import react.dom.ReactHTML.tbody
import react.dom.ReactHTML.td
import react.dom.ReactHTML.th
import react.dom.ReactHTML.thead
import react.dom.ReactHTML.tr
import react.functionComponent
import react.useState
import kotlin.math.abs
import kotlin.random.Random

//external interface QuestionProps: Props {
//    var name: String
//}
//data class QuestionState(var name: String): State
//
//class Question(props: QuestionProps): RComponent<QuestionProps, QuestionState>(props){
//    init{
//        state=QuestionState(props.name)
//    }
//
//    override fun RBuilder.render(){
//        button{
//            attrs{
//                type=ButtonType.button
//                label {
//                    + state.name
//                }
//                className="btn btn-primary"
//                onClick={ _ ->
//                    setState(
//                        QuestionState(name="押されたわ...")
//                    )
//
//                }
//            }
//        }
//    }
//}

val mockRandomNumberList = getRandomList(10, false)

val questionApp = functionComponent<PropsWithChildren> {
    val (randomNumberList, setRandomNumberList) = useState(
        mockRandomNumberList
    )

    div{
        form{
            attrs{
                onSubmit={
                    setRandomNumberList(
                        getRandomList(duplicate = false)
                    )

                    it.preventDefault()
                }
            }
            input{
                attrs{
                    type=InputType.submit
                    value="乱数を再生成"
                    className="btn btn-primary"
                }
            }
        }
    }

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
            randomNumberList.forEachIndexed { index, i ->
                tr{
                    th{
                        attrs{
                            scope="row"
                        }
                        + "${index+1}"
                    }
                    td{
                        + "$i"
                    }
                }
            }
//            for(i in randomNumberList.indices){
//                tr{
//                    th{
//                        attrs{
//                            scope="row text-center"
//                        }
//                        +"${i + 1}"
//                    }
//                    td{
//                        +"${randomNumberList[i]}"
//                    }
//                }
//            }
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