package components

import components.ViewStatus.*
import react.Props
import react.RBuilder
import react.RComponent
import react.State

enum class ViewStatus{
    Ready,
    InGame,
    Finish
}

external interface AppState: State{
    var status: ViewStatus
}

external interface AppProps: Props{
    var status: ViewStatus
}

class MainApp: RComponent<AppProps, AppState>() {
    override fun AppState.init(){
        status=Ready
    }

    override fun RBuilder.render() {
        when(props.status){
            InGame ->{
                +"ゲーム中"
            }
            Ready ->{
                +"準備中"
            }
            Finish -> TODO()
        }
    }
}
fun RBuilder.mainApp(status: ViewStatus)=child(MainApp::class){
    attrs.status=status
}