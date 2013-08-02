package servlets.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardModel;
import util.PageNavigator;
import dao.board.BoardDAOImpl;
import dao.board.BoardHibernateDAO;

/**
 * 게시판 목록 페이지 서블릿 클래스
 * @since 2013.07.09
 * @author stoneis.pe.kr
 */
@WebServlet("/board/boardListServlet")
public class BoardListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	/** BOARD DAO */
	private BoardDAOImpl boardDAO = null;
    
    public BoardListServlet() {
        super();
    }

	/**
	 * GET 접근 시 (목록 조회 접근 시)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// 파라미터
		String pageNum = request.getParameter("pageNum");
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		if (pageNum == null) {
			pageNum = "1";
		}
		if (searchText == null) {
			searchType = "";
			searchText = "";
		}
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		// 모델
		BoardModel boardModel = new BoardModel();
		boardModel.setPageNum(pageNum);
		boardModel.setSearchType(searchType);
		boardModel.setSearchText(searchTextUTF8);
		// 게시물 (BoardDAO : 일반 JDBC, BoardMyBatisDAO : Mybatis)
		this.boardDAO = new BoardHibernateDAO();
		// 게시물 총 수
		int totalCount = this.boardDAO.selectCount(boardModel);
		// 게시물 목록을 얻는 쿼리 실행
		List<BoardModel> boardList = this.boardDAO.selectList(boardModel);
		// View 사용될 객체 설정
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("pageNavigator", new PageNavigator().getPageNavigator(
			totalCount, boardModel.getListCount(), boardModel.getPagePerBlock(), 
				Integer.parseInt(pageNum), searchType, searchTextUTF8));
		request.setAttribute("boardList", boardList);
		request.setAttribute("boardModel", boardModel);
		// View 보내기
		RequestDispatcher requestDispatcher =
			request.getRequestDispatcher("/WEB-INF/jsps/board/boardList.jsp");
		requestDispatcher.forward(request, response);
		
	}

}
