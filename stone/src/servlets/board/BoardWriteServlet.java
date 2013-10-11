package servlets.board;

import java.io.IOException;

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
 * 게시판 등록폼, 등록처리 서블릿 클래스
 * @since 2013.07.09
 * @author stoneis.pe.kr
 */
@WebServlet("/servlet/board/boardWrite")
public class BoardWriteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	/** BOARD DAO */
	private BoardDAOImpl boardDAO = null;
    
    public BoardWriteServlet() {
        super();
    }

	/**
	 * GET 접근 시 (등록 폼 접근 시)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// View 보내기
		RequestDispatcher requestDispatcher =
			request.getRequestDispatcher("/WEB-INF/jsps/board/boardWrite.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * POST 접근 시 (등록처리 접근 시)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// POST 한글 파라미터 깨짐 처리
		request.setCharacterEncoding("UTF-8");
		// 파라미터
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		String ip = request.getRemoteAddr();
		// 모델
		BoardModel boardModel = new BoardModel();
		boardModel.setSubject(subject);
		boardModel.setWriter(writer);
		boardModel.setContents(contents);
		boardModel.setIp(ip);
		// 게시물 (BoardDAO : 일반 JDBC, BoardMyBatisDAO : Mybatis)
		this.boardDAO = new BoardHibernateDAO();
		// 게시물 등록
		this.boardDAO.insert(boardModel);
		// 페이지 이동
		response.sendRedirect("boardList");
	}

}
