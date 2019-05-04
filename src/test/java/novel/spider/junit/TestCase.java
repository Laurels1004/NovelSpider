package novel.spider.junit;

import java.util.List;

import org.junit.Test;

import novel.spider.NovelSiteEnum;
import novel.spider.configuration.Configuration;
import novel.spider.entities.Chapter;
import novel.spider.impl.DefaultChapterDetailSpider;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.impl.NovelDownload;
import novel.spider.impl.SpecialChapterSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.util.NovelSpiderUtil;

public class TestCase {
	//获取章节列表
	@Test
//	public void testGetChapter() throws Exception{
//		IChapterSpider spider = new DefaultChapterSpider();
//		List<Chapter> chapters = spider.getChapter("http://www.shuquge.com/txt/8659/index.html");
//		for (Chapter chapter:chapters) {
//			System.out.println(chapter);
//		}
//	}
//	
//	//通过枚举获取到相应站点文章列表内容
//	public void testGetSiteContext() {
//		System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("http://www.shuquge.com/txt/8659/index.html")));
//	}
//	
//	//获取文章详情页的内容
//	public void testGetChapterDetail() {
//		IChapterDetailSpider detailSpider = new DefaultChapterDetailSpider();
//		System.out.println(detailSpider.getChapterDetail("http://www.biquge.tw/0_5/1501.html").getContent());
//	}
//	
//	//获取站点特殊文章列表排序内容
//	public void testGetSpecailChapter() {
//		IChapterSpider spider = new SpecialChapterSpider();
//		List<Chapter> chapters = spider.getChapter("https://www.bixia.org/268_268073/");
//		for (Chapter chapter:chapters) {
//			System.out.println(chapter);
//		}
//	}
//	
	//测试下载
	public void testDownload() {
		INovelDownload download = new NovelDownload();
		Configuration config = new Configuration();
		config.setLocalpath("D:/1");
		config.setSize(100);
		download.download("http://www.shuquge.com/txt/8659/index.html", config);
	}
}
