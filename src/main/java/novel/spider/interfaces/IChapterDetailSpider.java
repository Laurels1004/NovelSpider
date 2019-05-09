package novel.spider.interfaces;

import java.util.Iterator;
import java.util.List;

import novel.spider.entities.ChapterDetail;
import novel.spider.entities.Novel;

public interface IChapterDetailSpider {
	//获取一个url.返回一个对应网站章节内容实体
	public ChapterDetail getChapterDetail(String url);
	public ChapterDetail getNextChapterDetail(String url);
	
	//判断是否还有下一章,返回false代表没有下一页,true代表还有下一页
	public boolean hasNext();
	//如果hasNext返回true,则通过next()方法获取下一页的url
	public String next();
	/**
	 *  List<Novel> novels = new ArrayList<>();
	 *  假设novels中有很多元素
	 *  for (int index = 0,size = novels.size(); index < size; index++) {
	 *  	System.out.println("第"+index+"个元素是:"+novel);
	 *  }
	 *  for (Novel novel : novels) {
	 *  	System.out.println(novel);
	 *  }
	 *  //通过迭代器遍历数据
	 *  Iterator<Novel> iterator = novels.iterator();
	 *  while (iterator.hasNext()) {
	 *  	Novel novel = iterator.next();
	 *  	System.out.println(novel);
	 *  }
	 * */

}
