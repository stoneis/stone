<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>게시판 수정 폼</title>
<script type="text/javascript" src="<s:url value="/ckeditor/ckeditor.js" />"></script>
<style type="text/css">
	* {font-size: 9pt;}
	p {width: 600px; text-align: right;}
	table tbody tr th {background-color: gray;}
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
	<s:form name="boardModifyForm" action="boardModifyAction" method="post" onsubmit="return boardModifyCheck();" theme="simple">
	<s:hidden name="num" value="%{num}" />
	<s:hidden name="pageNum" value="%{pageNum}" />
	<s:hidden name="searchType" value="%{searchType}" />
	<s:hidden name="searchText" value="%{searchText}" />
	<table border="1" summary="게시판 수정 폼">
		<caption>게시판 수정 폼</caption>
		<colgroup>
			<col width="100" />
			<col width="500" />
		</colgroup>
		<tbody>
			<tr>
				<th align="center">제목</th>
				<td>
					<s:textfield name="subject" value="%{boardModel.subject}" size="80" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<th align="center">작성자</th>
				<td>
					<s:textfield name="writer" value="%{boardModel.writer}" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<s:textarea name="contents" cols="80" rows="10" value="%{boardModel.contents}"></s:textarea>
					<script>
					CKEDITOR.replace('contents');
					</script>
				</td>
			</tr>
		</tbody>
	</table>
	<p>
		<input type="button" value="목록" onclick="goUrl('boardListAction?pageNum=${boardModel.pageNum}&amp;searchType=${boardModel.searchType}&amp;searchText=${boardModel.searchText}');" />
		<input type="submit" value="글수정" />
	</p>
	</s:form>
</body>
</html>