package struts2.board.action;

import model.board.BoardModel;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import dao.board.BoardDAOImpl;
import dao.board.BoardHibernateDAO;

/**
 * 등록 액션
 * @since 2013.09.03
 * @author stoneis.pe.kr
 */
public class BoardWriteAction extends ActionSupport implements Preparable, ModelDriven<BoardModel> {

	private static final long serialVersionUID = -7690786777811429722L;
	/** BOARD DAO */
	private BoardDAOImpl boardDAO = null;
	private BoardModel boardModel;
	
	/**
	 * 등록 처리
	 */
    public String execute() throws Exception {
		// 파라미터
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		// 모델
		boardModel.setIp(ip);
		// 게시물 (BoardDAO : 일반 JDBC, BoardMyBatisDAO : Mybatis)
		this.boardDAO = new BoardHibernateDAO();
		// 게시물 등록
		this.boardDAO.insert(boardModel);
        return SUCCESS;
    }

    /**
     * 등록 폼
     * @return
     * @throws Exception
     */
    public String form() throws Exception {
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
