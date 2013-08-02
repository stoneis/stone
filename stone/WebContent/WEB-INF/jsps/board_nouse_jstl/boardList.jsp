<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.board.BoardModel, java.util.List" %>
<%
	List<BoardModel> boardList = (List<BoardModel>)request.getAttribute("boardList");
	BoardModel boardModel = (BoardModel)request.getAttribute("boardModel");
	int totalCount = (Integer)request.getAttribute("totalCount");
	String searchText = boardModel.getSearchText();
	String searchType = boardModel.getSearchType();
	String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
	int pageNum = Integer.parseInt(boardModel.getPageNum());
	int listCount = boardModel.getListCount();
	int pagePerBlock = boardModel.getPagePerBlock();
	String pageNavigator = (String)request.getAttribute("pageNavigator");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>게시판 목록</title>
<style type="text/css">
	* {font-size: 9pt;}
	p {width: 600px; text-align: right;}
	table thead tr th {background-color: gray;}
</style>
<script type="text/javascript">
	function goUrl(url) {
		location.href=url;
	}
	// 검색 폼 체크
	function searchCheck() {
		var form = document.searchForm;
		if (form.searchText.value == '') {
			alert('검색어를 입력하세요.');
			form.searchText.focus();
			return false;
		}
		return true;
	}
</script>
<%

%>
</head>
<body>
	<form name="searchForm" action="" method="get" onsubmit="return searchCheck();" >
	<p>
		<select name="searchType">
			<option value="ALL" selected="selected">전체검색</option>
			<option value="SUBJECT" <%if ("SUBJECT".equals(searchType)) out.print("selected=\"selected\""); %>>제목</option>
			<option value="WRITER" <%if ("WRITER".equals(searchType)) out.print("selected=\"selected\""); %>>작성자</option>
			<option value="CONTENTS" <%if ("CONTENTS".equals(searchType)) out.print("selected=\"selected\""); %>>내용</option>
		</select>
		<input type="text" name="searchText" value="<%=searchTextUTF8%>" />
		<input type="submit" value="검색" />
	</p>
	</form>
	<table border="1" summary="게시판 목록">
		<caption>게시판 목록</caption>
		<colgroup>
			<col width="50" />
			<col width="300" />
			<col width="80" />
			<col width="100" />
			<col width="70" />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록 일시</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<%
			if (totalCount == 0) {
			%>
			<tr>
				<td align="center" colspan="5">등록된 게시물이 없습니다.</td>
			</tr>
			<%
			} else {
				for (int i=0, size=boardList.size(); i<size; i++) {
					BoardModel board = boardList.get(i);
			%>
			<tr>
				<td align="center"><%=totalCount - (i+1) + 1 - (pageNum - 1) * listCount %></td>
				<td><a href="boardViewServlet?num=<%=board.getNum()%>&amp;pageNum=<%=pageNum%>&amp;searchType=<%=searchType%>&amp;searchText=<%=searchText%>"><%=board.getSubject()%></a></td>
				<td align="center"><%=board.getWriter()%></td>
				<td align="center"><%=board.getRegDate().substring(0, 10) %></td>
				<td align="center"><%=board.getHit()%></td>
			</tr>
			<%
				}
			}
			%>
		</tbody>
		<tfoot>
			<tr>
				<td align="center" colspan="5"><%=pageNavigator%></td>
			</tr>
		</tfoot>
	</table>
	<p>
		<input type="button" value="목록" onclick="goUrl('<%=request.getContextPath()%>/board/boardListServlet');" />
		<input type="button" value="글쓰기" onclick="goUrl('<%=request.getContextPath()%>/board/boardWriteServlet');" />
	</p>
</body>
</html>