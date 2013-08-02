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
<title>게시판 수정 폼</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<style type="text/css">
	* {font-size: 9pt;}
	p {width: 600px; text-align: right;}
	table tbody tr th {background-color: gray;} 법
</style>
<script type="text/javascript">
	function goUrl(url) {
		location.href=url;
	}
	// 수정 폼 체크
	function boardModifyCheck() {
		var form = document.boardModifyForm;
		if (form.subject.value == '') {
			alert('제목을 입력하세요.');
			form.subject.focus();
			return false;
		}
		if (form.writer.value == '') {
			alert('작성자을 입력하세요');
			form.writer.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form name="boardModifyForm" action="<%=request.getContextPath() %>/board/boardModifyServlet" method="post" onsubmit="return boardModifyCheck();">
	<input type="hidden" name="num" value="<%=boardModel.getNum()%>" />
	<input type="hidden" name="pageNum" value="<%=pageNum %>" />
	<input type="hidden" name="searchType" value="<%=searchType %>" />
	<input type="hidden" name="searchText" value="<%=searchText %>" />
	<table border="1" summary="게시판 수정 폼">
		<caption>게시판 수정 폼</caption>
		<colgroup>
			<col width="100" />
			<col width="500" />
		</colgroup>
		<tbody>
			<tr>
				<th align="center">제목</th>
				<td><input type="text" name="subject" size="80" maxlength="100" value="<%=boardModel.getSubject()%>" /></td>
			</tr>
			<tr>
				<th align="center">작성자</th>
				<td><input type="text" name="writer" maxlength="20" value="<%=boardModel.getWriter()%>" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea name="contents" cols="80" rows="10"><%=boardModel.getContents()%></textarea>
					<script>
					CKEDITOR.replace('contents');
					</script>
				</td>
			</tr>
		</tbody>
	</table>
	<p>
		<input type="button" value="목록" onclick="goUrl('<%=request.getContextPath()%>/board/boardListServlet?pageNum=<%=pageNum%>&amp;searchType=<%=searchType%>&amp;searchText=<%=searchText%>');" />
		<input type="submit" value="글수정" />
	</p>
	</form>
</body>
</html>