package servlets.board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardModel;
import dao.board.BoardDAOImpl;
import dao.board.BoardHibernateDAO;

/**
 * 게시판 삭제 서블릿 클래스
 * @since 2013.07.09
 * @author stoneis.pe.kr
 */
@WebServlet("/board/boardDeleteServlet")
public class BoardDeleteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	/** BOARD DAO */
	private BoardDAOImpl boardDAO = null;
	
    public BoardDeleteServlet() {
        super();
    }

	/**
	 * GET 접근 시 (상세 조회 접근 시)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// 파라미터
		String num = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		String searchTextUTF8_E = URLEncoder.encode(searchTextUTF8, "UTF-8");
		// 모델
		BoardModel boardModel = new BoardModel();
		boardModel.setNum(Integer.parseInt(num));
		boardModel.setPageNum(pageNum);
		boardModel.setSearchType(searchType);
		boardModel.setSearchText(searchTextUTF8);	
		// 게시물 (BoardDAO : 일반 JDBC, BoardMyBatisDAO : Mybatis)
		this.boardDAO = new BoardHibernateDAO();
		// 게시물 삭제
		this.boardDAO.delete(boardModel);
		// 페이지 이동
		response.sendRedirect(
			"boardListServlet?pageNum="+pageNum+"&searchType="+searchType+"&searchText="+searchTextUTF8_E);
	}

}
