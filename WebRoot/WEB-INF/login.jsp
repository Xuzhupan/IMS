<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/include.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>库存管理系统登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<script type="text/javascript">
		function submitForm(){
			$.ajax({
				url:"login_login.action",
				type:'post',
				dataType:'json',
				data:$('#loginform').serialize(),
				success:function(data){
					if(data.success){
					window.location.href = "index.action";
					}else{
						$.messager.alert("提示","用户名或密码错误");
					}
				}
			});
		}
		function signUp(){
			$('body').append('<div id="da"></div>');
			$('#da').dialog({
				title:'用户注册',
				width: 550,
				height:450,
				cache:false,
				href:'signUp.jsp',
				modal:true,
				buttons:[{
					text:'保存',
					handler:function(){
					
						if($("#regForm").form('validate')){
							$.ajax({
								type:'post',
								url:'reg.action',
								data:$('#regForm').serialize(),
								dataType:'json',
								success:function(data){
									if(data.success){
										$("#da").dialog('close');
									}
									$.messager.alert("系统提示",data.msg);
								}
							});
						}
					}
				},{
					text:'取消',
					handler:function(){
						$("#da").dialog('close');
					}
				}]
			});
		}
	</script>

  </head>
  
  <body>
	
	<div class="wrapper">

	<div class="container">
		<h1>库存管理系统</h1>
		<form class="form" id="loginform">
			<input type="text" placeholder="Username" name="username" value="cjgly">
			<input type="password" placeholder="Password" name="password" value="cjgly"><br>
			<button type="button" id="login-button" onclick="submitForm()"><strong>登陆</strong></button>
			
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		
	</ul>
	
</div>
  </body>
</html>
