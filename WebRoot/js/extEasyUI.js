/**
 * 使panel和datagrid在加载时提示
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.fn.panel.defaults.loadingMessage = '加载中....';
$.fn.datagrid.defaults.loadMsg = '加载中....';

/**
 * @requires jQuery,EasyUI
 * 
 * panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 */
$.fn.panel.defaults.onBeforeDestroy = function() {
	var frame = $('iframe', this);
	try {
		if (frame.length > 0) {
			for ( var i = 0; i < frame.length; i++) {
				frame[i].src = '';
				frame[i].contentWindow.document.write('');
				frame[i].contentWindow.close();
			}
			frame.remove();
			if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
				try {
					CollectGarbage();
				} catch (e) {
				}
			}
		}
	} catch (e) {
	}
};

/**
 *  
 * 
 * @requires jQuery,EasyUI
 * 
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;

/**
 *  
 * 
 * @requires jQuery,EasyUI
 * 
 * 通用错误提示
 * 
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 */
var easyuiErrorFunction = function(XMLHttpRequest) {
	parent.$.messager.alert('错误', XMLHttpRequest.responseText);
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;

/**
 *  
 * 
 * @requires jQuery,EasyUI
 * 
 * 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
 */
var createGridHeaderContextMenu = function(e, field) {
	e.preventDefault();
	var grid = $(this);/* grid本身 */
	var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
	if (!headerContextMenu) {
		var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
		var fields = grid.datagrid('getColumnFields');
		for ( var i = 0; i < fields.length; i++) {
			var fildOption = grid.datagrid('getColumnOption', fields[i]);
			if (!fildOption.hidden) {
				$('<div iconCls="tick" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
			} else {
				$('<div iconCls="bullet_blue" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
			}
		}
		headerContextMenu = this.headerContextMenu = tmenu.menu({
			onClick : function(item) {
				var field = $(item.target).attr('field');
				if (item.iconCls == 'tick') {
					grid.datagrid('hideColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'bullet_blue'
					});
				} else {
					grid.datagrid('showColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'tick'
					});
				}
			}
		});
	}
	headerContextMenu.menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;

/**
 * grid tooltip参数
 * 
 *  
 */
var gridTooltipOptions = {
	tooltip : function(jq, fields) {
		return jq.each(function() {
			var panel = $(this).datagrid('getPanel');
			if (fields && typeof fields == 'object' && fields.sort) {
				$.each(fields, function() {
					var field = this;
					bindEvent($('.datagrid-body td[field=' + field + '] .datagrid-cell', panel));
				});
			} else {
				bindEvent($(".datagrid-body .datagrid-cell", panel));
			}
		});

		function bindEvent(jqs) {
			jqs.mouseover(function() {
				var content = $(this).text();
				if (content.replace(/(^\s*)|(\s*$)/g, '').length > 5) {
					$(this).tooltip({
						content : content,
						trackMouse : true,
						position : 'bottom',
						onHide : function() {
							$(this).tooltip('destroy');
						},
						onUpdate : function(p) {
							var tip = $(this).tooltip('tip');
							if (parseInt(tip.css('width')) > 500) {
								tip.css('width', 500);
							}
						}
					}).tooltip('show');
				}
			});
		}
	}
};
/**
 * Datagrid扩展方法tooltip 基于Easyui 1.3.3，可用于Easyui1.3.3+
 * 
 * 简单实现，如需高级功能，可以自由修改
 * 
 * 使用说明:
 * 
 * 在easyui.min.js之后导入本js
 * 
 * 代码案例:
 * 
 * $("#dg").datagrid('tooltip'); 所有列
 * 
 * $("#dg").datagrid('tooltip',['productid','listprice']); 指定列
 * 
 *  
 */
$.extend($.fn.datagrid.methods, gridTooltipOptions);

/**
 * Treegrid扩展方法tooltip 基于Easyui 1.3.3，可用于Easyui1.3.3+
 * 
 * 简单实现，如需高级功能，可以自由修改
 * 
 * 使用说明:
 * 
 * 在easyui.min.js之后导入本js
 * 
 * 代码案例:
 * 
 * $("#dg").treegrid('tooltip'); 所有列
 * 
 * $("#dg").treegrid('tooltip',['productid','listprice']); 指定列
 * 
 *  
 */
$.extend($.fn.treegrid.methods, gridTooltipOptions);

/**
 *  
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展validatebox，添加验证两次密码功能
 */
$.extend($.fn.validatebox.defaults.rules, {
	eqPwd : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	},
	chs : {
		validator : function(value, param){
			return /^[\u4e00-\u9fa5]+$/.test(value);
		},
		message : '请输入汉字'
	},
	idcard: {
        validator: function (value, param) {
            return idCard(value);
        },
        message:'请输入正确的身份证号码'
    }
});

var idCard = function (value) {
    if (value.length == 18 && 18 != value.length) return false;
    var number = value.toLowerCase();
    var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
    var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
    if (re == null || a.indexOf(re[1]) < 0) return false;
    if (re[2].length == 9) {
        number = number.substr(0, 6) + '19' + number.substr(6);
        d = ['19' + re[4], re[5], re[6]].join('-');
    } else d = [re[9], re[10], re[11]].join('-');
    if (!isDateTime.call(d, 'yyyy-MM-dd')) return false;
    for (var i = 0; i < 17; i++) sum += number.charAt(i) * w[i];
    return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
};
var isDateTime = function (format, reObj) {
    format = format || 'yyyy-MM-dd';
    var input = this, o = {}, d = new Date();
    var f1 = format.split(/[^a-z]+/gi), f2 = input.split(/\D+/g), f3 = format.split(/[a-z]+/gi), f4 = input.split(/\d+/g);
    var len = f1.length, len1 = f3.length;
    if (len != f2.length || len1 != f4.length) return false;
    for (var i = 0; i < len1; i++) if (f3[i] != f4[i]) return false;
    for (var i = 0; i < len; i++) o[f1[i]] = f2[i];
    o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
    o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
    o.dd = s(o.dd, o.d, d.getDate(), 31);
    o.hh = s(o.hh, o.h, d.getHours(), 24);
    o.mm = s(o.mm, o.m, d.getMinutes());
    o.ss = s(o.ss, o.s, d.getSeconds());
    o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
    if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0) return false;
    if (o.yyyy < 100) o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
    d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
    var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM && d.getDate() == o.dd && d.getHours() == o.hh && d.getMinutes() == o.mm && d.getSeconds() == o.ss && d.getMilliseconds() == o.ms;
    return reVal && reObj ? d : reVal;
    function s(s1, s2, s3, s4, s5) {
        s4 = s4 || 60, s5 = s5 || 2;
        var reVal = s3;
        if (s1 != undefined && s1 != '' || !isNaN(s1)) reVal = s1 * 1;
        if (s2 != undefined && s2 != '' && !isNaN(s2)) reVal = s2 * 1;
        return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
    }
};
//扩展tree，使其可以获取实心节点
$.extend($.fn.tree.methods, {
	getCheckedExt : function(jq) {// 获取checked节点(包括实心)
		var checked = $(jq).tree("getChecked");
		var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
		$.each(checkbox2, function() {
			var node = $.extend({}, $.data(this, "tree-node"), {
				target : this
			});
			checked.push(node);
		});
		return checked;
	},
	getSolidExt : function(jq) {// 获取实心节点
		var checked = [];
		var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
		$.each(checkbox2, function() {
			var node = $.extend({}, $.data(this, "tree-node"), {
				target : this
			});
			checked.push(node);
		});
		return checked;
	}
});

