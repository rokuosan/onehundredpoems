import react.PropsWithChildren
import react.dom.ButtonType
import react.dom.ReactHTML.button
import react.dom.ReactHTML.div
import react.dom.ReactHTML.h1
import react.dom.ReactHTML.label
import react.dom.ReactHTML.p
import react.functionComponent
import react.useState

/**
 * とりあえず一問一答で実装したもの
 */
val simplePoemApp = functionComponent<PropsWithChildren> {
    val poemList = Poems.values().toList()
    val (count, setCount) = useState(0)
    val (poem, setPoem) = useState(poemList[count].toPoem())
    val (poemTitle, setPoemTitle) = useState(poem.topContent)
    val (poemAnswer, setPoemAnswer) = useState("")
    val (poemRemind, setPoemRemind) = useState("")

    div{
        div{
            attrs{
                className="text-center mt-md-5 mb-md-5"
            }
            h1{
                +poemTitle
            }
            div{
                attrs{
                    className="text-start"
                }
                p{
                    attrs{
                        className="fs-4"
                    }
                    +"${if(poemAnswer.isNotBlank()){ "[解答] " }else{"　"}} $poemAnswer"
                }
                p{
                    attrs{
                        className="fs-4"
                    }
                    +"${if(poemRemind.isNotBlank()){ "[決字] " }else{"　"}} $poemRemind"
                }
            }
        }
    }
    div{
        attrs{
            className="row gy-5 mb-md-5"
        }
        div{
            attrs{
                className="col"
            }
            div{
                attrs{
                    className="d-grid gap-2 d-md-flex justify-content-md-end"
                }
                button{
                    attrs{
                        type=ButtonType.button
                        label{
                            + "答えを見る"
                        }
                        className="btn btn-primary"
                        onClick={ event ->
                            event.preventDefault()
                            setPoemAnswer(poem.underContent)
                            setPoemRemind(poem.remindContent)
                        }
                    }
                }
            }
        }
        div{
            attrs{
                className="col"
            }
            div{
                attrs{
                    className="d-grid gap-2 d-md-flex justify-content-md-start"
                }
                button{
                    attrs{
                        type=ButtonType.button
                        label{
                            + "次の問題へ"
                        }
                        className="btn btn-primary"
                        onClick={ event ->
                            event.preventDefault()
                            if(count < poemList.size-1){
                                setCount(count+1)
                                val p = poemList[count+1].toPoem()
                                setPoem(p)
                                setPoemTitle(p.topContent)
                                setPoemAnswer("")
                                setPoemRemind("")
                            }else{
                                setPoemTitle("おわり！")
                                setPoemAnswer("")
                                setPoemRemind("")
                                setPoem(Poem())
                            }
                        }
                    }
                }
            }
        }
    }


}

/**
 * ランダムな句を任意の数選ぶ
 * answerに置いた句を含めた数をamountに指定する
 */
fun getRandomPoems(): ArrayList<Poem>{
    val poemList = arrayListOf<Poem>()
    val poems = Poems.values()
    var poem: Poem

    while(poemList.size<4){
        poem = poems.random().toPoem()
        if(!poemList.contains(poem)){
            poemList.add(poem)
        }
    }

    return poemList
}