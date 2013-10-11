package servlets.board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardModel;
import dao.board.BoardDAOImpl;
import dao.board.BoardHibernateDAO;

/**
 * 게시판 수정폼, 수정처리 서블릿 클래스
 * @since 2013.07.09
 * @author stoneis.pe.kr
 */
@WebServlet("/servlet/board/boardModify")
public class BoardModifyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	/** BOARD DAO */
	private BoardDAOImpl boardDAO = null;
    
    public BoardModifyServlet() {
        super();
    }

	/**
	 * GET 접근 시 (수정폼 접근 시)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// 파라미터
		String num = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		// 모델
		BoardModel boardModel = new BoardModel();
		boardModel.setNum(Integer.parseInt(num));
		boardModel.setPageNum(pageNum);
		boardModel.setSearchType(searchType);
		boardModel.setSearchText(searchTextUTF8);
		// 게시물 (BoardDAO : 일반 JDBC, BoardHibernateDAO : Mybatis)
		this.boardDAO = new BoardHibernateDAO();
		// 게시물 상세 조회
		boardModel = this.boardDAO.select(boardModel);
		// View 사용될 객체 설정
		request.setAttribute("boardModel", boardModel);
		// View 보내기
		RequestDispatcher requestDispatcher =
				request.getRequestDispatcher("/WEB-INF/jsps/board/boardModify.jsp");
			requestDispatcher.forward(request, response);
	}

	/**
	 * POST 접근 시 (수정처리 접근 시)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// POST 한글 파라미터 깨짐 처리
		request.setCharacterEncoding("UTF-8");
		// 파라미터
		String num = request.getParameter("num");
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		String pageNum = request.getParameter("pageNum");
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		String searchTextUTF8_E = URLEncoder.encode(searchText, "UTF-8");
		String ip = request.getRemoteAddr();
		// 모델
		BoardModel boardModel = new BoardModel();
		boardModel.setNum(Integer.parseInt(num));
		boardModel.setSubject(subject);
		boardModel.setWriter(writer);
		boardModel.setContents(contents);
		boardModel.setIp(ip);
		boardModel.setPageNum(pageNum);
		boardModel.setSearchType(searchType);
		boardModel.setSearchText(searchText);
		// 게시물 (BoardDAO : 일반 JDBC, BoardHibernateDAO : Mybatis)
		this.boardDAO = new BoardHibernateDAO();
		// 게시물 수정
		this.boardDAO.update(boardModel);
		// 페이지 이동	
		response.sendRedirect(
			"boardView?num="+num+"&pageNum="+pageNum+"&searchType="+searchType+"&searchText="+searchTextUTF8_E);
	}

}
