package components

import components.SceneType.*
import react.PropsWithChildren
import react.dom.ButtonType
import react.dom.HTMLAttributeAnchorTarget
import react.dom.ReactHTML.a
import react.dom.ReactHTML.br
import react.dom.ReactHTML.button
import react.dom.ReactHTML.div
import react.dom.ReactHTML.h1
import react.dom.ReactHTML.h3
import react.dom.ReactHTML.label
import react.dom.ReactHTML.p
import react.dom.ReactHTML.section
import react.dom.ReactHTML.span
import react.dom.ReactHTML.table
import react.dom.ReactHTML.tbody
import react.dom.ReactHTML.td
import react.dom.ReactHTML.th
import react.dom.ReactHTML.thead
import react.dom.ReactHTML.tr
import react.dom.style
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
                        className="d-grid gap-2 d-md-flex justify-content-md-center mt-5 mb-5"
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
                div{
                    attrs{
                        className="container mt-5"
                    }
                    div{
                        attrs{
                            className="row"
                        }
                        div{
                            attrs{
                                className="col"
                            }
                            section{
                                p{
                                    +"百人一首の下50句をまとめて問題にしたものです。下50句だけを最小の努力で一時的に記憶したい場合に向いています。それ以外の用途は、決まり字の違いなどの理由から推奨できません。"
                                }
                            }
                            section{
                                p{
                                    +"また、今回の問題を作成するにあたって参考にしたサイトは"
                                    a{
                                        attrs{
                                            href="https://polygondrill.com/firstkaruta/list/list-kimari-goro"
                                            target=HTMLAttributeAnchorTarget._blank
                                            rel="noopener noreferrer"
                                        }
                                        +"こちら"
                                    }
                                    +"です。"
                                }
                            }
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
                        className="d-grid gap-2 d-md-flex justify-content-md-center my-5"
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
    val (tableVisible, setTableVisible) = useState(false)
    val (stackVisible, setStackVisible) = useState(false)

    // 進行度
    div{
        div{
            attrs{
                className="progress pr-h mb-3"
            }
            div{
                val width=if(controllerVisible){
                    poem.id-50L
                }else{
                    50
                }
                attrs{
                    className="progress-bar progress-bar-striped prd-w"
                    style(content= ".prd-w{width: ${width*2}%; height: 5px;} .pr-h{height: 5px;}")
//                    role=AriaRole.progressbar
                    ariaValueNow=width* 2.toDouble()
                    ariaValueMin=2.toDouble()
                    ariaValueMax=100.toDouble()
                }
            }
        }
    }

    if(!controllerVisible){
        // お疲れ様
        div {
            attrs {
                className="row row-cols-1 mb-5 gy-3"
            }
            div {
                attrs {
                    className="col align-self-center text-center"
                }
                h1 {
                    attrs {
                        className="display-3"
                    }
                    +"お疲れさまでした"
                }
            }
            div {
                attrs {
                    className="col"
                }
                div {
                    attrs {
                        className="d-grid gap-2 d-md-flex justify-content-md-center my-3"
                    }
                    button {
                        attrs {
                            className="btn btn-success"
                            type=ButtonType.button
                            onClick={
                                setTableVisible(!tableVisible)
                                it.preventDefault()
                            }
                        }
                        label {
                            span {
                                attrs {
                                    className="material-icons-outlined align-middle"
                                }
                                +if(!tableVisible) {
                                    "visibility"
                                } else {
                                    "visibility_off"
                                }
                            }
                            span {
                                attrs {
                                    className="align-top"
                                }
                                +if(!tableVisible) {
                                    "　表を表示する"
                                } else {
                                    "　表を表示しない"
                                }
                            }
                        }
                    }
                }
            }
            if(tableVisible) {
                div {
                    attrs {
                        className="col align-self-center text-center"
                    }
                    table {
                        attrs {
                            className="table table-sm table-striped table-bordered"
                        }
                        thead {
                            tr {
                                th {
                                    attrs {
                                        scope="col-sm-1"
                                    }
                                    +"No"
                                }
                                th {
                                    attrs {
                                        scope="col-sm-auto"
                                    }
                                    +"上の句"
                                }
                                th {
                                    attrs {
                                        scope="col-sm-auto"
                                    }
                                    +"下の句"
                                }
                                th {
                                    attrs {
                                        scope="col-sm-auto"
                                    }
                                    +"覚え方"
                                }
                            }
                        }
                        tbody {
                            poemList.forEachIndexed { index, poems ->
                                tr {
                                    th {
                                        attrs {
                                            scope="row"
                                        }
                                        +"${index + 51}"
                                    }
                                    td {
                                        +poems.toPoem().topContent
                                    }
                                    td {
                                        +poems.toPoem().underContent
                                    }
                                    td {
                                        +poems.toPoem().remindContent
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    if(controllerVisible){
        // 問題を表示する行
        div{
            attrs{
                className="container"
            }
            div{
                attrs{
                    className="row"
                }
                div{
                    attrs{
                        className="col-auto border border-2"
                    }
                    h3{
                        attrs{
                            className="my-0"
                        }
                        + "No.${poem.id}"
                    }
                }
            }
            div{
                attrs{
                    className="container"
                }
                div{
                    attrs{
                        className="row justify-content-md-center"
                    }
                    div{
                        attrs{
                            className="col-auto text-start border-start border-4 ps-4"
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
        }

        // 答えと覚え方を表示する行
        div{
            attrs{
                className="container mt-5"
            }
            // こたえ
            div{
                attrs{
                    className="row justify-content-md-center"
                }
                div{
                    attrs{
                        className="col-auto border-bottom border-2"
                    }
                    span{
                        attrs{
                            className="fs-4 fw-light fst-italic"
                        }
                        +"こたえ "
                    }
                }
            }
            // こたえの本文
            div{
                attrs{
                    className="row justify-content-md-center mt-1"
                }
                div{
                    attrs{
                        className="col-auto"
                    }
                    span{
                        attrs{
                            className="fs-4 fst-italic"
                        }
                        +poemAnswer.ifBlank {
                            "　"
                        }
                    }
                }
            }
            // 覚え方
            div{
                attrs{
                    className="row justify-content-md-center mt-3"
                }
                div{
                    attrs{
                        className="col-auto border-bottom border-2"
                    }
                    span{
                        attrs{
                            className="fs-4 fw-light fst-italic"
                        }
                        +"覚え方 "
                    }
                }
            }
            // 覚え方の本文
            div{
                attrs{
                    className="row justify-content-md-center mt-1"
                }
                div{
                    attrs{
                        className="col-auto text-center"
                    }
                    span{
                        attrs{
                            className="fs-4 fst-italic"
                        }
                        +poemRemind.ifBlank {
                            "　"
                        }
                    }
                }
            }
        }
    }

    // 答えを見る、次の問題へボタンを表示する行
    if(controllerVisible){
        div{
            attrs{
                className="row row-cols-1 gx-1 gy-5 mb-md-5 mt-4"
//                className="row d-grid gap-2 d-md-block"
            }

            // 答えをみるボタン
            div{
                attrs{
                    className="col-4"
                }
                div{
                    attrs{
                        className="d-grid gap-2 d-md-block d-md-flex justify-content-md-end"
                    }
                    button{
                        attrs{
                            type=ButtonType.button
                            label{
                                span{
                                    attrs{
                                        className="material-icons-outlined align-middle"
                                    }
                                    +"arrow_back"
                                }
                                br{}
                                span{
                                    attrs{
                                        className="align-middle"
                                    }
                                    + "前の問題へ"
                                }
                            }
                            className="btn btn-primary"
                            disabled=count==0
                            onClick={ event ->
                                event.preventDefault()
                                if(count < poemList.size-1){
                                    setCount(count-1)
                                    val p = poemList[count-1].toPoem()
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
            // 答えをみるボタン
            div{
                attrs{
                    className="col-4"
                }
                div{
                    attrs{
                        className="d-grid gap-2 d-md-flex justify-content-md-center"
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
                                br{}
                                span{
                                    attrs{
                                        className="align-middle"
                                    }
                                    + "答えを見る"
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
                    className="col-4"
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
                                        className="material-icons-outlined align-middle"
                                    }
                                    +"arrow_forward"
                                }
                                br{}
                                span{
                                    attrs{
                                        className="align-middle"
                                    }
                                    + "次の問題へ"
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
                                    setPoemTitle("おわり")
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
    if(controllerVisible){
        div {
            attrs {
                className="row row-cols-1 mb-5 gy-3"
            }
            div {
                attrs {
                    className="col"
                }
                div {
                    attrs {
                        className="d-grid gap-2 d-md-flex justify-content-md-center my-3"
                    }
                    button {
                        attrs {
                            className="btn btn-success"
                            type=ButtonType.button
                            onClick={
                                setStackVisible(!stackVisible)
                                it.preventDefault()
                            }
                        }
                        label {
                            span {
                                attrs {
                                    className="material-icons-outlined align-middle"
                                }
                                +if(!stackVisible) {
                                    "visibility"
                                } else {
                                    "visibility_off"
                                }
                            }
                            span {
                                attrs {
                                    className="align-top"
                                }
                                +if(!stackVisible) {
                                    "　表を表示する"
                                } else {
                                    "　表を表示しない"
                                }
                            }
                        }
                    }
                }
            }
            if(stackVisible && count!=0) {
                div {
                    attrs {
                        className="col align-self-center text-center"
                    }
                    table {
                        attrs {
                            className="table table-sm table-striped table-bordered"
                        }
                        thead {
                            tr {
                                th {
                                    attrs {
                                        scope="col-sm-1"
                                    }
                                    +"No"
                                }
                                th {
                                    attrs {
                                        scope="col-sm-auto"
                                    }
                                    +"上の句"
                                }
                                th {
                                    attrs {
                                        scope="col-sm-auto"
                                    }
                                    +"下の句"
                                }
                                th {
                                    attrs {
                                        scope="col-sm-auto"
                                    }
                                    +"覚え方"
                                }
                            }
                        }
                        tbody {
                            poemList.forEachIndexed { index, poems ->
                                if(index < count){
                                    tr {
                                        th {
                                            attrs {
                                                scope="row"
                                            }
                                            +"${index + 51}"
                                        }
                                        td {
                                            +poems.toPoem().topContent
                                        }
                                        td {
                                            +poems.toPoem().underContent
                                        }
                                        td {
                                            +poems.toPoem().remindContent
                                        }
                                    }
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