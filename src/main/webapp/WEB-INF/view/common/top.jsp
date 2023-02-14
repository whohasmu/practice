<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head>

	<meta http-equiv="Content-Type" content="text/html; CHARSET=utf-8" />
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	
	<title>title</title>

<%--	<%@ include file="/WEB-INF/jsp/common/css.jsp" %>--%>
<%--	<%@ include file="/WEB-INF/jsp/common/javascripts.jsp" %>--%>

	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">

		// $(document).ready(function(){ }); : DOM이 완성된 이후에 호출되는 함수
		//	 DOM 트리만 완성되면 바로 발생하므로 빠른 실행속도가 필요할때 적합
		//	 여러번 사용 가능
		//  == $(function(){ });

		//  $(window).load(function(){ }); : img와 같은 다른 요소(모든페이지 구성요소)가 모두 load된 이후에 호출되는 함수
		//  	onload는 하나만 존재해야 한다
		//		== $(window).on("load"),function(){ });
		//		== window.onload(function(){ });
		$(window).load(function(){

		});

		$(document).ready(function(){

		});
	</script>
</head>
<body>
<!-- Ajax 로딩시 이미지 출력 영역 -->
<%--<div id="ajax_indicator" style="display:none"><p><img src="<%=_imgUrl %>common/loading2.gif" /></p></div>--%>
