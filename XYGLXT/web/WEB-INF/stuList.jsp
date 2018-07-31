<%--
  Created by IntelliJ IDEA.
  User: Jay
  Date: 2018-6-14
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息列表</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#dg').datagrid({
                url: '${pageContext.request.contextPath}/Servlet',
                queryParams: {
                    m: 'findStudentByPages',
                },
                striped: true,
                fitColumns: true,
                pagination: true,
                pageSize: 10,
                pageList: [3, 5, 10, 20],
                columns: [[
                    {field: 'id', title: '序号', width: 100, hidden: true},
                    {field: 'stuid', title: '学号', width: 100, align: "center"},
                    {field: 'stuname', title: '姓名', width: 100, align: "center"},
                    {field: 'stuage', title: '年龄', width: 100, align: "center"},
                    {field: 'stuXL', title: '学历', width: 100, align: "center"},
                    {field: 'stusex', title: '性别', width: 100, align: 'center'}
                ]],
                toolbar: [{
                    iconCls: 'icon-add',
                    test: '添加',
                    handler: function () {
                        $('#add').dialog('center');
                        //alert('添加按钮')
                        $('#add').dialog('open');
                    }
                }, '-', {
                    iconCls: 'icon-edit',
                    handler: function () {
                        alert('编辑按钮')
                    }
                }, '-', {
                    iconCls: 'icon-remove',
                    handler: function () {
                        alert('删除按钮')
                    }
                }, '-', {
                    iconCls: 'icon-help',
                    handler: function () {
                        alert('帮助按钮')
                    }
                }]
            });
            $('#add').dialog({
                title: '添加信息',
                width: 400,
                height: 260,
                closed: true,
                cache: false,
                modal: true,
                buttons: [{
                    text: '保存',
                    handler: function () {
                        $('#aff').form('submit', {
                            url: '${pageContext.request.contextPath}/Servlet',
                            success: function (data) {
                                $.messager.alert(data);
                                $("#add").dialog('close');
                            }
                        });
                    }
                }, {
                    text: '关闭',
                    handler: function () {
                        $("#add").dialog('close');
                    }
                }]
            });
        })
    </script>
</head>
<body>
<div align="right"><font color="red">${msg}</font>,欢迎回来</div>
<br>
<center><font style="color: red;font-size: 20px">学生信息管理系统</font>
    <hr>
    <table id="dg" width="80%"></table>
</center>
<div id="add">
    <center>
        <form id="aff" method="post">
            <input type="hidden" name="m" value="add">
            <table cellpadding="5">
                <tr>
                    <td>学号:</td>
                    <td><input name="stuid" class="easyui-validatebox textbox" type="text"
                               data-options="required:true,novalidate:true">
                        <span><font color="red">*</font></span></td>
                </tr>
                <tr>
                    <td>姓名:</td>
                    <td><input name="stuname" class="easyui-validatebox textbox" type="text"
                               data-options="required:true,validType:'length[2,32]',novalidate:true">
                        <span><font color="red">*</font></span></td>
                </tr>
                <tr>
                    <td>年龄:</td>
                    <td><input name="stuage" class="easyui-validatebox textbox" type="text"
                               data-options="required:true,novalidate:true">
                        <span><font color="red">*</font></span></td>
                </tr>
                <tr>
                    <td>学历:</td>
                    <td><select name="stuXL" class="easyui-validatebox textbox">
                            <option value="博士">博士</option>
                            <option value="硕士">硕士</option>
                            <option value="本科">本科</option>
                            <option value="专科">专科</option>
                            <option value="高中">高中</option>
                            <option value="中专">中专</option>
                        </select></td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td><input type="radio" name="stusex" value="男">男
                        <input type="radio" name="stusex" value="女">女
                    </td>
                </tr>
            </table>
        </form>
    </center>
</div>

</body>
</html>
