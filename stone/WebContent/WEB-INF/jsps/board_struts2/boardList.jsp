<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
</head>
<body>
	<s:form name="searchForm" action="boardList" method="get" onsubmit="return searchCheck();" theme="simple">
	<p>
		<s:select name="searchType" list="#{'ALL':'전체검색','SUBJECT':'제목','WRITER':'작성자','CONTENTS':'내용'}" />
		<s:textfield name="searchText" />
		<s:submit value="검색" />
	</p>
	</s:form>
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
			<s:if test="%{totalCount == 0}">
			<tr>
				<td align="center" colspan="5">등록된 게시물이 없습니다.</td>
			</tr>
			</s:if>
			<s:else>
			<s:iterator value="boardList" status="status">
			<tr>
				<td align="center"><s:number name="totalCount  - (#status.index+1)+ 1 - (boardModel.pageNum - 1) * boardModel.listCount" type="integer"  /></td>
				<td><a href="boardView?num=${num}&pageNum=${pageNum}&amp;searchType=${boardModel.searchType}&amp;searchText=${boardModel.searchText}"><s:property value="subject" /></a></td>
				<td align="center"><s:property value="writer" /></td>
				<td align="center"><s:property value="regDate.substring(0,10)" /></td>
				<td align="center"><s:property value="hit" /></td>
			</tr>
			</s:iterator>
			</s:else>
		</tbody>
		<tfoot>
			<tr>
				<td align="center" colspan="5"><s:property value="pageNavigator" escape="false" /></td>
			</tr>
		</tfoot>
	</table>
	<p>
		<input type="button" value="목록" onclick="goUrl('<s:url value="boardList"/>');" />
		<input type="button" value="글쓰기" onclick="goUrl('<s:url value="boardWriteForm"/>');" />
	</p>
</body>
</html>