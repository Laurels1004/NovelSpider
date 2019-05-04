package novel.spider.interfaces;

public interface INovelDownload {
	//这里的url是小说章节列表页面,返回下载文件的本地地址
	public String download(String url);
}
