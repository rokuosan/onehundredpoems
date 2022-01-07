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
    TAKINOOTOHA(55, "滝の音は", "名こそ流れて", "滝の名こそ"),
    ARAZARAMU(56, "あらあざらむ", "今一度の逢ふこともがな", "あらざらん今一度の会う"),
    MEGURIAITE(57, "めぐり逢ひて", "雲隠れにし", "メグ、雲隠れ"),
    ARIMAYAMA(58, "有馬山", "いでそよ人を", "有馬、いいで"),
    YASURAHADE(59, "やすらはで", "傾くまでの", "安かった"),
    OOEYAMA(60, "大江山", "まだふみも見ず", "大江、まだ？"),
    INISIENO(61, "いにしえの", "きょうここのえに", "古の京子"),
    YOWOKOMETE(62, "夜をこめて", "よに逢坂の", "よをよに"),
    IMAHATADA(63, "いまはただ", "ひとづてならで", "今は一つ");

    fun toPoem(): Poem{
        return Poem(this.id, this.top, this.under, this.remind)
    }
}
