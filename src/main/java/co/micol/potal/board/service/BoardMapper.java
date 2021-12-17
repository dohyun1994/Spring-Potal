package co.micol.potal.board.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BoardMapper {
	List<BoardVO> boardSelectList();
	BoardVO boardSelect(BoardVO vo);
	int boardInsert(BoardVO vo);
	int boardUpdate(BoardVO vo);
	int boardDelete(BoardVO vo);
	void boardHit(int n);
	
	// mybatis에서 파라미터를 2개 이상 전달하려면, @Param 어노테이션을 사용.
	List<BoardVO> boardSearchList(@Param("key") String key, @Param("val") String val);  // 게시글 검색을 위함
	void boardNoupdate(int n);		//글 삭제 후 번호를 맞추기 위해
}
