<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
				<td><c:out value="${boardModel.subject}" /></td>
			</tr>
			<tr>
				<th align="center">작성자/조회수</th>
				<td><c:out value="${boardModel.writer}" /> / <c:out value="${boardModel.hit}" /></td>
			</tr>
			<tr>
				<th align="center">등록 일시</th>
				<td><c:out value="${boardModel.regDate}" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:out value="${boardModel.contents}" escapeXml="false" /></td>
			</tr>
		</tbody>
	</table>
	<p class="btn_align">
		<input type="button" value="목록" onclick="goUrl('<c:url value="/board/boardListServlet?pageNum=${boardModel.pageNum}&amp;searchType=${boardModel.searchType}&amp;searchText=${boardModel.searchText}" />');" />
		<input type="button" value="수정" onclick="goUrl('<c:url value="/board/boardModifyServlet?num=${boardModel.num}&pageNum=${boardModel.pageNum}&amp;searchType=${boardModel.searchType}&amp;searchText=${boardModel.searchText}" />');" />
		<input type="button" value="삭제" onclick="deleteCheck('<c:url value="/board/boardDeleteServlet?num=${boardModel.num}&pageNum=${boardModel.pageNum}&amp;searchType=${boardModel.searchType}&amp;searchText=${boardModel.searchText}" />');" />
	</p>
</body>
</html>