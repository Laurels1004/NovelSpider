package novel.spider.junit;

import java.util.List;

import org.junit.Test;

import novel.spider.entities.Chapter;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.interfaces.IChapterSpider;

public class TestCase {
	@Test
	public void test1() throws Exception{
		IChapterSpider spider = new DefaultChapterSpider();
		List<Chapter> chapters = spider.getChapter("http://www.shuquge.com/txt/8659/index.html");
		for (Chapter chapter:chapters) {
			System.out.println(chapter);
		}
	}
}
