package Gradle_Payroll.data;

import java.math.BigDecimal;

public class Check {
	
	//NOTICE: ALL tax values are handled separately in the Tax type in order to facilitate a dynamic amount of taxes for each individual person.
	
	private int ID = 0;
	private BigDecimal checkNum = new BigDecimal(0);
	private BigDecimal grossAmmnt = new BigDecimal(0);
	private BigDecimal fedGrossAmmnt = new BigDecimal(0);
	private BigDecimal stateGrossAmmnt = new BigDecimal(0);
	private BigDecimal state2GrossAmmnt = new BigDecimal(0);
	private BigDecimal sscGrossAmmnt = new BigDecimal(0);
	private BigDecimal medicareGrossAmmnt = new BigDecimal(0);
	private BigDecimal localGrossAmmnt = new BigDecimal(0);
	private BigDecimal netAmmnt = new BigDecimal(0);
	private BigDecimal regHours = new BigDecimal(0);
	private BigDecimal regRate = new BigDecimal(0);
	private BigDecimal regAmmnt = new BigDecimal(0);
	private BigDecimal ptoHours = new BigDecimal(0);
	private BigDecimal ptoRate = new BigDecimal(0);
	private BigDecimal ptoAmmnt = new BigDecimal(0);
	private BigDecimal otHours = new BigDecimal(0);
	private BigDecimal otRate = new BigDecimal(0);
	private BigDecimal otAmmnt = new BigDecimal(0);
	private BigDecimal salAmmnt =new BigDecimal(0);
	private BigDecimal advAmmnt =new BigDecimal(0);
	private BigDecimal royaltyAmmnt = new BigDecimal(0);
	private BigDecimal AddStateTax = new BigDecimal(0);
	private BigDecimal AddFedTax = new BigDecimal(0);
	private String startDate ="";
	private String endDate = "";
	private String date = " ";
	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	private Name name;
	private Address address;
	private YTD ytd = new YTD();
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public BigDecimal getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(BigDecimal checkNum) {
		this.checkNum = checkNum;
	}
	public BigDecimal getGrossAmmnt() {
		return grossAmmnt;
	}
	public void setGrossAmmnt(BigDecimal grossAmmnt) {
		this.grossAmmnt = grossAmmnt;
	}
	public BigDecimal getNetAmmnt() {
		return netAmmnt;
	}
	public void setNetAmmnt(BigDecimal netAmmnt) {
		this.netAmmnt = netAmmnt;
	}
	public BigDecimal getRegHours() {
		return regHours;
	}
	public void setRegHours(BigDecimal regHours) {
		this.regHours = regHours;
	}
	public BigDecimal getRegRate() {
		return regRate;
	}
	public void setRegRate(BigDecimal regRate) {
		this.regRate = regRate;
	}
	public BigDecimal getPtoHours() {
		return ptoHours;
	}
	public void setPtoHours(BigDecimal ptoHours) {
		this.ptoHours = ptoHours;
	}
	public BigDecimal getPtoRate() {
		return ptoRate;
	}
	public void setPtoRate(BigDecimal ptoRate) {
		this.ptoRate = ptoRate;
	}
	public BigDecimal getOtHours() {
		return otHours;
	}
	public void setOtHours(BigDecimal otHours) {
		this.otHours = otHours;
	}
	public BigDecimal getOtRate() {
		return otRate;
	}
	public void setOtRate(BigDecimal otRate) {
		this.otRate = otRate;
	}
	
	public BigDecimal getRoyaltyRate() {
		return royaltyAmmnt;
	}
	public void setRoyaltyRate(BigDecimal royaltyRate) {
		this.royaltyAmmnt = royaltyRate;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public YTD getYtd() {
		return ytd;
	}
	public void setYtd(YTD ytd) {
		this.ytd = ytd;
	}
	public BigDecimal getAddStateTax() {
		return AddStateTax;
	}
	public void setAddStateTax(BigDecimal addStateTax) {
		AddStateTax = addStateTax;
	}
	public BigDecimal getAddFedTax() {
		return AddFedTax;
	}
	public void setAddFedTax(BigDecimal addFedTax) {
		AddFedTax = addFedTax;
	}
	public BigDecimal getFedGrossAmmnt() {
		return fedGrossAmmnt;
	}
	public void setFedGrossAmmnt(BigDecimal fedGrossAmmnt) {
		this.fedGrossAmmnt = fedGrossAmmnt;
	}
	public BigDecimal getStateGrossAmmnt() {
		return stateGrossAmmnt;
	}
	public void setStateGrossAmmnt(BigDecimal stateGrossAmmnt) {
		this.stateGrossAmmnt = stateGrossAmmnt;
	}
	public BigDecimal getState2GrossAmmnt() {
		return state2GrossAmmnt;
	}
	public void setState2GrossAmmnt(BigDecimal state2GrossAmmnt) {
		this.state2GrossAmmnt = state2GrossAmmnt;
	}
	public BigDecimal getSscGrossAmmnt() {
		return sscGrossAmmnt;
	}
	public void setSscGrossAmmnt(BigDecimal sscGrossAmmnt) {
		this.sscGrossAmmnt = sscGrossAmmnt;
	}
	public BigDecimal getMedicareGrossAmmnt() {
		return medicareGrossAmmnt;
	}
	public void setMedicareGrossAmmnt(BigDecimal medicareGrossAmmnt) {
		this.medicareGrossAmmnt = medicareGrossAmmnt;
	}
	public BigDecimal getLocalGrossAmmnt() {
		return localGrossAmmnt;
	}
	public void setLocalGrossAmmnt(BigDecimal localGrossAmmnt) {
		this.localGrossAmmnt = localGrossAmmnt;
	}
	public BigDecimal getRegAmmnt() {
		return regAmmnt;
	}
	public void setRegAmmnt(BigDecimal regAmmnt) {
		this.regAmmnt = regAmmnt;
	}
	public BigDecimal getPtoAmmnt() {
		return ptoAmmnt;
	}
	public void setPtoAmmnt(BigDecimal ptoAmmnt) {
		this.ptoAmmnt = ptoAmmnt;
	}
	public BigDecimal getOtAmmnt() {
		return otAmmnt;
	}
	public void setOtAmmnt(BigDecimal otAmmnt) {
		this.otAmmnt = otAmmnt;
	}
	public BigDecimal getSalAmmnt() {
		return salAmmnt;
	}
	public void setSalAmmnt(BigDecimal salAmmnt) {
		this.salAmmnt = salAmmnt;
	}
	public BigDecimal getAdvAmmnt() {
		return advAmmnt;
	}
	public void setAdvAmmnt(BigDecimal advAmmnt) {
		this.advAmmnt = advAmmnt;
	}
	public BigDecimal getRoyaltyAmmnt() {
		return royaltyAmmnt;
	}
	public void setRoyaltyAmmnt(BigDecimal royaltyAmmnt) {
		this.royaltyAmmnt = royaltyAmmnt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
}
