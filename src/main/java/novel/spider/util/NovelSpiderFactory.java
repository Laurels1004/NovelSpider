package novel.spider.util;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.novel.SqgNovelSpider;
import novel.spider.interfaces.INovelSpider;

public final class NovelSpiderFactory {
		private NovelSpiderFactory() {}
		public static INovelSpider getNovelSpider(String url) {
			NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
			switch(novelSiteEnum) {
				case  ShuQuGe:return new SqgNovelSpider();
				default : throw new RuntimeException(novelSiteEnum+"暂时不被支持");
			}
		}
		
}
