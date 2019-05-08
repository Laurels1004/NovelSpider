package novel.spider.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.management.RuntimeErrorException;

import novel.spider.NovelSiteEnum;
import novel.spider.configuration.Configuration;
import novel.spider.entities.Chapter;
import novel.spider.entities.ChapterDetail;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.util.ChapterDetailSpiderFactory;
import novel.spider.util.ChapterSpiderFactory;
import novel.spider.util.NovelSpiderUtil;
/**
 *  实现多线程下载任意网站的小说
 * 1.拿到该网站的某本小说所有章节:章节列表
 * 2.通过计算,将这些章节分配给指定数量的线程,让他们去解析,然后保存到文本中
 * 3.通知主线程,将这些零散的小文件合并成一个大文件,最后将分片小文件删除
 * */
public class NovelDownload implements INovelDownload {

	@Override
	public String download(String url, Configuration config) {
		// TODO Auto-generated method stub
		//获取章节列表
		IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider(url);
		List<Chapter> chapters = chapterSpider.getChapter(url);
		//某个线程下载完毕之后,通知主线程下载完毕
		//所有线程都下载完毕后合并
		int size = config.getSize();
		// 2010章节 100章 需要21个线程
		//java中一个int÷int 结果只能是int; double÷double 结果double; double÷int结果double
		int maxThreadSize = (int) Math.ceil(chapters.size() * 1.0 / size);
		//给每个线程分配任务
		Map<String, List<Chapter>> downloadTaskAlloc = new HashMap<>();
		for (int i=0; i < maxThreadSize; i++) {
			// i=0 0-99;i=1 10-199;i=2 200-299
			int fromIndex = i*config.getSize();
			int toIndex = i == maxThreadSize-1 ? chapters.size() :i*(config.getSize())+config.getSize();
			//subList,从原有集合中拿出一段子集合,该子集合不能被修改
			downloadTaskAlloc.put(fromIndex+"-"+toIndex, chapters.subList(fromIndex, toIndex));
		}
		//创建线程池
		ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
		Set<String> keySet = downloadTaskAlloc.keySet();
		List<Future<String>> tasks = new ArrayList<>();
		
		//创建相关站点路径
		String savePath = config.getLocalpath()+"/"+NovelSiteEnum.getEnumByUrl(url).getUrl();
		//mkdirs创建多级路径
		new File(savePath).mkdirs();
		for (String key : keySet) {
			tasks.add(service.submit(new DownloadCallable(savePath+"/"+key+".txt",downloadTaskAlloc.get(key),config.getTryTimes())));
		}
		service.shutdown();
		for (Future<String> future:tasks) {
			try {
				System.out.println(future.get()+",下载完成!");
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		NovelSpiderUtil.mutiFileMerge(savePath, null, true);
		return savePath+"/merge.txt";
	}

}

class DownloadCallable implements Callable<String>{
	private List<Chapter> chapters;
	private String path;
	private int tryTimes;
	public DownloadCallable(String path, List<Chapter> chapters,int tryTimes) {
		// TODO Auto-generated constructor stub
		this.path = path;
		this.chapters = chapters;
		this.tryTimes = tryTimes;
	}
	@Override
	public String call() throws Exception {
		try (PrintWriter out = new PrintWriter(new File(path),"UTF-8")) {
			for (Chapter chapter : chapters) {
				IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(chapter.getUrl());
				ChapterDetail detail = null;
				for (int i = 0; i < tryTimes; i++) {
					try {
						detail = spider.getChapterDetail(chapter.getUrl());
						out.println(detail.getTitle());
						out.println(detail.getContent());
						break;
					} catch (RuntimeException e) {
						System.out.println("尝试第["+(i+1)+"/"+tryTimes+"]次下载失败了!");
					}	
				}
			}			
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		return path;
	}
	
}