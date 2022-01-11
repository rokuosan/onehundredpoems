import components.ViewStatus
import components.app
import components.mainApp
import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    window.onload={
        // URLを解析してリストに入れる
        val pathList: List<String> = window.location.pathname.split("/").toMutableList().filter { it.isNotBlank() }

        // 判定して適したコンポーネントを呼び出す
        if(pathList.isEmpty()){
            // ルートディレクトリの場合
            render(document.getElementById("simpleApp")){
                child(app)
            }
        }else{
            // ルートディレクトリ以外
            when(pathList[0]){
                "demo"->{
                    when(pathList[1]){
                        "playground.html"->{
                            render(document.getElementById("mainApp")){
                                mainApp(ViewStatus.Ready)
                            }
                        }
                    }
                }
            }
        }


    }
}
