package novel.spider.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import novel.spider.NovelSiteEnum;

public final class NovelSpiderUtil {
	private static final Map<NovelSiteEnum,Map<String,String>> CONTEXT_MAP = new HashMap<>();
	static {
		init();
	}
	
	private NovelSpiderUtil() {}
	
	@SuppressWarnings("unchecked")
	// 初始化，从配置文件中加载数据
	private static void init() {
		//通过SAXReader解析XML
		SAXReader xmlReader = new SAXReader();
		try {
			//使用dom4j对xml操作,读取Spider-Rules.xml文件
			Document doc = xmlReader.read(new File("conf/Spider-Rules.xml"));
			//获取xml文件根节点<sites>
			Element root = doc.getRootElement();
			//获取根节点下的所有site节点并存入List
			List<Element> sites = root.elements("site");
			//遍历sites列表
			for (Element site : sites) {
				// 将site节点中的所有子节点存入subs列表
				List<Element> subs = site.elements();
				// 定义一个名为subMap的HashMap集合,用于存入所有<site>的子节点相关数据
				Map<String,String> subMap = new HashMap<>();
				//遍历子节点subs列表
				for (Element sub:subs) {
					//获取到site节点下的各个子节点名,例如title url ...
					String name = sub.getName();
					//获取文本值,Trim用于删除空格
					String text = sub.getTextTrim();
					//数据存入HashMap
					subMap.put(name, text);
				}
				//存入CONTEXT_MAP,键-url,值-subMap(含有多个数据,titel-网站标题;url-www.xxx.xxx)
				CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")), subMap);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 获取到对应网站的解析规则
	public static Map<String,String> getContext(NovelSiteEnum novelSiteEnum){
		//通过传入的url获取相应的HashMap值
		return CONTEXT_MAP.get(novelSiteEnum);
	}
	
	/**
	 * 多个文件合并为一个文件,合并规则:按照文件名分割排序
	 * @param path基础目录,该目录下的所有文本文件都会被合并到mergeToFile
	 * @param mergeToFile 被合并的文本文件,这个参数可以 为null,为null合并后的文件保存在path/merge.txt
	 **/
	public static void mutiFileMerge(String path,String mergeToFile,boolean deleteThisFiles) {
		mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
		File[] files = new File(path).listFiles(new FilenameFilter() 
		{
			@Override
			public boolean accept(File dir, String name) {
				// 获取文件名：xx.txt
				return name.endsWith(".txt");
			}});
		//对文件进行排序
		Arrays.sort(files, new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				// TODO Auto-generated method stub
				int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
				int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
				if (o1Index > o2Index) {
					return 1;
				} else if(o1Index == o2Index){
					return 0;
				}else {
					return -1;
				}
			}
		});
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(mergeToFile),"UTF-8");
			for (File file : files) {
				BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				String line = null;
				while((line = buff.readLine()) != null) {
					out.println(line);
				}
				buff.close();
				if (deleteThisFiles) {
					file.delete();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			out.close();
		}

	}
	
}
