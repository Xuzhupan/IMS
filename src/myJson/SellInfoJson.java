package myJson;

public class SellInfoJson {
	private Integer sellInfoId;
	private Double sellInfoUnitPrice;
	private Integer sellInfoNumber;
	private Integer goodsId;
	private String goodsName;
	private String goodsType;
	private String goodsUnit;
	private String goodsInformation;
	private Integer goodsStatus;
	public Integer getSellInfoId() {
		return sellInfoId;
	}
	public void setSellInfoId(Integer sellInfoId) {
		this.sellInfoId = sellInfoId;
	}
	public Integer getSellInfoNumber() {
		return sellInfoNumber;
	}
	public void setSellInfoNumber(Integer sellInfoNumber) {
		this.sellInfoNumber = sellInfoNumber;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public String getGoodsInformation() {
		return goodsInformation;
	}
	public void setGoodsInformation(String goodsInformation) {
		this.goodsInformation = goodsInformation;
	}
	public Integer getGoodsStatus() {
		return goodsStatus;
	}
	public void setGoodsStatus(Integer goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
	public Double getSellInfoUnitPrice() {
		return sellInfoUnitPrice;
	}
	public void setSellInfoUnitPrice(Double sellInfoUnitPrice) {
		this.sellInfoUnitPrice = sellInfoUnitPrice;
	}
}
