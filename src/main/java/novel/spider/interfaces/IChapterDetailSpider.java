package novel.spider.interfaces;

import novel.spider.entities.ChapterDetail;

public interface IChapterDetailSpider {
	//获取一个url.返回一个对应网站章节内容实体
	public ChapterDetail getChapterDetail(String url);

}
