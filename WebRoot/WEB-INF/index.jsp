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
    
    <title>库存管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/default.css" />
		<style type="text/css">
			.menu_list{
				margin: 0px;
				padding:0px;
				list-style: none;
			}
			.menu_list li{
				margin: 5px;
				padding-left: 15px;
			}
			.menu_list li a{
				display: block;
				color: #000;
				text-decoration: none;
			}
			.menu_list li a:hover{
				background: #d1d1d1;
				color: #fff;
			}
			.menu_list li img{
				margin: 5px;
				padding:3px;
				vertical-align: middle;
			}
		</style>
	
	<script type="text/javascript">
        	$(document).ready(function(){
        		$.ajax({
        			type:'POST',
        			dataType:"json",
        			url:"Index_initMenu?roleId="+ '${user.role.id}',
        			success:function(data){
        				initLeftMenu(data);
        			}
        			
        		});
        		
        	getChar();
        		
        	});
        	function getChar(){
        		if(${permission.setLimit || permission.viewPurchasePlan}){
	        		$.getJSON('repertoryLimit_getChar',function(data){
	            		$('#container').highcharts(data);
	            	})
        		}
        	}
        	function reflushChar(){
        		$('#tt').tabs('getTab','欢迎').panel('refresh');
        	}
        	function loginOut(){
        	//(title,message,function(r){})
        		$.messager.confirm("系统提示","你确定要退出登录吗？",function(r){
        			if(r){
        				$.post('logout.d');
        				window.location.href = "login.jsp";
        			}
        		});
        	}
        	
        	function initLeftMenu(data){
        		$.each(data,function(i,n){
        			var menulist = '';
        			menulist +='<ul>';
        			//判断是否有子菜单
        			$.each(n.children,function(j,o){
        				menulist +='<li><div><a ref="'+o.name+'" href="javascript:void(0)" rel="'+o.url+'" icon="'+o.icon+'"><span class="icon'+o.icon+'">&nbsp;</span><span class="nav">'+o.name+'</span></a></div></li>';
        			});
        			menulist +='</ul>';
        			//添加标题
        			$('#aa').accordion('add',{
        				title:n.name,
        				content:menulist,
        				iconCls:'icon'+n.icon//可有可无
        			});
        		});
        		
        		$('.easyui-accordion li a').click(function(){//当单击菜单某个选项时，在中间出现对应的内容
        			var tabTitle = $(this).children('.nav').text();//获取超链接里span标签的内容
        			var url = $(this).attr("rel");
        			var menuid = $(this).attr("ref");
        			var icon = $(this).attr("icon");
        			//添加选项卡
        			addTab(tabTitle,url,icon);
        			$('.easyui-accordion li div').removeClass("selected");
        			$(this).parent().addClass("selected");
        		}).hover(function(){
        			$(this).parent().addClass("hover");
        		},function(){
        			$(this).parent().removeClass("hover");
        		});
        		
        		$('#aa').accordion({
        		
     				// collapsible: true,
    			});
        	}
        	
        	function addTab(tabTitle,url,icon){
        		if(!$('#tt').tabs('exists',tabTitle)){//选项卡是否已经打开
        			$('#tt').tabs('add',{
        				title:tabTitle,
        				//href:url,
        				content:'<iframe scrolling="auto" framebord="0" src="'+url+'" style="width:100%;height:100%;" scrolling="auto" frameborder="0"></iframe>',
        				closable:true,
        				icon:icon
        			});
        		}else{
        			$('#tt').tabs('select',tabTitle);
        			$('#mm-tabupdate').click();
        		}
        	}
        	function loginOut(){
        		$.messager.confirm("系统通知","确定要退出系统?",function(r){
        			if(r){
        				 location.href = "loginOut.action";
        			}
        		});
        	}
        	function updatePwd(){
        		$('body').append('<div id="da"></div>');
        		$('#da').dialog({
        			iconCls:'icon-add',
        			title:"修改密码",
        			width:300,
        			height:150,
        			cache:false,
        			modal:true,
        			href:'updatePwd',
        			buttons:[{
        				iconCls:'icon-save',
        				text:'确定',
        				handler:function(){
        					$('#updatePwd').form('submit',{
        						onSubmit:function(){
        							return $(this).form('enableValidation').form('validate');
        						},
        						success:function(data){
		        					$.ajax({
		        						type:'post',
		        						url:'login_updatePwd?username=${user.username}',
		        						data:$('#updatePwd').serialize(),
		        						dataType:'json',
		        						success:function(data){
		        							if(data.success){
		        								$('#da').dialog('close');
		        								$("#table").treegrid('reload');
		        								$.messager.show({title:"提示",msg:"修改成功"})
		        							}else{
		        								$.messager.show({title:"提示",msg:"修改失败，密码错误"})
		        							}
		        						}
		        					});
        						}
        					});
        				}
        			},{
        				iconCls:'icon-cancel',
        				text:'关闭',
        				handler:function(){
        					$('#da').dialog('close');
        				}
        			}]
        		});
        	}
	</script>
  </head>
  
  <body>
  	<div id="cc" class="easyui-layout" data-options = "fit:true">   
		    <div data-options="region:'north',title:'',split:false" 
		    	style="height:30px;overflow: hidden;
		    	background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
		    	line-height: 20px;color: #fff;font-family: Verdana,黑体 ">
		    	<span style="float:right;
		    	padding-right: 20px;" class="head">欢迎${user.role.name }
		    		<a onclick="updatePwd()">修改密码</a>
		    		<a onclick="loginOut()">安全退出</a>
		    	</span>
		    	<span style="padding-left: 45%;font-size: 16px;">
		    		库存管理系统
		    	</span>
		    </div>   
		    <div data-options="region:'south',title:'',split:false" style="height:30px;background: #d2e0f2;">
		    	<div class="footer"></div>
		    </div>   
		    <div data-options="region:'west',title:'目录',split:true" style="width:200px;">
		    	
		    	<div id="aa" class="easyui-accordion" data-options="fit:true">   
				      
				</div> 
		    </div>   
		    <div data-options="region:'center',title:''" style="padding:0px;background:#eee;">
		    	<div id="tt" class="easyui-tabs" data-options="fit:true">   
				    <div title="欢迎" style="padding:20px;display:none;">   
				        <div id="container"></div>   
				    </div>   
				</div> 
		    </div>   
		</div> 
</div>   

<div id="tab_rightmenu" class="easyui-menu" style="width:150px;">
	<div name="tab_menu-tabreflush">刷新</div>
	<div class="menu-sep"></div>
	<!-- <div name="tab_menu-tabclose">关闭</div> -->
	<div name="tab_menu-tabcloseall">关闭全部标签</div>
	<div name="tab_menu-tabcloseother">关闭其他标签</div>
	<div class="menu-sep"></div>
	<div name="tab_menu-tabcloseright">关闭右侧标签</div>
	<div name="tab_menu-tabcloseleft">关闭左侧标签</div>
</div>
<script type="text/javascript" src="js/closeTabs.js"></script>
 </body>
</html>
