<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.board.BoardModel" %>
<%
	BoardModel boardModel = (BoardModel)request.getAttribute("boardModel");
	String searchText = boardModel.getSearchText();
	String searchType = boardModel.getSearchType();
	String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
	int pageNum = Integer.parseInt(boardModel.getPageNum());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>게시판 상세보기</title>
<style type="text/css">
	* {font-size: 9pt;}
	.btn_align {width: 600px; text-align: right;}
	table tbody tr th {background-color: gray;}
</style>
<script type="text/javascript">
	function goUrl(url) {
		location.href=url;
	}
	// 삭제 체크
	function deleteCheck(url) {
		if (confirm('정말 삭제하시겠어요?')) {
			location.href=url;
		}
	}
</script>
</head>
<body>
	<table border="1" summary="게시판 상세조회">
		<caption>게시판 상세조회</caption>
		<colgroup>
			<col width="100" />
			<col width="500" />
		</colgroup>
		<tbody>
			<tr>
				<th align="center">제목</th>
				<td><%=boardModel.getSubject()%></td>
			</tr>
			<tr>
				<th align="center">작성자/조회수</th>
				<td><%=boardModel.getWriter()%> / <%=boardModel.getHit()%></td>
			</tr>
			<tr>
				<th align="center">등록 일시</th>
				<td><%=boardModel.getRegDate()%></td>
			</tr>
			<tr>
				<td colspan="2"><%=boardModel.getContents()%></td>
			</tr>
		</tbody>
	</table>
	<p class="btn_align">
		<input type="button" value="목록" onclick="goUrl('<%=request.getContextPath()%>/board/boardListServlet?pageNum=<%=pageNum%>&amp;searchType=<%=searchType%>&amp;searchText=<%=searchText%>');" />
		<input type="button" value="수정" onclick="goUrl('<%=request.getContextPath()%>/board/boardModifyServlet?num=<%=boardModel.getNum()%>&amp;pageNum=<%=pageNum%>&amp;searchType=<%=searchType%>&amp;searchText=<%=searchText%>');" />
		<input type="button" value="삭제" onclick="deleteCheck('<%=request.getContextPath()%>/board/boardDeleteServlet?num=<%=boardModel.getNum()%>&amp;pageNum=<%=pageNum%>&amp;searchType=<%=searchType%>&amp;searchText=<%=searchText%>');" />
	</p>
</body>
</html>