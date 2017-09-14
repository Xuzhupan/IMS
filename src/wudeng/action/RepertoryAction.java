package wudeng.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;

import wudeng.entity.Goods;
import wudeng.entity.Repertory;
import wudeng.service.RepertoryService;

@Controller
public class RepertoryAction extends BaseAction implements ModelDriven<Repertory>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Repertory repertory = new Repertory();
	private int rows;
	private int page;
	private String actionUsername;
	private String searchGoodsName;
	private String searchGoodsType;
	
	@Autowired
	private RepertoryService service;
	public void findAll(){
		List<Repertory> list = service.findAll(repertory);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			j.put("total", list.size());
			j.put("rows", list);
			this.write(j.toJSONString());
		}
	}
	public void paginationFindAll(){
		List<Repertory> list = service.findAll(repertory);
		JSONObject j = new JSONObject();
		if(list!=null && list.size()>0){
			List<Repertory> li = service.paginationFind(repertory, page, rows);
			j.put("total", list.size());
			j.put("rows", li);
			this.write(j.toJSONString());
		}
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
			List<Repertory> list = service.search(repertory, m);
			JSONObject j = new JSONObject();
			if(list!=null && list.size()>0){
				j.put("total", list.size());
				j.put("rows", list);
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
	public Repertory getModel() {
		return this.repertory;
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

}
