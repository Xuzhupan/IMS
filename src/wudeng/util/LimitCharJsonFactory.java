package wudeng.util;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import wudeng.entity.RepertoryLimit;

public class LimitCharJsonFactory {
	public static String toCharJson(List<RepertoryLimit> list){
		StringBuffer s = new StringBuffer();
		s.append("{");
		//char type
		s.append("\"char\":").append("{")
			.append("\"type\":").append("\"bar\"")
		.append("}").append(",");
		//标题
		s.append("\"title\":").append("{")
			.append("\"text\":").append("\"仓库物品库存容量图\"").append(",")
			.append("\"style\":").append("{").append("\"fontWeight\":").append("\"bold\"").append("}")
		.append("}").append(",");
		//副标题
		s.append("\"subtitle\":").append("{")
			.append("\"text\":").append("\""+new Date().toLocaleString()+"\"")
		.append("}").append(",");
		//x轴提示
		s.append("\"xAxis\":").append("{")
			.append("\"categories\":").append(getXAxisJson(list)).append(",")
			.append("\"title\":").append("{")
				.append("\"text\":").append("\"物品名称\"")
			.append("}")
		.append("}").append(",");
		//y轴提示
		s.append("\"yAxis\":").append("{")
			.append("\"min\":").append(0) .append(",")
			.append("\"title\":").append("{")
				.append("\"text\":").append("\"数量\"")  .append(",")
				.append("\"align\":").append("\"high\"")
			.append("}").append(",")
			.append("\"labels\":").append("{")
				.append("\"overflow\":").append("\"justify\"")
			.append("}")
		.append("}").append(",");
		//tooltip 提示标签
		s.append("\"tooltip\":").append("{")
			.append("\"valueSuffix\":").append("\"库存\"")//提示后跟字符
		.append("}").append(",");
		//plotOptions
		s.append("\"plotOptions\":").append("{")
			.append("\"bar\":").append("{")
				.append("\"dataLabels\":").append("{")
					.append("\"enabled\":").append(true)
				.append("}")
			.append("}")
		.append("}").append(",");
		//legend
		s.append("\"legend\":").append("{")
			.append("\"layout\":").append("\"vertical\"").append(",")
			.append("\"align\":").append("\"right\"").append(",")
			.append("\"verticalAlign\":").append("\"top\"").append(",")
			.append("\"x\":").append(-40).append(",")
			.append("\"y\":").append(100).append(",")
			.append("\"floating\":").append(true).append(",")
			.append("\"borderWidth\":").append(1).append(",")
			.append("\"backgroudColor\":").append("\"((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || #FFFFFF\"").append(",")
			.append("\"shadow\":").append(true)
		.append("}").append(",");
		//credits
		s.append("\"credits\":").append("{")
			.append("\"enabled\":").append(false)
		.append("}").append(",");
		//series
		s.append("\"series\":").append(getSeries(list));
		
		s.append("}");
		return s.toString();
	}
	
	private static String getXAxisJson(List<RepertoryLimit> list){
		JSONArray j = new JSONArray();
		for(RepertoryLimit r : list){
			j.add(r.getGoods().getId()+"#"+r.getGoods().getName());
		}
		return j.toJSONString();
	}
	private static String getSeries(List<RepertoryLimit> list){
		JSONObject jMax = new JSONObject();
		JSONObject jNow = new JSONObject();
		JSONObject jMin = new JSONObject();
		JSONArray jsonArrayMax = new JSONArray();
		JSONArray jsonArrayNow = new JSONArray();
		JSONArray jsonArrayMin = new JSONArray();
		JSONArray array = new JSONArray();
		for(RepertoryLimit r : list){
			jsonArrayMax.add(r.getMaxNumber());
			jsonArrayNow.add(r.getGoods().getRepertory().getNumber());
			jsonArrayMin.add(r.getMinNumber());
		}
		jMax.put("name", "库存容量上限");
		jMax.put("data", jsonArrayMax);
		jNow.put("name", "当前库存容量");
		jNow.put("data", jsonArrayNow);
		jMin.put("name", "库存容量下限");
		jMin.put("data", jsonArrayMin);
		array.add(jMax);
		array.add(jNow);
		array.add(jMin);
		return array.toJSONString();
	}
}
