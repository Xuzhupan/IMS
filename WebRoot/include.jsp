<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!--
	作者：offline
	时间：2016-09-27
	描述：easyui主题
-->
<link rel="stylesheet" href="css/themes/bootstrap/easyui.css" />
<link rel="stylesheet" href="css/themes/icon.css" />

<!--
	作者：offline
	时间：2016-09-27
	描述：easyui基础包
-->
<script type="text/javascript" src="js/jquery-1.8.3.js" ></script>
<script type="text/javascript" src="js/jquery.easyui.min.js" ></script>
<!--
	作者：offline
	时间：2016-09-27
	描述：中文包
-->
<script type="text/javascript" src="js/easyui-lang-zh_CN.js" ></script>
<!--
	作者：offline
	时间：2016-09-27
	描述：easyui拓展
-->
<script type="text/javascript" src="js/extEasyUI.js" ></script>

<!--
	作者：offline
	时间：2016-09-27
	描述：jquery拓展
-->
<script type="text/javascript" src="js/extJquery.js" ></script>
<!--
	作者：offline
	时间：2016-09-27
	描述：highcharts
-->
<script type="text/javascript" src="js/highcharts.js" ></script>
<script type="text/javascript" src="js/highcharts-more.js" ></script>

<script type="text/javascript">
	function pagerFilter(data){
            if (typeof data.length == 'number' && typeof data.splice == 'function'){    // 判断数据是否是数组
                data = {
                    total: data.length,
                    rows: data
                }
            }
            var dg = $(this);
            var opts = dg.datagrid('options');
            var pager = dg.datagrid('getPager');
            pager.pagination({
                onSelectPage:function(pageNum, pageSize){
                    opts.pageNumber = pageNum;
                    opts.pageSize = pageSize;
                    pager.pagination('refresh',{
                        pageNumber:pageNum,
                        pageSize:pageSize
                    });
                    dg.datagrid('loadData',data);
                }
            });
            if (!data.originalRows){
                data.originalRows = (data.rows);
            }
            var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
            var end = start + parseInt(opts.pageSize);
            data.rows = (data.originalRows.slice(start, end));
            return data;
        }
        
        function formatterLocalTime(value,row,index){
			var time = new Date(value);
			return time.toLocaleDateString();
		}
        function formatterToSecond(value,row,index){
        	var time = new Date(value);
			return time.toLocaleDateString()+" "+time.getHours()+":"+time.getMinutes()+":"+time.getSeconds();
        }
        function sortDatetimer(a,b){
        	var time1 = new Date(a);
        	var time2 = new Date(b);
        	alert(time1);
        	return time1>time2;
        }
        function getRow(select,index){
        	 var rows = $('#'+select).datagrid('getRows');
        	 var row = rows[index];
        	 return row;
        }
        
        function RefreshTab(currentTab) {
    		var url = $(currentTab.panel('options')).attr('href');
    		parent.$('#tt').tabs('update', {
    			tab: currentTab,
    			options: {
    				href: url
    			}
    		});
    		currentTab.panel('refresh');
    	}
</script>
