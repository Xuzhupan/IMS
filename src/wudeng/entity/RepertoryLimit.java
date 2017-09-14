package wudeng.entity;

public class RepertoryLimit {
	private Integer goodsId;
	private Goods goods;
	private Integer maxNumber;
	private Integer minNumber;
	private Integer status;
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}
	public Integer getMinNumber() {
		return minNumber;
	}
	public void setMinNumber(Integer minNumber) {
		this.minNumber = minNumber;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
