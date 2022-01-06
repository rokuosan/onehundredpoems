data class Poem(
    var id: Long=0,
    var topContent: String = "",
    var underContent: String = "",
    var remindContent: String = ""
)

enum class Poems(private val id: Long, private val top: String, private val under: String, private val remind: String){

    KAKUTDANI(51, "かくとだに", "さしもしらじな", "かく さ(書くさ)"),
    AKENUREBA(52, "明けぬれば", "なほ恨めしき", "あけ なほうら(開けな ほら)"),
    NAGEKITUTU(53, "嘆きつつ", "いかに久しき", "嘆き イカ"),
    WASUREZINO(54, "忘れじの", "今日を限りの", "忘れじ今日を"),
    TAKINOOTOHA(55, "滝の音は", "名こそ流れて", "滝の名こそ");

    fun toPoem(): Poem{
        return Poem(this.id, this.top, this.under, this.remind)
    }
}