# 企业库存管理系统操作手册

标签（空格分隔）： IMS Java

---

本系统为B/S架构，使用java语言开发，运用SSH三大框架搭建后台，前端是Easyui界面。系统有用户管理、员工管理、部门管理、菜单管理、角色管理、货物管理、客户管理、供应商管理、库存管理、库存容量管理，采购计划管理、销售订单管理、出库、入库14个功能模块，拥有细粒度的权限划分，管理员可根据需要自由分配权限。

#### 运行环境

> 操作系统：Windows 7
数据库：MySql 
Web服务器：Tomcat9.0
开发语言：Java、Html、Css、Jquery、Easyui、SSH框架
开发工具：Eclipse
浏览器：Chrome   

#### 运行步骤
> 目录结构
+--IMS.rar
+--IMS.sql

 1. 将IMS.rar解压缩，打开Eclipse,依次点击File-->improt-->General-->Existing Projects into Workspace 选中刚解压缩的IMS文件夹，点击确定。
 2. 在MySql中新建utf-8数据表IMS,导入ims.sql。
 3. 根据MySql的用户名密码修改IMS/resource/db.properties 配置文件。
 4. 根据tomcat配置在浏览器中输入URL访问，例:http://localhost:8080/IMS
  
**提示：**  
    *非系统维护人员请不要操作数据库！！！系统提供一个超级管理员账号，拥有全部权限，超级管理员角色和系统管理员角色拥有操作角色及系统用户的权限，在使用时应严格遵守规定，根据最小原则，为用户赋予刚好满足其需要的权限，不要为公司业务角色赋予用户权限、菜单权限及角色权限，它们属于系统维护人员。角色表记录1.超级管理员、2.系统管理员在程序中已经固定，请不要修改其名称或有其它角色与其同名。完*

---

## 登陆
目前系统用户账号：[角色名] [账号] [密码]   
 1. 超级管理员 cjgly cjgly
 2. 系统管理员 xtgly xtgly
 3. 人事 rlzybjl rlzybjl
 4. 采购 cgbjl cgbjl
 5. 销售 xsbjl xsbjl
 6. 仓管 ckgly ckgly    
输入用户名、密码进入系统。   

## 主界面
![系统主界面][1]
登陆后进入系统主界面，左边是菜单栏，右边是信息及操作界面，展示图用户拥有查看库存图的相应权限，右边界面展示了当前库存情况。

## Tab右键菜单
![Tab右键菜单][2]
每个tab标签都拥有右键菜单，右击可打开。有刷新、关闭全部标签、关闭其他标签、关闭在右侧标签、关闭左侧标签功能。

## 界面说明
![界面说明][3]

 - 菜单栏：点击进入各功能菜单。 
 - 标签栏：点击菜单选项后弹出标签。
 - 搜索栏：输入搜索条件或选择搜索条件搜索。
 - 功能栏：各功能按钮。
 - 分布栏：分页功能。
 - 数据展示界面：展示数据。

## 员工管理
 管理员工信息。
 
## 用户管理
管理用户信息。
 
![新增用户][4]
新增用户,录入用户名、密码、员工及角色。

![角色授权][5]
为用户更换角色。

## 部门管理
管理部门信息。

## 菜单管理
管理菜单信息。
![菜单管理][6]
点击停用，停用菜单。点击启用，启用菜单。

## 角色管理
![角色界面][7]
默认处于新增角色界面，输入角色名，选择角色权限保存即可。
![查看角色界面][8]
点击左侧角色名在右侧显示角色权限，勾选权限点击保存即修改角色权限，只可授予当前登陆用户拥有的权限。

## 货物管理
管理商品信息。
![角色界面][9]

## 客户管理
略。

## 供应商管理
略。

## 库存管理
略。

## 库存容量管理
![库存容量设置界面][10]
点击编辑，编辑库存容量上下限。点击停用，停用当前设置。点击启用，启用库存容量设置。

## 采购计划管理
![采购计划界面][11]
双击记录查看详细内容。删除记录会同时删除入库记录。

![新增采购计划界面][12]
点击添加采购计划打开此标签页，点击添加新增一行，点击商品名称下拉框弹出下拉界面，可以进行搜索，选中一行并自动补全商品信息。

## 销售订单管理
同采购计划管理。
![销售订单界面][13]
![新增销售订单界面][14]
![商品搜索界面][15]

## 入库管理
![入库管理界面][16]
左侧显示可入库采购记录，选中并点击整单入库即完成入库操作。右侧记录操作历史，选中可撤销记录点击撤销撤销入库操作。双击记录查看详情。

## 出库管理
![出库管理界面][17]
同入库管理。

  [1]: http://wudeng.oschina.io/imspng/main.png
  [2]: http://wudeng.oschina.io/imspng/menuTab.png
  [3]: http://wudeng.oschina.io/imspng/view.png
  [4]: http://wudeng.oschina.io/imspng/addUser.png
  [5]: http://wudeng.oschina.io/imspng/authorize.png
  [6]: http://wudeng.oschina.io/imspng/menuManager.png
  [7]: http://wudeng.oschina.io/imspng/role.png
  [8]: http://wudeng.oschina.io/imspng/viewRole.png 
  [9]: http://wudeng.oschina.io/imspng/goods.png
  [10]: http://wudeng.oschina.io/imspng/limit.png
  [11]: http://wudeng.oschina.io/imspng/purchasePlan.png
  [12]: http://wudeng.oschina.io/imspng/purchaseInfo.png
  [13]: http://wudeng.oschina.io/imspng/sellPlan.png
  [14]: http://wudeng.oschina.io/imspng/sellInfo1.png
  [15]: http://wudeng.oschina.io/imspng/goodsSearch.png
  [16]: http://wudeng.oschina.io/imspng/in.png
  [17]: http://wudeng.oschina.io/imspng/outViewSellInfo.png