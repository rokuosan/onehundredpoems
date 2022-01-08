package components

data class Poem(
    var id: Long=0,
    var topContent: String = "",
    var underContent: String = "",
    var remindContent: String = ""
)

enum class Poems(private val id: Long, private val top: String, private val under: String, private val remind: String){

    KAKUTDANI(51, "かくとだに", "さしもしらじな", "かくさ(書くさ)"),
    AKENUREBA(52, "明けぬれば", "なほ恨めしき", "あけなほうら(開けな ほら)"),
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
    IMAHATADA(63, "いまはただ", "ひとづてならで", "今は一つ"),
    ASABORAKE(64, "朝ぼらけ", "あらはれ渡る", "朝ぼらけ宇治、現れる"),
    URAMIWABI(65, "恨みわび", "恋に朽ちなむ", "裏の鯉に"),
    MOROTOMONI(66, "もろともに", "花より外に", "もろともに花よ"),
    HARUNOYONO(67, "春の夜の", "かひなく立たむ", "春の貝"),
    KOKORONIMO(68, "心にも", "恋しかるべし", "心に小石"),
    ARASIHUKU(69, "嵐吹く", "龍田の川の", "嵐たつ"),
    SABISISANI(70, "さびしさに", "いづこも同じ", "さぁ、いづこ？"),
    YUUSAREBA(71, "夕されば", "芦のまろやに", "言う足"),
    OTONIKIKU(72, "音に聞く", "かけじや袖の", "音にかけ"),
    TAKASAGONO(73, "高砂の", "外山の霞", "たか とやま(鷹と山)"),
    UKARIKERU(74, "うかりける", "はげしかれとは", "うかり ハゲ(うっかりハゲ)"),
    CHIGIRIOKISI(75, "契りおきし", "あはれ今年の", "ちぎりお あわれ(千切り尾、哀れ)"),
    WATANOHARA(76, "わたの原", "雲居にまがふ", "わたの原、雲居)"),
    SEWOHAYAMI(77, "瀬を早み", "われても末に", "せわれ(背割れ)"),
    AWAJISIMA(78, "淡路島", "幾夜寝覚めぬ", "淡路島行くよ"),
    AKIKAZENI(79, "秋風に", "もれ出づる月の", "秋風、もれ出づる"),
    NAGAKARAN(80, "長からむ", "乱れてけさは", "長から、乱れて"),
    HOTOTOGISU(81, "ほととぎす", "ただ有り明けの", "ほととぎすはタダ"),
    OMOHIWABI(82, "思ひわび", "憂きにたへぬは", "重い浮き輪"),
    YONONAKAYO(83, "世の中よ", "山の奥にも", "世の中よ、山の奥"),
    NAGARAHEBA(84, "ながらへば", "憂しと見し世ぞ", "ながら牛"),
    YOMOSUGARA(85, "夜もすがら", "閏(ねや)のひまさへ", "用もねぇや"),
    NAGEKETOTE(86, "嘆けとて", "かこち顔なる", "嘆け過去"),
    MURASAMENO(87, "村雨の", "霧立ちのぼる", "ムキ～"),
    NANIWAENO(88, "難波江の", "みをつくしてや", "難波江の澪標やぁ"),
    TAMANOWOYO(89, "玉の緒よ", "忍ぶることの", "玉忍ぶ"),
    MISEBAYANA(90, "見せばやな", "濡れにぞ濡れし", "見せぬ"),
    KIRIGIRISU(91, "きりぎりす", "衣かたしき", "きりぎりすの衣は固い"),
    WAGASODEHA(92, "わが袖は", "人こそ知らね", "我が袖は人こそ知らね"),
    YONONAKAHA(93,"世の中は", "海人の小舟の", "世の中は海女の尾"),
    MIYOSINONO(94,"み吉野の", "ふるさと寒く", "見よ、ふるさと"),
    OHOKENAKU(95,"おほけなく", "我が立つ杣に", "OKわかった"),
    HANASASOHU(96,"花さそふ", "ふりゆくものは", "花誘うふり"),
    KONUHITOWO(97,"来ぬ人を", "焼くや藻塩の", "来ぬ人を焼く"),
    KAZESOYOGU(98,"風そよぐ", "みそぎぞ夏の", "風そよぐ味噌"),
    HITOMOOSI(99, "人も惜し", "世を思ふ故に", "人もよおぉ～"),
    MOMOSIKIYA(100, "ももしきや", "なほあまりある", "桃、なお余り");






    fun toPoem(): Poem {
        return Poem(this.id, this.top, this.under, this.remind)
    }
}
