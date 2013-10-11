package struts2.board.action;

import java.util.List;

import model.board.BoardModel;
import util.PageNavigator;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import dao.board.BoardDAOImpl;
import dao.board.BoardHibernateDAO;

/**
 * 목록 조회 액션
 * @since 2013.09.03
 * @author stoneis.pe.kr
 */
public class BoardListAction extends ActionSupport implements Preparable, ModelDriven<BoardModel> {
	
	private static final long serialVersionUID = 1786040797141970275L;
	
	/** BOARD DAO */
	private BoardDAOImpl boardDAO = null;
	private BoardModel boardModel;
	private int totalCount = 0;
	private String pageNavigator = null;
	private List<BoardModel> boardList = null;
	
    public String execute() throws Exception {
		// 파라미터
		String pageNum = boardModel.getPageNum();
		String searchType = boardModel.getSearchType();
		String searchText = boardModel.getSearchText();
		if (pageNum == null) {
			pageNum = "1";
		}
		if (searchText == null) {
			searchType = "";
			searchText = "";
		}
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		// 모델
		boardModel.setPageNum(pageNum);
		boardModel.setSearchType(searchType);
		boardModel.setSearchText(searchTextUTF8);
		// 게시물 (BoardDAO : 일반 JDBC, BoardMyBatisDAO : Mybatis)
		this.boardDAO = new BoardHibernateDAO();
		// 게시물 총 수
		totalCount = this.boardDAO.selectCount(boardModel);
		// 게시물 목록을 얻는 쿼리 실행
		boardList = this.boardDAO.selectList(boardModel);
		// 페이지 네비게이터
		pageNavigator = new PageNavigator().getPageNavigator(
			totalCount, boardModel.getListCount(), boardModel.getPagePerBlock(), 
			Integer.parseInt(pageNum), searchType, searchTextUTF8);
        return SUCCESS;
    }

	public BoardModel getBoardModel() {
		return boardModel;
	}

	public void setBoardModel(BoardModel boardModel) {
		this.boardModel = boardModel;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getPageNavigator() {
		return pageNavigator;
	}

	public void setPageNavigator(String pageNavigator) {
		this.pageNavigator = pageNavigator;
	}

	public List<BoardModel> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<BoardModel> boardList) {
		this.boardList = boardList;
	}

	@Override
	public BoardModel getModel() {
		return boardModel;
	}

	@Override
	public void prepare() throws Exception {
		boardModel = new BoardModel();
	}

}
