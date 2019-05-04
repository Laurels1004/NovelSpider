package novel.spider.interfaces;

import novel.spider.configuration.Configuration;

public interface INovelDownload {
	//这里的url是小说章节列表页面,返回下载文件的本地地址
	public String download(String url,Configuration config);
}
