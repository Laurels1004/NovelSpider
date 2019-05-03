package novel.spider.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.entities.Chapter;
import novel.spider.interfaces.IChapterSpider;

public class AbstractChapterSpider implements IChapterSpider {
	protected String crawl(String url) throws Exception{
		/**
		 * 	通过静态类HttpClientBuilder的静态方法create,build后返回一个client实体
		 **/
		//创建HttpClient实体,创建get请求,请求结束后释放HttpResponse
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			   CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(url))) {
			String	result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			// 出错抛出运行异常
			throw new RuntimeException(e);
		}
		
//		CloseableHttpResponse httpResponse = null;
//		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
//			HttpGet httpGet = new HttpGet(url);
//			httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
//			httpGet.addHeader("Accept-Encoding", "gzip, deflate");
//			httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
//			httpGet.addHeader("Connection", "keep-alive");
//			httpGet.addHeader("Host", "www.shuquge.com");
//			httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
//			httpResponse = httpClient.execute(httpGet);
//			Header[] headers = httpResponse.getHeaders("Content-Encoding");
//				boolean isGzip = false;
//				for (Header h:headers) {
//					if (h.getValue().equals("gzip")) {
//						//返回头中含有gzip
//						isGzip = true;
//					}
//				}
//				String result = null;
//				if (isGzip) {
//					//需要进行gzip解压处理
//					result =  EntityUtils.toString(new GzipDecompressingEntity(httpResponse.getEntity()),"UTF-8");
//				} else {
//					result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
//				}
//				return result;
//			} catch (Exception e) {
//				// TODO: handle exception
//				// 出错抛出运行异常
//				throw new RuntimeException(e);
//			} finally {
//	            try {
//	                if (httpResponse != null) {
//	                	httpResponse.close();
//	                }
//	                httpResponse.close();
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            }
//			}
	}
	@Override
	public List<Chapter> getChapter(String url) {
		// TODO Auto-generated method stub
		try {
			String result = crawl(url);
			Document doc = Jsoup.parse(result);
			Elements as = doc.select(".listmain dd a");
			List<Chapter> chapters = new ArrayList<Chapter>();
			for (Element a:as) {
				Chapter chapter = new Chapter();
				chapter.setTitle(a.text());
				chapter.setUrl("http://www.shuquge.com/"+a.attr("href"));
				chapters.add(chapter);
			}
			return chapters;
			//System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
