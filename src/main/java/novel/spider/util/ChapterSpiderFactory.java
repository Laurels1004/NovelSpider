package novel.spider.util;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.impl.chapter.SpecialChapterSpider;
import novel.spider.interfaces.IChapterSpider;

public final class ChapterSpiderFactory {
	private ChapterSpiderFactory() {}
	
	//通过给定的url返回一个实现了IChapterSpider接口的实现类
	public static IChapterSpider getChapterSpider(String url) {
		//通过传入的url获取到对应的枚举
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		IChapterSpider chapterSpider = null;
		switch (novelSiteEnum) {
			case ShuQuGe:
			case BiQuGe:
				chapterSpider = new DefaultChapterSpider();
				break;
			case BiXiaWenXue: chapterSpider = new SpecialChapterSpider();break;
		}
		return chapterSpider;
	}
}
