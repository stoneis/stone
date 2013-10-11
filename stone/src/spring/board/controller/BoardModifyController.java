package spring.board.controller;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import model.board.BoardModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.board.BoardDAOImpl;

/**
 * 수정 컨트롤러 클래스
 * @since 2013.10.11
 * @author stoneis.pe.kr
 */
@Controller("boardModifyController")
public class BoardModifyController {
	
	@Resource(name="boardMyBatisDAO")
	private BoardDAOImpl boardDAO;
	
	@RequestMapping(value="/board/boardModify", method=RequestMethod.GET)
	public String boardModifyForm(HttpServletRequest request, BoardModel boardModel, 
			Model model) throws Exception {
		// 파라미터
		String searchText = request.getParameter("searchText");
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		boardModel.setSearchText(searchTextUTF8);
		// 게시물 상세 조회
		boardModel = this.boardDAO.select(boardModel);
		// View 사용될 객체 설정
		model.addAttribute("boardModel", boardModel);
		return "/board/boardModify";
	}
	
	@RequestMapping(value="/board/boardModify", method=RequestMethod.POST)
	public String boardModify(HttpServletRequest request, BoardModel boardModel, 
			Model model) throws Exception {
		// 파라미터
		String searchText = boardModel.getSearchText();
		String searchTextUTF8_E = URLEncoder.encode(searchText, "UTF-8");
		String ip = request.getRemoteAddr();
		boardModel.setIp(ip);
		// 게시물 수정
		this.boardDAO.update(boardModel);
		// 페이지 이동	
		return "redirect:boardView?num="+boardModel.getNum()+"&pageNum="+boardModel.getPageNum()+
				"&searchType="+boardModel.getSearchType()+"&searchText="+searchTextUTF8_E;
	}

}
