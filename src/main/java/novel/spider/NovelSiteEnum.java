package novel.spider;
/**
 * 	支持爬取的小说网站枚举
 * */
public enum NovelSiteEnum {
	ShuQuGe(1,"shuquge.com"),
	BiQuGe(2,"biquge.tw"),
	BiXiaWenXue(3,"bixia.org");
	private int id;
	private String url;
	private NovelSiteEnum(int id, String url) {
		this.id = id;
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	// 通过传入id返回相应的网站枚举
	public static NovelSiteEnum getEnumById(int id) {
		switch (id) {
			case 1: return ShuQuGe;
			case 2:return BiQuGe;
			default:throw new RuntimeException("id="+id+"是不被支持的小说网站");
		}
	}
	
	// 通过传入的url返回相对应的网站枚举
	public static NovelSiteEnum getEnumByUrl(String url) {
		//遍历所有枚举
		for (NovelSiteEnum novelSiteEnum : values()) {
			//当传入url等于当前枚举url时返回当前枚举
			if (url.contains(novelSiteEnum.url)) {
				return novelSiteEnum;
			}
		}
		throw new RuntimeException("url="+url+"是不被支持的小说网站");
	}
}
