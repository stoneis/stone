package spring.board.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import model.board.BoardModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import util.PageNavigator;
import dao.board.BoardDAOImpl;

/**
 * 목록 컨트롤러 클래스
 * @since 2013.10.11
 * @author stoneis.pe.kr
 */
@Controller("boardListController")
public class BoardListController {
	
	@Resource(name="boardMyBatisDAO")
	private BoardDAOImpl boardDAO;
	
	@RequestMapping("/board/boardList")
	public String boardList(HttpServletRequest request, BoardModel boardModel, 
			Model model) throws Exception {
		// 파라미터
		String pageNum = boardModel.getPageNum();
		String searchType = boardModel.getSearchType();
		String searchText = boardModel.getSearchText();
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		boardModel.setSearchText(searchTextUTF8);
		// 게시물 총 수
		int totalCount = this.boardDAO.selectCount(boardModel);
		// 게시물 목록을 얻는 쿼리 실행
		List<BoardModel> boardList = this.boardDAO.selectList(boardModel);
		// View 사용될 객체 설정
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageNavigator", new PageNavigator().getPageNavigator(
			totalCount, boardModel.getListCount(), boardModel.getPagePerBlock(), 
				Integer.parseInt(pageNum), searchType, searchTextUTF8));
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardModel", boardModel);
		
		return "/board/boardList";
	}

}
