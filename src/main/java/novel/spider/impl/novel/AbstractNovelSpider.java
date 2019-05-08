package novel.spider.impl.novel;

import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import novel.spider.NovelSiteEnum;
import novel.spider.entities.Novel;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderUtil;

//一个抽象的小说列表抓取,实现解析a元素方法
public abstract class AbstractNovelSpider extends AbstractSpider implements INovelSpider {
	//默认的抓取方法
	protected Elements getA(String url) throws Exception {
		return getA(url,INovelSpider.MAX_TRY_TIMES);
	}
	protected Elements getA(String url, Integer maxTryTimes) throws Exception{
		maxTryTimes = maxTryTimes == null ? INovelSpider.MAX_TRY_TIMES:maxTryTimes;
		Elements as = null;
		for (int i = 0; i<maxTryTimes; i++) {
			try {
				String result = super.crawl(url);
				Map<String,String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
				String novelSelector = context.get("novel-selector");
				if(novelSelector == null) throw new RuntimeException(NovelSiteEnum.getEnumByUrl(url).getUrl()+",url="+url+"目前不支持") ;
				Document doc = Jsoup.parse(result);
				doc.setBaseUri(url);
				as = doc.select(novelSelector);
				return as;
			} catch (Exception e) {
				// TODO: handle exception
			}
			throw new RuntimeException(url+"尝试了"+maxTryTimes+"次,依然下载失败了");
		}
		return null;
	}
}
