package novel.spider.junit;

import java.util.List;

import org.junit.Test;

import novel.spider.NovelSiteEnum;
import novel.spider.entities.Chapter;
import novel.spider.impl.DefaultChapterDetailSpider;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSpiderUtil;

public class TestCase {
	@Test
//	public void testGetChapter() throws Exception{
//		IChapterSpider spider = new DefaultChapterSpider();
//		List<Chapter> chapters = spider.getChapter("http://www.shuquge.com/txt/8659/index.html");
//		for (Chapter chapter:chapters) {
//			System.out.println(chapter);
//		}
//	}
//	
//	public void testGetSiteContext() {
//		System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("http://www.shuquge.com/txt/8659/index.html")));
//	}
	
	public void testGetChapterDetail() {
		IChapterDetailSpider detailSpider = new DefaultChapterDetailSpider();
		System.out.println(detailSpider.getChapterDetail("http://www.shuquge.com/txt/8659/24398816.html").getContent());
	}
}
