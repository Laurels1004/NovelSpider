package novel.spider.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import novel.spider.entities.ChapterDetail;

public class ChapterDetailDao {

	public SqlSession session = null;
	public  ChapterDetailDao(SqlSession sqlSession) {
		this.session = sqlSession;
	}

	public ChapterDetail getDetailById(int i) {
		ChapterDetail fiction = session.selectOne("novel.spider.mapper.NovelMapper.selectChapterDetailById", i);
		return fiction;
	}

	public List<ChapterDetail> selectAllFictions(){
		List<ChapterDetail> list = session.selectList("novel.spider.mapper.NovelMapper.selectAllChapterDetail");
		return list;
	}

	public void addChapterDetail(ChapterDetail chapterdetail){
		session.insert("novel.spider.mapper.NovelMapper.addChapterDetail", chapterdetail);
		session.commit();
	}

}
