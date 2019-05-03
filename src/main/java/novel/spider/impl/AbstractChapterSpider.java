package novel.spider.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.NovelSiteEnum;
import novel.spider.entities.Chapter;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSpiderUtil;

public abstract class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {
	
	@Override
	public List<Chapter> getChapter(String url) {
		// TODO Auto-generated method stub
		try {
			String result = crawl(url);
			Document doc = Jsoup.parse(result);
			//网站路径
			doc.setBaseUri(url);
			Elements as = doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-lists-selector"));
			List<Chapter> chapters = new ArrayList<Chapter>();
			for (Element a:as) {
				Chapter chapter = new Chapter();
				chapter.setTitle(a.text());
				//chapter.setUrl("http://www.shuquge.com/"+a.attr("href"));
				//链接路径设置绝对路径
				chapter.setUrl(a.absUrl("href"));
				chapters.add(chapter);
			}
			return chapters;
			//System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
