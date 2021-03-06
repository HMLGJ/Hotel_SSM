<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>角色管理管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- layui样式 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">
    
    <!-- layui_ext样式 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui_ext/dtree/dtree.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui_ext/dtree/font/dtreefont.css" >
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">角色名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="role_name" autocomplete="off" class="layui-input" >
                            </div>
                        </div>
                        
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn"  lay-submit lay-filter="data-search-btn"><i class="layui-icon layui-icon-search"></i>搜索</button>
                            <button type="reset" class="layui-btn layui-btn-warm"><i class="layui-icon layui-icon-refresh-1"></i>重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="grantMenu">分配菜单</a>
        </script>
        
        <%-- 添加和修改窗口弹出层 --%>
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <div class="layui-form-item">
                    <label class="layui-form-label">角色名称</label>
                    <div class="layui-input-block">
                        <%-- 隐藏域type=hidden，而type="text"是可见 --%>
                        <input type="hidden" name="role_id">
                        <input type="text" name="role_name" lay-verify="required" autocomplete="off"
                               placeholder="请输入角色名称" class="layui-input">
                    </div>
                </div>
                <!-- <div class="layui-form-item">
                    <label class="layui-form-label">角色描述</label>
                    <div class="layui-input-block">
                        <input type="text" name="role_desc" lay-verify="required" autocomplete="off" placeholder="请输入角色描述"
                               class="layui-input">
                    </div>
                </div> -->
                <div class="layui-form-item">
                    <label class="layui-form-label">角色描述</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" name="role_desc" id="content"></textarea>
                    </div>
                </div>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block" style="text-align: center;">
                        <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span
                                class="layui-icon layui-icon-add-1"></span>提交
                        </button>
                        <button type="reset" class="layui-btn layui-btn-warm"><span
                                class="layui-icon layui-icon-refresh-1"></span>重置
                        </button>
                    </div>
                </div>
            </form>
        </div>
        
        <!-- 分配菜单弹出层 -->
        <!-- style="display: none;"如果不写这个电话，关闭窗口会在下面显示 -->
    <div style="display: none;" id="selectRoleMenuDiv">
    	<ul id="roleTree" class="dtree" data-id="0"></ul>
    </div>

    </div>
    
    
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.extend({
    	dtree:"${pageContext.request.contextPath}/statics/layui_ext/dtree/dtree",
    }).use(['form', 'table','layer','dtree'], function () {
        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer,
            table = layui.table,
        	dtree = layui.dtree;

        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/role/list',
            toolbar: '#toolbarDemo',
            
            cols: [[
                {field: 'role_id', width: 120, title: '角色编号', align: "center"},
                {field: 'role_name', minWidth: 120, title: '角色名称', align: "center"},
                {field: 'role_desc', minWidth: 100, title: '角色描述', align: "center"},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true,
            done: function (res, curr, count) {
                //判断当前页码是否是大于1并且当前页的数据量为0
                if (curr > 1 && res.data.length == 0) {
                    var pageValue = curr - 1;
                    //刷新数据表格的数据
                    tableIns.reload({
                        page: {curr: pageValue}
                    });
                }
            }
        });        
        

         // 监听搜索操作
         form.on('submit(data-search-btn)', function (data) {
             tableIns.reload({
                 where: data.field,
                 page: {
                     curr: 1
                 }
             });
             return false;
         });

         //toolbar头部监听事件
         //currentTableFilter是表格lay-filter过滤器的值
         table.on("toolbar(currentTableFilter)",function (obj) {
             switch (obj.event) {
                 case "add"://添加按钮
                     openAddWindow();//打开添加窗口
                     break;
             }
         });
        
        //监听行工具栏事件
         table.on("tool(currentTableFilter)",function (obj) {
             /* console.log(obj); */
             switch (obj.event) {
                 case "edit"://编辑按钮
                     openUpdateWindow(obj.data);//打开修改窗口
                     break;
                 case "delete"://删除按钮
                     deleteById(obj.data);
                     break;
                 case "grantMenu"://分配菜单按钮
                	 openGrantMenuWindow(obj.data);
                     break;
             }
         });
       
         var url;//提交地址
         var mainIndex;//打开窗口的索引
         
         //打开添加窗口方法
         function openAddWindow() {
             mainIndex = layer.open({
                 type: 1,//打开类型
                 title: "添加角色",//窗口标题
                 area: ["800px", "400px"],//窗口宽高
                 content: $("#addOrUpdateWindow"),//引用的内容窗口
                 success: function () {
                     //清空表单数据
                     $("#dataFrm")[0].reset();
                     //添加的提交请求
                     url = "/admin/role/addRole";
                 }
             });
         }
         //打开修改窗口方法
         function openUpdateWindow(data) {
             mainIndex = layer.open({
                 type: 1,//打开类型
                 title: "修改角色",//窗口标题
                 area: ["800px", "400px"],//窗口宽高
                 content: $("#addOrUpdateWindow"),//引用的内容窗口
                 success: function () {
                     //表单数据回显
                     form.val("dataFrm",data);//参数1：lay-filter值  参数2：回显的数据
                     //修改的提交请求
                     url = "/admin/role/updateRole";
                 }
             });
         }
        
       //监听表单提交事件
         form.on("submit(doSubmit)",function (data) {
             //通过Ajax请求提交
             $.post(url,data.field,function(result){
                 if(result.success){
                     //刷新数据表格
                     tableIns.reload();
                     //关闭窗口
                     layer.close(mainIndex);
                 }
                 //提示信息
                 layer.msg(result.message);
             },"json");
             //禁止页面刷新
             return false;
         });
        
       
         //删除角色方法
         function deleteById(data) {
             //判断当前角色下是否存在员工
             $.get("/admin/role/checkRoleHasEmployee",{"role_id":data.role_id},function(result){
            	 
                 if(result.exist){
                     //提示用户无法删除
                     layer.msg(result.message);
                 }else{
                     //提示用户是否删除该角色
                     layer.confirm("确定要删除[<font color='red'>"+data.role_name+"</font>]吗", {icon: 3, title:'提示'}, function(index){
                         //发送ajax请求进行删除
                         $.post("/admin/role/deleteById",{"role_id":data.role_id},function(result){
                             if(result.success){
                                 //刷新数据表格
                                 tableIns.reload();
                             }
                             //提示
                             layer.msg(result.message);
                         },"json");

                         layer.close(index);
                     });
                 }
             },"json");
         }
		
         
         /**
          * 打开分配菜单窗口
          * @param data  当前行的数据
          * 参考文档：https://www.wisdomelon.com/DTreeHelper/
          */
         function openGrantMenuWindow(data) {
             mainIndex = layer.open({
                 type: 1,//打开类型
                 area:["400px","550px"],
                 title:"分配[<font color='red'>"+data.role_name+"</font>]的菜单",
                 content: $("#selectRoleMenuDiv"),//引用的内容窗口
                 btn: ['确定', '取消']
                 ,yes: function(index, layero){
                     //获取复选框选中的值
                     var params = dtree.getCheckbarNodesParam("roleTree");
                     //判断是否有选中复选框
                     if(params.length>0){
                         //定义数组，保存选中的值
                         var idArr = [];
                         //循环获取选中的值
                         for (var i = 0; i < params.length; i++) {
                             idArr.push(params[i].nodeId);//nodeId是选中的树节点的值，固定不能修改
                         }
                         //将数组转换成字符串
                         var ids = idArr.join(",");//选中的菜单ID值
                         //发送ajax请求，保存选中的菜单ID
                         $.post("/admin/role/saveRoleMenu",{"ids":ids,"role_id":data.role_id},function(result){
                             layer.msg(result.message);
                         },"json");
                     }else{
                         layer.msg("请选择要分配的菜单");
                     }

                 }
                 ,btn2: function(index, layero){
                	//刷新数据表格
                     tableIns.reload();
                     //关闭窗口
                     layer.close(mainIndex);
                 },
                 success: function () {
                     //渲染dtree组件
                     dtree.render({
                         elem: "#roleTree",//绑定ul标签的ID属性值
                         //role_id="+data.role_id(第一个role_id为Controlle的id,第二个id为当前表单的id名)
                         url: "/admin/role/initMenuTree?role_id="+data.role_id, //请求地址
                         dataStyle: "layuiStyle",  //使用layui风格的数据格式
                         dataFormat: "list",  //配置data的风格为list
                         response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
                         checkbar: true,
                         checkbarType: "all" // 默认就是all，其他的值为： no-all  p-casc   self  only
                     });
                 }
             });
         }
        

    });
</script>

</body>
</html>