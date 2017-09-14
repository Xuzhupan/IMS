package wudeng.entity;

public class SellInfo {
	private Integer id;
	private SellPlan sellPlan;
	private Goods goods;
	private Double unitPrice;
	private Integer number;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SellPlan getSellPlan() {
		return sellPlan;
	}
	public void setSellPlan(SellPlan sellPlan) {
		this.sellPlan = sellPlan;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
