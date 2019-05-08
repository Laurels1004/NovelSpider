package novel.spider.impl.chapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import novel.spider.entities.Chapter;

//针对特殊(不规则)的章节列表进行排序处理
public class SpecialChapterSpider extends AbstractChapterSpider {
	public List<Chapter> getChapter(String url){
		List<Chapter> chapters = super.getChapter(url);
		Collections.sort(chapters, new Comparator<Chapter>() {
			@Override
			public int compare(Chapter o1, Chapter o2) {
				// TODO Auto-generated method stub
				//获取到需要比对的两个url地址
				String o1Url = o1.getUrl();
				String o2Url = o2.getUrl();
				String o1Index = o1Url.substring(o1Url.lastIndexOf('/')+1, o1Url.lastIndexOf('.'));
				String o2Index = o2Url.substring(o2Url.lastIndexOf('/')+1, o2Url.lastIndexOf('.'));
				return o1Index.compareTo(o2Index);
			}
		});
		return chapters;
	}
}
