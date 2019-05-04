package novel.spider.configuration;

import java.io.Serializable;

public class Configuration implements Serializable {
	/**
	 * 每个线程默认可下载最大章节数量 100
	 * */
	public static final int DEFAULT_SIZE = 100;
	/**
	 * 下载后文件保存的基地址 D:/novel /url/xxx.txt
	 * */
	private String localpath;
	/**
	 * 每个线程能够下载最大章节数量
	 * */
	private int size;
	
	public Configuration() {
		// TODO Auto-generated constructor stub
		this.size = DEFAULT_SIZE;
	}
	public String getLocalpath() {
		return localpath;
	}
	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
