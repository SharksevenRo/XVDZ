<%@ page language="java" contentType="text/xml; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${menuList }" var="menu" varStatus="index">
	<c:if test="${menu.childrenMenuList==null }">
		<li class="">
			<a href="#ajax/${menu.menuUrl}" data-toggle="ajax">
	</c:if>
	<c:if test="${menu.childrenMenuList!=null }">
		<li class="has-sub">
			<a href="javascript:void(0);">
				<b class="caret pull-right"></b>
	</c:if>
			<c:if test="${menu.menuIcon!=null&&menu.fatherMenuId==null}">
				<i class="${menu.menuIcon}"></i>
			</c:if>
				<span>${menu.menuName }</span>
			</a>
		<c:if test="${menu.childrenMenuList!=null }">
				<ul class="sub-menu">
					<c:set var="menuList" value="${menu.childrenMenuList}" scope="request"/>
					<c:import url="/admin/part/tree.jsp"></c:import>
				</ul>
		</c:if>
	</li>
</c:forEach>
