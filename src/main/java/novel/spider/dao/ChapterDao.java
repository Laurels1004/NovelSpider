package novel.spider.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import novel.spider.entities.Chapter;
//文章列表
public class ChapterDao {
	public SqlSession session = null;
	public ChapterDao(SqlSession sqlSession) {
		this.session = sqlSession;
	}
	
	public Chapter getChapterById(String id) {
		Chapter chapter = session.selectOne("novel.spider.mapper.NovelMapper.selectChapterById", id);
		return chapter;
	}
	
	public List<Chapter> selectAllChapters(){
		List<Chapter> list = session.selectList("novel.spider.mapper.NovelMapper.selectAllChapters");
		return list;
	}
	
	public void addChapter(Chapter chapter){
		session.insert("novel.spider.mapper.NovelMapper.addChapter", chapter);
		session.commit();
	}

}
