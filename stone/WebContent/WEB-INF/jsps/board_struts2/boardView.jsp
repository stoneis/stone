<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
				<td><s:property value="boardModel.subject" /></td>
			</tr>
			<tr>
				<th align="center">작성자/조회수</th>
				<td><s:property value="boardModel.writer" /> / <s:property value="boardModel.hit" /></td>
			</tr>
			<tr>
				<th align="center">등록 일시</th>
				<td><s:property value="boardModel.regDate" /></td>
			</tr>
			<tr>
				<td colspan="2"><s:property value="boardModel.contents" escape="false" /></td>
			</tr>
		</tbody>
	</table>
	<p class="btn_align">
		<input type="button" value="목록" onclick="goUrl('boardListAction?pageNum=${pageNum}&amp;searchType=${searchType}&amp;searchText=${searchText}');" />
		<input type="button" value="수정" onclick="goUrl('boardModifyFormAction?num=${num}&pageNum=${pageNum}&amp;searchType=${searchType}&amp;searchText=${searchText}');" />
		<input type="button" value="삭제" onclick="deleteCheck('boardDeleteAction?num=${num}&pageNum=${pageNum}&amp;searchType=${searchType}&amp;searchText=${searchText}');" />
	</p>
</body>
</html>