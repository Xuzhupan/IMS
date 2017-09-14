package wudeng.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

import myJson.EmployeeJson;
import myJson.GoodsJson;
import wudeng.entity.Employee;
import wudeng.entity.Goods;
import wudeng.entity.Repertory;
import wudeng.entity.RepertoryLimit;
import wudeng.service.GoodsService;

@Controller
public class GoodsAction extends BaseAction implements ModelDriven<Goods>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rows;
	private int page;
	private String actionUsername;
	private String searchGoodsName;
	private String searchGoodsType;
	private String searchStatus;
	private Integer goodsId;
	private Integer goodsNum;
	
	private Goods goods = new Goods();
	@Autowired
	private GoodsService service;
	public void findAll(){
		List<Goods> list = service.findAll(goods);
		JSONObject j = new JSONObject();
		if(list.size()>0){
			j.put("total", list.size());
			j.put("rows", list);
			this.write(j.toJSONString());
		}
	}
	public void findById(){
		goods = service.findById(goods);
		if(goods!=null){
			this.write(JSON.toJSONString(goods));
		}
	}
	public void paginationFindAll(){
		List<Goods> list = service.findAll(goods);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			List<Goods> li = service.paginationFind(goods, page, rows);
			j.put("total", list.size());
			j.put("rows", li);
			this.write(j.toJSONString());
		}
	}
	public void findNotSetLimitGoods(){
		List<Goods> list = service.findAll(goods);
		List<Goods> li = new ArrayList<Goods>();
		for(Goods g : list){
			if(g.getRepertoryLimit().getStatus()==0){
				li.add(g);
			}
		}
		JSONObject j = new JSONObject();
		j.put("total", li.size());
		j.put("rows", li);
		this.write(j.toJSONString());
		
	}
	public String addAndEidt(){
		//判断是编辑还是新增
		if(goods.getId()!=null&&!"".equals(goods.getId())){
			Goods g = service.findById(goods);
			this.getRequest().setAttribute("goods", g);
		}
		return SUCCESS;
	}
	public void saveOrUpdate(){
		JSONObject j = new JSONObject();
		if(service.exist(goods)){
			j.put("success", false);
			j.put("message", "已存在,不能重复添加");
			this.write(j.toJSONString());
			return;
		}
		if(service.findById(goods)==null){
			RepertoryLimit repertoryLimit = new RepertoryLimit();
			repertoryLimit.setGoodsId(goods.getId());
			repertoryLimit.setGoods(goods);
			repertoryLimit.setMaxNumber(0);
			repertoryLimit.setMinNumber(0);
			repertoryLimit.setStatus(0);
			Repertory repertory = new Repertory();
			repertory.setGoodsId(goods.getId());
			repertory.setGoods(goods);
			repertory.setNumber(0);
			goods.setRepertory(repertory);
			goods.setRepertoryLimit(repertoryLimit);
		}
		Goods goods = service.saveOrUpdate(this.goods);
		if(goods!=null&&!goods.getId().equals("")){
			j.put("message", "保存成功");
			j.put("success", true);
		}else{
			j.put("message", "保存失败");
			j.put("success", false);
		}
		this.write(JSON.toJSONString(j));
	}
	public void alterStatus(){
		service.saveOrUpdate(goods);
		JSONObject j = new JSONObject();
		if(goods!=null&&!goods.getId().equals("")){
			j.put("success", true);
		}else{
			j.put("success", false);
		}
		this.write(JSON.toJSONString(j));
	}
	public void delete(){
		goods = service.findById(goods);
		JSONObject j = new JSONObject();
		try {
			service.delete(goods);
			j.put("message", "删除成功");
			j.put("success", true);
		} catch (Exception e) {
			j.put("message", "删除失败");
			j.put("success", false);
			e.printStackTrace();
		}finally{
			this.write(JSON.toJSONString(j));
		}
	}
	public void search(){
		if(actionUsername!=null && !actionUsername.equals("")){
			Map<String,Object> m = new HashMap<String, Object>();
			if(searchGoodsName!=null && !searchGoodsName.equals("") ){
				m.put("name like ", "\'%"+searchGoodsName+"%\'");
			}
			if(searchGoodsType!=null && !searchGoodsType.equals("") ){
				m.put("type like ", "\'%"+searchGoodsType+"%\'");
			}
			if(searchStatus!=null && !searchStatus.equals("") ){
				m.put("status = ", searchStatus);
			}
			List<Goods> list = service.search(goods, m);
			List<GoodsJson> jsons = new ArrayList<GoodsJson>();
			GoodsJson json;
			for(Goods g:list){
				json = new GoodsJson();
				BeanUtils.copyProperties(g, json);
				json.setRepertoryNumber(g.getRepertory().getNumber());
				jsons.add(json);
			}
			JSONObject j = new JSONObject();
			if(list!=null && list.size()>0){
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
	public void remoteNumber(){
		if(goodsId!=null){
			goods = service.get(Goods.class, goodsId);
			if(goodsNum <= goods.getRepertory().getNumber()){
				this.write("true");
			}else{
				this.write("false");
			}
		}else{
		}
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public Goods getModel() {
		return this.goods;
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
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	
}
