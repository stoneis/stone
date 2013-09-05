package struts2.board.action;

import model.board.BoardModel;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import dao.board.BoardDAOImpl;
import dao.board.BoardHibernateDAO;

/**
 * 보기 액션
 * @since 2013.09.03
 * @author stoneis.pe.kr
 */
public class BoardViewAction extends ActionSupport implements Preparable, ModelDriven<BoardModel> {

	private static final long serialVersionUID = -8770284777146941394L;
	/** BOARD DAO */
	private BoardDAOImpl boardDAO = null;
	private BoardModel boardModel;
	
    public String execute() throws Exception {
    	// 파라미터
		String searchText = boardModel.getSearchText();
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		// 모델
		boardModel.setSearchText(searchTextUTF8);
		// 게시물 (BoardDAO : 일반 JDBC, BoardMyBatisDAO : Mybatis)
		this.boardDAO = new BoardHibernateDAO();
		// 게시물 상세 조회
		boardModel = this.boardDAO.select(boardModel);
		// 게시물 조회수 증가
		this.boardDAO.updateHit(boardModel);
        return SUCCESS;
    }

	@Override
	public BoardModel getModel() {
		return getBoardModel();
	}

	@Override
	public void prepare() throws Exception {
		setBoardModel(new BoardModel());
	}

	public BoardModel getBoardModel() {
		return boardModel;
	}

	public void setBoardModel(BoardModel boardModel) {
		this.boardModel = boardModel;
	}

}
