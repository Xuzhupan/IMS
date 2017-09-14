package wudeng.entity;

import java.util.Date;

public class Supplier {
	private Integer id;
	private String name;
	private String messenger;
	private String tel;
	private String taxpayerIdentity;
	private String bankName;
	private String bankAccount;
	private String registerTel;
	private String registerAddress;
	private Date createDate;
	private String information;
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessenger() {
		return messenger;
	}
	public void setMessenger(String messenger) {
		this.messenger = messenger;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTaxpayerIdentity() {
		return taxpayerIdentity;
	}
	public void setTaxpayerIdentity(String taxpayerIdentity) {
		this.taxpayerIdentity = taxpayerIdentity;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getRegisterTel() {
		return registerTel;
	}
	public void setRegisterTel(String registerTel) {
		this.registerTel = registerTel;
	}
	public String getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
