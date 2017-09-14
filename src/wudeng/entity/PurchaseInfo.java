package wudeng.entity;

public class PurchaseInfo {
	private Integer id;
	private PurchasePlan purchasePlan;
	private Goods goods;
	private Supplier supplier;
	private Double unitPrice;
	private Integer number;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}
	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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
