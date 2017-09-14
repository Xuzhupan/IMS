package wudeng.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import wudeng.entity.Menu;

public class CreateJsonTreeByList {

	public static String createTreeJson(List<Menu> list){
		//声明一个json数组
		JSONArray rootArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			Menu menu = list.get(i);
			//没有父id就是最顶端的一层
			if(menu.getParentId()==null){
				//查询该父类是否还有子类（分支）
				JSONObject rootObj = createBranch(list,menu);
				rootArray.add(rootObj);
			}
		}
		return rootArray.toString();
		
	}
	
	public static JSONObject createBranch(List<Menu> list,Menu menu){
		
		//把类转换成json字符串对象
		String jsonString = JSON.toJSONString(menu);
		//把字符串转换成json对象
		JSONObject currentObj = JSON.parseObject(jsonString);
		//声明一个json数组
		JSONArray childArray = new JSONArray();
		
		for(int i=0;i<list.size();i++){
			Menu newNode = list.get(i);
			//判断是否还有分支
			if(newNode.getParentId()!=null 
					&& newNode.getParentId().compareTo(menu.getId())==0){
				//递归查询是否还有下一个
				JSONObject childObj = createBranch(list,newNode);
				childArray.add(childObj);
			}
		}
		
		//判断当前子节点是否为空，不为空将子节点加入children字段
		if(!childArray.isEmpty()){
			currentObj.put("children", childArray);
		}
		
		return currentObj;
		
	}

}
