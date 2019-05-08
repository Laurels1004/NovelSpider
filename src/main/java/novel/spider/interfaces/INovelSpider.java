package novel.spider.interfaces;

import java.util.List;

import novel.spider.entities.Novel;
//获取小说列表
public interface INovelSpider {
	//抓取某个页面最大尝试次数
	public static final int MAX_TRY_TIMES = 3;
	//下载
	public List<Novel> getNovel(String url);
}
