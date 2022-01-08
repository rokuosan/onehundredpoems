package components

import components.SceneType.*
import react.PropsWithChildren
import react.dom.ButtonType
import react.dom.ReactHTML.button
import react.dom.ReactHTML.div
import react.dom.ReactHTML.h1
import react.dom.ReactHTML.h3
import react.dom.ReactHTML.label
import react.dom.ReactHTML.span
import react.functionComponent
import react.useState

enum class SceneType{
    InGame,
    Ready;
}

val app = functionComponent<PropsWithChildren> {
    val (scene, setScene) = useState(Ready)

    when(scene){
        Ready ->{
            div{
                attrs{
                    className="text-center mt-md-5 mb-md-5"
                }
                h1{
                    + "百人一首完全マスター君(50%)"
                }
                div{
                    attrs{
                        className="d-grid gap-2 d-md-flex justify-content-md-center mt-5"
                    }
                    button{
                        attrs{
                            className="btn btn-primary"
                            type=ButtonType.button
                            onClick={
                                setScene(InGame)
                                it.preventDefault()
                            }
                        }
                        span{
                            + "ゲームを開始する"
                        }
                    }
                }
            }
        }
        InGame ->{
            div{
                attrs{
                    className="container"
                }
                child(poemGame)
            }
            div{
                div{
                    attrs{
                        className="d-grid gap-2 d-md-flex justify-content-md-center mt-5"
                    }
                    button{
                        attrs{
                            className="btn btn-outline-danger"
                            type=ButtonType.button
                            onClick={
                                setScene(Ready)
                                it.preventDefault()
                            }
                        }
                        label{
                            span{
                                attrs{
                                    className="material-icons-outlined align-middle"
                                }
                                +"clear"
                            }
                            span{
                                attrs{
                                    className="align-top"
                                }
                                + " 終了する"
                            }
                        }
                    }
                }
            }
        }
    }
}


val poemGame = functionComponent<PropsWithChildren> {
    val poemList = Poems.values().toMutableList()
    val (count, setCount) = useState(0)
    val (poem, setPoem) = useState(poemList[count].toPoem())
    val (poemTitle, setPoemTitle) = useState(poem.topContent)
    val (poemAnswer, setPoemAnswer) = useState("")
    val (poemRemind, setPoemRemind) = useState("")
    val (controllerVisible, setControllerVisible) = useState(true)

    // 問題を表示する行
    div{
        attrs{
            className="container"
        }
        div{
            attrs{
                className="row justify-content-center align-items-end"
            }
            div{
                attrs{
                    className="col-4 text-end"
                }
                h3{
                    + "No.${poem.id}"
                }
            }
            div{
                attrs{
                    className="col-8 text-start border-start border-4 ps-4"
                }
                h1{
                    attrs{
                        className="display-4 fw-bold"
                    }
                    +poemTitle
                }
            }
        }
    }

    // 答えと覚え方を表示する行
    div{
        attrs{
            className="container mt-5"
        }
        div{
            attrs{
                className="row row-cols-2 gy-2 align-items-end"
            }
            div{
                attrs{
                    className="col-4 text-end border-end border-2"
                }
                span{
                    attrs{
                        className="fs-4 fw-light fst-italic"
                    }
                    +"答え "
                }
            }
            div{
                attrs{
                    className="col-8"
                }
                span{
                    attrs{
                        className="fs-4 fst-italic"
                    }
                    +"　$poemAnswer"
                }
            }
            div{
                attrs{
                    className="col-4 text-end border-end border-2"
                }
                span{
                    attrs{
                        className="fs-4 fw-light fst-italic"
                    }
                    +"覚え方 "
                }
            }
            div{
                attrs{
                    className="col-8"
                }
                span{
                    attrs{
                        className="fs-4 fst-italic"
                    }
                    +"　$poemRemind"
                }
            }
        }
    }

    // 答えを見る、次の問題へボタンを表示する行
    if(controllerVisible){
        div{
            attrs{
                className="row gy-5 mb-md-5 mt-4"
            }
            // 答えをみるボタン
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
                                span{
                                    attrs{
                                        className="material-icons-outlined align-middle"
                                    }
                                    +"spellcheck"
                                }
                                span{
                                    attrs{
                                        className="align-middle"
                                    }
                                    + " 答えを見る"
                                }
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
            // 次の問題へボタン
            div{
                attrs{
                    className="col "
                }
                div{
                    attrs{
                        className="d-grid gap-2 d-md-flex justify-content-md-start"
                    }
                    button{
                        attrs{
                            type=ButtonType.button
                            label{
                                span{
                                    attrs{
                                        className="align-middle"
                                    }
                                    + "次の問題へ "
                                }
                                span{
                                    attrs{
                                        className="material-icons-outlined align-middle"
                                    }
                                    +"east"
                                }
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
                                    setControllerVisible(false)
                                }
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