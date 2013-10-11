package spring.board.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import model.board.BoardModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.board.BoardDAOImpl;

/**
 * 보기 컨트롤러 클래스
 * @since 2013.10.11
 * @author stoneis.pe.kr
 */
@Controller("boardViewController")
public class BoardViewController {
	
	@Resource(name="boardMyBatisDAO")
	private BoardDAOImpl boardDAO;
	
	@RequestMapping("/board/boardView")
	public String boardView(HttpServletRequest request, BoardModel boardModel, 
			Model model) throws Exception {
		// 파라미터
		String searchText = boardModel.getSearchText();
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		boardModel.setSearchText(searchTextUTF8);
		// 게시물 상세 조회
		boardModel = this.boardDAO.select(boardModel);
		// 게시물 조회수 증가
		this.boardDAO.updateHit(boardModel);
		// View 사용될 객체 설정
		model.addAttribute("boardModel", boardModel);
		return "/board/boardView";
	}

}
