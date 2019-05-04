package novel.spider.util;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.DefaultChapterDetailSpider;
import novel.spider.interfaces.IChapterDetailSpider;

public final class ChapterDetailSpiderFactory {
	private ChapterDetailSpiderFactory() {}
	
	//通过给定的url拿到实现了IChapterDetailSpider的具体实现类
	public static IChapterDetailSpider getChapterDetailSpider(String url) {
		IChapterDetailSpider chapterDetailSpider = null;
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
			case ShuQuGe:
			case BiQuGe:
			case BiXiaWenXue:
				chapterDetailSpider = new DefaultChapterDetailSpider(); break;
		}
		return chapterDetailSpider;
	}
}
