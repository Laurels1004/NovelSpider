package novel.spider.impl;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import novel.spider.NovelSiteEnum;
import novel.spider.entities.ChapterDetail;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.util.NovelSpiderUtil;

public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider {

	@Override
	public ChapterDetail getChapterDetail(String url) {
		try {
			String result = super.crawl(url);
			//替换特殊字符
			result = result.replace("&nbsp;", "  ").replace("<br />", "\n").replace("<br/>", "\n");
			//通过jsoup解析结果
			Document doc = Jsoup.parse(result);
			//设置基准地址,将相对地址转为绝对地址
			doc.setBaseUri(url);
			//获取小说网站枚举
			Map<String,String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
			ChapterDetail detail = new ChapterDetail();
			String[] splits = null;
			
			//1.获取标题内容
			//1).获取配置文件中的标题选择器
			String titleSelector = context.get("detail-title-selector");
			//2).字符分割,不能分割的做特殊处理(选择器设为默认下标0)
			splits = titleSelector.split("\\,");
			splits = parseSelector(splits);
			//3).通过选择器选中对应节点,设置下标获取标题内容并写入
			detail.setTitle(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());
			
			//2.获取文章内容
			//1).获取配置文件中的文章内容选择器
			String contentSelector = context.get("detail-content-selector");
			//2).字符分割,不能分割的做特殊处理(选择器设为默认下标0)
			splits = contentSelector.split("\\,");
			splits = parseSelector(splits);
			//3).通过选择器选中对应节点,设置下标获取文章内容并写入
			detail.setContent(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());
			
			//3.获取前一章内容
			//1).获取配置文件中的前一章的选择器
			String prevSelector = context.get("detail-prev-selector");
			//2).字符分割,不能分割的做特殊处理(选择器设为默认下标0)
			splits = prevSelector.split("\\,");
			splits = parseSelector(splits);
			//3).通过选择器选中对应节点,设置下标获取前一章标题并写入
			//detail.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());
			detail.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			
			//3.获取下一章内容
			//1).获取配置文件中的下一章的选择器
			String nextSelector = context.get("detail-prev-selector");
			//2).字符分割,不能分割的做特殊处理(选择器设为默认下标0)
			splits = nextSelector.split("\\,");
			splits = parseSelector(splits);
			//3).通过选择器选中对应节点,设置下标获取前一章标题并写入
			//detail.setNext(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());
			detail.setNext(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
		
			return detail;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//定义解析选择器方法
	private String[] parseSelector(String[] splits) {
		String[] newSplits = new String[2];
		//如果传来的选择器长度为1,设置下标默认为0,不为0则说明传递有下标了
		if (splits.length == 1) {
			newSplits[0] = splits[0];
			newSplits[1] = "0";
			return newSplits;
		} else {
			return splits;
		}
	}
}
