package eu.kanade.tachiyomi.extension.pt.mangasbrasuka

import eu.kanade.tachiyomi.multisrc.aurora.Aurora
import keiyoushi.annotation.Source
import org.jsoup.nodes.Element

@Source
class MangasBrasuka : Aurora(
    "Mangas Brasuka",
    "https://mangasbrasuka.org",
    "pt-BR",
) {
    override fun chapterListSelector() = "ul li:has(a[href*='/manga/'])"

    override fun chapterFromElement(element: Element) = super.chapterFromElement(element).apply {
        val link = element.selectFirst("a[href*='/manga/']")
        if (link != null) {
            setUrlWithoutDomain(link.attr("href"))
            name = link.selectFirst("span, p, div")?.text() ?: link.text()
        }
    }
}
