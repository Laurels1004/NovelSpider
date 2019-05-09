package novel.spider.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import novel.spider.entities.Chapter;
import novel.spider.entities.Novel;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class NovelDao {

	public SqlSession session = null;
	public NovelDao(SqlSession sqlSession) {
		this.session = sqlSession;
	}

	public Novel getNovelById(String id) {
		Novel novel = session.selectOne("novel.spider.mapper.NovelMapper.selectNovelById", id);
		return novel;
	}

	public List<Novel> selectAllNovels(){
		List<Novel> list = session.selectList("novel.spider.mapper.NovelMapper.selectAllNovels");
		return list;
	}

	public void addNovel(Novel novel){
		session.insert("novel.spider.mapper.NovelMapper.addNovel", novel);
		session.commit();
	}

}
