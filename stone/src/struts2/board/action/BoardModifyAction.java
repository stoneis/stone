package struts2.board.action;

import model.board.BoardModel;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import dao.board.BoardDAOImpl;
import dao.board.BoardHibernateDAO;

/**
 * 수정 액션
 * @since 2013.09.03
 * @author stoneis.pe.kr
 */
public class BoardModifyAction extends ActionSupport implements Preparable, ModelDriven<BoardModel> {

	private static final long serialVersionUID = -1474479227539974717L;
	/** BOARD DAO */
	private BoardDAOImpl boardDAO = null;
	private BoardModel boardModel;
	
	/**
	 * 수정 처리
	 */
    public String execute() throws Exception {
		// 파라미터
		String searchText = boardModel.getSearchText();
		//String searchTextUTF8_E = URLEncoder.encode(searchText, "UTF-8");
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		// 모델
		boardModel.setIp(ip);
		boardModel.setSearchText(searchText);
		// 게시물 (BoardDAO : 일반 JDBC, BoardHibernateDAO : Mybatis)
		this.boardDAO = new BoardHibernateDAO();
		// 게시물 수정
		this.boardDAO.update(boardModel);
        return SUCCESS;
    }
    
    /**
     * 수정 폼
     * @return
     * @throws Exception
     */
    public String form() throws Exception {
		// 파라미터
		String searchText = getBoardModel().getSearchText();
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		// 모델
		getBoardModel().setSearchText(searchTextUTF8);
		// 게시물 (BoardDAO : 일반 JDBC, BoardHibernateDAO : Mybatis)
		this.boardDAO = new BoardHibernateDAO();
		// 게시물 상세 조회
		setBoardModel(this.boardDAO.select(getBoardModel()));
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
