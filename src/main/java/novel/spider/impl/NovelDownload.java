package novel.spider.impl;

import novel.spider.interfaces.INovelDownload;
/**
 *  实现多线程下载任意网站的小说
 * 1.拿到该网站的某本小说所有章节:章节列表
 * 2.通过计算,将这些章节分配给指定数量的线程,让他们去解析,然后保存到文本中
 * 3.通知主线程,将这些零散的小文件合并成一个大文件,最后将分片小文件删除
 * */
public class NovelDownload implements INovelDownload {

	@Override
	public String download(String url) {
		// TODO Auto-generated method stub
		return null;
	}

}
