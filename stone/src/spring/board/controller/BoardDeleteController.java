package spring.board.controller;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import model.board.BoardModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.board.BoardDAOImpl;

/**
 * 삭제 컨트롤러 클래스
 * @since 2013.10.11
 * @author stoneis.pe.kr
 */
@Controller("boardDeleteController")
public class BoardDeleteController {
	
	@Resource(name="boardMyBatisDAO")
	private BoardDAOImpl boardDAO;
	
	@RequestMapping("/board/boardDelete")
	public String boardDelete(HttpServletRequest request, BoardModel boardModel, 
			Model model) throws Exception {
		// 파라미터
		String searchText = boardModel.getSearchText();
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		String searchTextUTF8_E = URLEncoder.encode(searchTextUTF8, "UTF-8");		
		boardModel.setSearchText(searchTextUTF8);	
		// 게시물 삭제
		this.boardDAO.delete(boardModel);
		return "redirect:boardList?pageNum="+boardModel.getPageNum()+
				"&searchType="+boardModel.getSearchType()+"&searchText="+searchTextUTF8_E;
	}

}
