package novel.spider.impl.novel;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.entities.Novel;

//网站书籍列表爬取
public class SqgNovelSpider extends AbstractNovelSpider {

	public SqgNovelSpider() {
		
	}

	@Override
	public List<Novel> getNovel(String url) {
		List<Novel> novels = new ArrayList<>();
		try {
			Elements as = super.getA(url,2);
			for(int index = 0, size = as.size(); index < size; index++) {
				Element a = as.get(index);
				Novel novel = new Novel();
				novel.setName(a.text());
				String Novelurl = a.absUrl("href");
				novel.setUrl(Novelurl);
				String author = a.previousElementSibling().text();
				novel.setAuthor(author);
				novels.add(novel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		return novels;
	}

}