//扩展tree，使其支持平滑数据格式
$.fn.tree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

// 扩展treegrid，使其支持平滑数据格式
$.fn.treegrid.defaults.loadFilter = function(data, parentId) {
	var opt = $(this).data().treegrid.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

// 扩展combotree，使其支持平滑数据格式
$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;

/**
 * 
 * @requires jQuery,EasyUI
 * 
 * 创建一个模式化的dialog
 * 
 * @returns $.modalDialog.handler 这个handler代表弹出的dialog句柄
 * 
 * @returns $.modalDialog.xxx 这个xxx是可以自己定义名称，主要用在弹窗关闭时，刷新某些对象的操作，可以将xxx这个对象预定义好
 */
$.modalDialog = function(options) {
	if ($.modalDialog.handler == undefined) {// 避免重复弹出
		var opts = $.extend({
			title : '',
			width : 840,
			height : 680,
			modal : true,
			onClose : function() {
				$.modalDialog.handler = undefined;
				$(this).dialog('destroy');
			},
			onOpen : function() {
			}
		}, options);
		opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
		return $.modalDialog.handler = $('<div/>').dialog(opts);
	}
};

function convert(rows){
    function exists(rows, parentId){
        for(var i=0; i<rows.length; i++){
            if (rows[i].id == parentId) return true;
        }
        return false;
    }
    
    var nodes = [];
    // 获取顶级的node
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        if (!exists(rows, row.parentId)){
            /**
            gys    给顶层添加链接
            **/
            var topNode={id:row.id,text:row.name,url:row.url};                
            nodes.push(topNode);
            
            /* nodes.push({
                id:row.id,
                text:row.name
            }); */
        }
    }
    
    var toDo = [];
    for(var i=0; i<nodes.length; i++){
        toDo.push(nodes[i]);
    }
    while(toDo.length){//循环toDo当toDo长度为零时停止
        var node = toDo.shift();//删除第一个元素,然后返回第一个元素,改变数组长度    
        // 获取子节点
        for(var i=0; i<rows.length; i++){
            var row = rows[i];
            if (row.parentId == node.id){
                var child = {id:row.id,text:row.name};
                /**
                gys 添加链接
                **/
              if(row.url){
                    child.url=row.url;
                }
                if (node.children){
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    return nodes;
}

//自己拓展 datagrid--只读不可编辑
$.extend($.fn.datagrid.defaults.editors, {
    textReadonly: {
        init: function (container, options) {
            var input = $('<input type="text" readonly="readonly" class="datagrid-editable-input">').appendTo(container);
            return input;
        },
        getValue: function (target) {
            return $(target).val();
        },
        setValue: function (target, value) {
            $(target).val(value);
        },
        resize: function (target, width) {
            var input = $(target);
            if ($.boxModel == true) {
                input.width(width - (input.outerWidth() - input.width()));
            } else {
                input.width(width);
            }
        }
    }
});
//密码验证
$.extend($.fn.validatebox.defaults.rules, {
    /*必须和某个字段相等*/
    equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '字段不匹配' },
    tel : {// 电话号码或手机号码  
        validator : function(value) {  
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value) || /^(13|15|18)\d{9}$/i.test(value);  
        },  
        message : '电话号码或手机号码格式不正确'  
    },
    phone : {// 验证电话号码  
        validator : function(value) {  
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);  
        },  
        message : '格式不正确,请使用下面格式:020-88888888'  
    },  
    mobile : {// 验证手机号码  
        validator : function(value) {  
            return /^(13|15|18)\d{9}$/i.test(value);  
        },  
        message : '手机号码格式不正确'  
    },  
    phoneAndMobile : {// 电话号码或手机号码  
        validator : function(value) {  
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value) || /^(13|15|18)\d{9}$/i.test(value);  
        },  
        message : '电话号码或手机号码格式不正确'  
    }, 
    name : {// 验证姓名，可以是中文或英文  
        validator : function(value) {  
            return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);  
        },  
        message : '请输入姓名'  
    },  
    engOrChinese : {// 可以是中文或英文  
        validator : function(value) {  
            return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);  
        },  
        message : '请输入中文'  
    }
   });
