package novel.spider.entities;

import java.io.Serializable;
import java.util.Date;
//小说实体
public class Novel implements Serializable {
	//书名
	private String name;
	//作者名
	private String author;
	//小说链接
	private String url;
	//小说描述
	
	public Novel() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Novel [name=" + name + ", author=" + author + ", url=" + url + "]";
	}
	

}
