package novel.spider.interfaces;

import java.util.List;

import novel.spider.entities.Chapter;

public interface IChapterSpider {
	/**
	 * 	获取完整url，返回所有章节列表
	 * */
	public List<Chapter> getChapter(String url);
}
