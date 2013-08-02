package dao.board;

import java.util.List;

import model.board.BoardModel;

/**
 * 게시판 DAO 인터페이스
 * @since 2013.07.09
 * @author stoneis.pe.kr
 */
public interface BoardDAOImpl {
	
	/**
	 * 게시판 목록 조회
	 * @param boardModel
	 * @return
	 */
	public List<BoardModel> selectList(BoardModel boardModel);
	
	/**
	 * 게시판 수 조회
	 * @param boardModel
	 * @return
	 */
	public int selectCount(BoardModel boardModel);
	
	/**
	 * 게시판 상세 조회
	 * @param boardModel
	 * @return
	 */
	public BoardModel select(BoardModel boardModel);
	
	/**
	 * 게시판 등록 처리
	 * @param boardModel
	 */
	public void insert(BoardModel boardModel);
	
	/**
	 * 게시판 수정 처리
	 * @param boardModel
	 */
	public void update(BoardModel boardModel);
	
	/**
	 * 게시판 조회수 증가 수정 처리
	 * @param boardModel
	 */
	public void updateHit(BoardModel boardModel);
	
	/**
	 * 게시판 삭제 처리
	 * @param boardModel
	 */
	public void delete(BoardModel boardModel);

}