//拓展combogrid，加上了toolbar，解决toolbar：'#id'这种形式只可以绑定一个combogrid，其他行没有。这里给每个combogrid初始化一个toolbar.
$.extend($.fn.datagrid.defaults.editors, {
	myCombogrid: {
		init: function(container, options){
			var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container); 
			input.combogrid(options);
			var div = $('<div></div>');
			var form = $('<form ></form>');
			var table = $('<table></table>');
			var tr = $('<tr></tr>');
			var td1 = $('<td><label>货物名称：</label></td>');
			var td2 = $('<td></td>');
			var td2_1 = $('<input name="searchGoodsName">');
			var td3 = $('<td><label>规格型号：</label></td>');
			var td4 = $('<td></td>');
			var td4_1 = $('<input name="searchGoodsType">');
			var td5 = $('<td><input type="hidden" class="easyui-textbox" name="searchStatus" value="1"></td>');
			var td6 = $('<td><input type="hidden" name="actionUsername" value="${user.username }"></td>');
			var td7 = $('<td></td>');
			var td7_1 = $('<a class="easyui-linkbutton" id="search">搜索</a>');
			
			$(td2).append(td2_1);
			$(tr).append(td1);
			$(tr).append(td2);
			$(td4).append(td4_1);
			$(tr).append(td3);
			$(tr).append(td4);
			$(tr).append(td5);
			$(tr).append(td6);
			$(td7).append(td7_1);
			$(tr).append(td7);
			
			$(table).append(tr);
			$(form).append(table);
			$(div).append(form);
			
			$(td2_1).textbox({
				onChange:function(){send();}
			});
			$(td4_1).textbox({
				onChange:function(){send();}
			});
			$(td7_1).linkbutton();
			$(td7_1).bind('click',function(){send();});
			
			input.combogrid({toolbar:div});
			function send(){
				$.getJSON(
						'goods_search',
						$(form).serialize(),
						function(data){
							if(data){
								$(input).combogrid('grid').datagrid('loadData',data);
							}else{
								$(input).combogrid('grid').datagrid('loadData',{'total':0,'row':[]});
							}
						}
					);
			};
			return input;
		},
		destroy: function(target){
			$(target).combogrid('destroy');
		},
		getValue: function(target){
			return $(target).combogrid('getValue');
		},
		setValue: function(target, value){
			$(target).combogrid('setValue', value);
		},
		resize: function(target, width){
			$(target).combogrid('resize',width);
		}
	},
	
	myCombogrid_supplier: {
		init: function(container, options){
			var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container); 
			input.combogrid(options);
			var div = $('<div></div>');
			var form = $('<form ></form>');
			var table = $('<table></table>');
			var tr = $('<tr></tr>');
			var td1 = $('<td><label>供应商名称：</label></td>');
			var td2 = $('<td></td>');
			var td2_1 = $('<input name="searchSupplierName">');
			var td3 = $('<td><label>纳税识别码：</label></td>');
			var td4 = $('<td></td>');
			var td4_1 = $('<input name="searchSupplierTax">');
			var td5 = $('<td><label>开户银行：</label></td>');
			var td6 = $('<td></td>');
			var td6_1 = $('<input name="searchBankName">');
			var td7 = $('<td><input type="hidden" class="easyui-textbox" name="searchStatus" value="1"></td>');
			var td8 = $('<td><input type="hidden" name="actionUsername" value="${user.username }"></td>');
			var td9 = $('<td></td>');
			var td9_1 = $('<a class="easyui-linkbutton" id="search">搜索</a>');
			
			$(td2).append(td2_1);
			$(tr).append(td1);
			$(tr).append(td2);
			$(td4).append(td4_1);
			$(tr).append(td3);
			$(tr).append(td4);
			$(td6).append(td6_1);
			$(tr).append(td5);
			$(tr).append(td6);
			$(tr).append(td7);
			$(tr).append(td8);
			$(td9).append(td9_1);
			$(tr).append(td9);
			
			$(table).append(tr);
			$(form).append(table);
			$(div).append(form);
			
			$(td2_1).textbox({
				onChange:function(){send();}
			});
			$(td4_1).textbox({
				onChange:function(){send();}
			});
			$(td6_1).textbox({
				onChange:function(){send();}
			});
			$(td9_1).linkbutton();
			$(td9_1).bind('click',function(){send();});
			
			input.combogrid({toolbar:div});
			function send(){
				$.getJSON(
						'supplier_search',
						$(form).serialize(),
						function(data){
							if(data){
								$(input).combogrid('grid').datagrid('loadData',data);
							}else{
								$(input).combogrid('grid').datagrid('loadData',{'total':0,'row':[]});
							}
						}
					);
			};
			return input;
		},
		destroy: function(target){
			$(target).combogrid('destroy');
		},
		getValue: function(target){
			return $(target).combogrid('getValue');
		},
		setValue: function(target, value){
			$(target).combogrid('setValue', value);
		},
		resize: function(target, width){
			$(target).combogrid('resize',width);
		}
	}
	
});
