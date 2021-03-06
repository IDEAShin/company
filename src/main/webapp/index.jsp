<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>后台管理</title>
    <meta charset="UTF-8">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/b.tabs.css" type="text/css">
    <style type="text/css">
        div.menuSideBar { }
        div.menuSideBar li.nav-header { font-size: 14px; padding: 3px 15px; }
        div.menuSideBar .nav-list > li > a, div.menuSideBar .dropdown-menu li a { -webkit-border-radius: 0px; -moz-border-radius: 0px; -ms-border-radius: 0px; border-radius: 0px; }
    </style>

</head>
<body><script src="/demos/googlegg.js"></script>

<div class="content">
    <div class="container">
        <h3 class="page-header">公司人事后台管理系统</h3>
        <div class="">
            <div class="row-fluid">
                <div class="col-md-2" style="padding-left: 0px;">
                    <div class="well menuSideBar" style="padding: 8px 0px;">
                        <ul class="nav nav-list" id="menuSideBar">
                            <li class="nav-header">导航菜单</li>
                            <li class="nav-divider"></li>
                            <li mid="tab1" funurl="departmentList.do"><a tabindex="-1" href="javascript:void(0);">部门管理</a></li>
                            <li mid="tab2" funurl="employeeList.do"><a tabindex="-1" href="javascript:void(0);">员工管理</a></li>
                            <li mid="tab3" funurl="historyList.do"><a tabindex="-1" href="javascript:void(0);">员工变更管理</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-10" id="mainFrameTabs" style="padding : 0px;">

                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active noclose"><a href="#bTabs_navTabsMainPage" data-toggle="tab">首页</a></li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div class="tab-pane active" id="bTabs_navTabsMainPage">
                            <div class="page-header">
                                <h2 style="font-size: 31.5px;text-align: center;font-weight: normal; line-height:60px;">欢迎使用
                                    <br>公司人事后台管理系统
                                </h2>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/b.tabs.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/demo.js" ></script>

</body>
</html>
