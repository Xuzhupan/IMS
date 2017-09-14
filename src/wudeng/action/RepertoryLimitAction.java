package wudeng.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

import myJson.RepertoryLimitJson;
import wudeng.entity.Goods;
import wudeng.entity.Repertory;
import wudeng.entity.RepertoryLimit;
import wudeng.service.RepertoryLimitService;
import wudeng.util.LimitCharJsonFactory;
import wudeng.service.ObjectService;

@Controller
public class RepertoryLimitAction extends BaseAction implements ModelDriven<RepertoryLimit>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rows;
	private int page;
	private String actionUsername;
	private String searchGoodsName;
	private String searchGoodsType;
	
	private RepertoryLimit repertoryLimit = new RepertoryLimit();
	@Autowired
	private RepertoryLimitService service;
	@Autowired
	private ObjectService ObService;
	
	public void findAll(){ 
		JSONObject j = new JSONObject();
		List<RepertoryLimit> list = service.findAll(repertoryLimit);
		List<RepertoryLimitJson> jsons = new ArrayList<RepertoryLimitJson>();
		RepertoryLimitJson json;
		if(list!=null && list.size()>0){
			for(RepertoryLimit r : list){
				if(r.getGoods().getStatus()==1){ //如果商品状态为可用，则返回商品库存容量
					json = new RepertoryLimitJson();
					json.setGoodsId(r.getGoodsId());
					json.setGoodsName(r.getGoods().getName());
					json.setGoodsType(r.getGoods().getType());
					json.setGoodsUnit(r.getGoods().getUnit());
					json.setGoodsInformation(r.getGoods().getInformation());
					json.setMaxNumber(r.getMaxNumber());
					json.setMinNumber(r.getMinNumber());
					json.setStatus(r.getStatus());
					jsons.add(json);
				}
			}
			j.put("total", jsons.size());
			j.put("rows", jsons);
			this.write(j.toJSONString());
		}
	}
	public void saveOrUpdate(){
		Goods goods = ObService.findGoodstById(repertoryLimit.getGoodsId());
		repertoryLimit.setGoods(goods);
		repertoryLimit.setStatus(goods.getRepertoryLimit().getStatus());
		service.saveOrUpdate(this.repertoryLimit);
		JSONObject j = new JSONObject();
		if(repertoryLimit!=null&&!repertoryLimit.getGoodsId().equals("")){
			j.put("message", "保存成功");
			j.put("success", true);
		}else{
			j.put("message", "保存失败");
			j.put("success", false);
		}
		this.write(JSON.toJSONString(j));
	}
	public void changeStatus(){ //
		repertoryLimit = service.get(RepertoryLimit.class, repertoryLimit.getGoodsId());
		repertoryLimit.setStatus(repertoryLimit.getStatus()==0?1:0);
		service.saveOrUpdate(this.repertoryLimit);
		JSONObject j = new JSONObject();
		j.put("message", "成功");
		j.put("success", true);
		this.write(JSON.toJSONString(j));
	}
	public void getChar(){
		List<RepertoryLimit> limits = service.findStatusOf(repertoryLimit, 1);
		this.write(LimitCharJsonFactory.toCharJson(limits));
	
	}
	
	public void search(){
		if(actionUsername!=null && !actionUsername.equals("")){
			Map<String,Object> m = new HashMap<String, Object>();
			if(searchGoodsName!=null && !searchGoodsName.equals("") ){
				m.put("goods.name like ", "\'%"+searchGoodsName+"%\'");
			}
			if(searchGoodsType!=null && !searchGoodsType.equals("") ){
				m.put("goods.type like ", "\'%"+searchGoodsType+"%\'");
			}
			List<RepertoryLimit> list = service.search(repertoryLimit, m);
			JSONObject j = new JSONObject();
			List<RepertoryLimitJson> jsons = new ArrayList<RepertoryLimitJson>();
			RepertoryLimitJson json;
			if(list!=null && list.size()>0){
				for(RepertoryLimit r : list){
					if(r.getGoods().getStatus()==1){ //如果商品状态为可用，则返回商品库存容量
						json = new RepertoryLimitJson();
						json.setGoodsId(r.getGoodsId());
						json.setGoodsName(r.getGoods().getName());
						json.setGoodsType(r.getGoods().getType());
						json.setGoodsUnit(r.getGoods().getUnit());
						json.setGoodsInformation(r.getGoods().getInformation());
						json.setMaxNumber(r.getMaxNumber());
						json.setMinNumber(r.getMinNumber());
						json.setStatus(r.getStatus());
						jsons.add(json);
					}
				}
				j.put("total", jsons.size());
				j.put("rows", jsons);
				this.write(j.toJSONString());
			}else{
				j.put("total", 0);
				j.put("rows", "");
				this.write(j.toJSONString());
			}
		}else{
			this.write("");
		}
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public RepertoryLimit getModel() {
		return this.repertoryLimit;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getActionUsername() {
		return actionUsername;
	}
	public void setActionUsername(String actionUsername) {
		this.actionUsername = actionUsername;
	}
	public String getSearchGoodsName() {
		return searchGoodsName;
	}
	public void setSearchGoodsName(String searchGoodsName) {
		this.searchGoodsName = searchGoodsName;
	}
	public String getSearchGoodsType() {
		return searchGoodsType;
	}
	public void setSearchGoodsType(String searchGoodsType) {
		this.searchGoodsType = searchGoodsType;
	}
	public RepertoryLimit getRepertoryLimit() {
		return repertoryLimit;
	}
	public void setRepertoryLimit(RepertoryLimit repertoryLimit) {
		this.repertoryLimit = repertoryLimit;
	}

}
