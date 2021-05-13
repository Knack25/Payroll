package Gradle_Payroll.data;

public class Check {
	
	//NOTICE: ALL tax values are handled separately in the Tax type in order to facilitate a dynamic amount of taxes for each individual person.
	
	private int ID = 0;
	private double checkNum = 0;
	private double grossAmmnt = 0;
	private double netAmmnt = 0;
	private double regHours = 0;
	private double regRate = 0;
	private double ptoHours = 0;
	private double ptoRate = 0;
	private double otHours = 0;
	private double otRate = 0;
	private double salHours = 0;
	private double salRate = 0;
	private double advHours = 0;
	private double advRate = 0;
	private double royaltyRate = 0;
	private double AddStateTax = 0;
	private double AddFedTax = 0;
	
	
	private Name name;
	private Address address;
	private YTD ytd = new YTD();
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(double checkNum) {
		this.checkNum = checkNum;
	}
	public double getGrossAmmnt() {
		return grossAmmnt;
	}
	public void setGrossAmmnt(double grossAmmnt) {
		this.grossAmmnt = grossAmmnt;
	}
	public double getNetAmmnt() {
		return netAmmnt;
	}
	public void setNetAmmnt(double netAmmnt) {
		this.netAmmnt = netAmmnt;
	}
	public double getRegHours() {
		return regHours;
	}
	public void setRegHours(double regHours) {
		this.regHours = regHours;
	}
	public double getRegRate() {
		return regRate;
	}
	public void setRegRate(double regRate) {
		this.regRate = regRate;
	}
	public double getPtoHours() {
		return ptoHours;
	}
	public void setPtoHours(double ptoHours) {
		this.ptoHours = ptoHours;
	}
	public double getPtoRate() {
		return ptoRate;
	}
	public void setPtoRate(double ptoRate) {
		this.ptoRate = ptoRate;
	}
	public double getOtHours() {
		return otHours;
	}
	public void setOtHours(double otHours) {
		this.otHours = otHours;
	}
	public double getOtRate() {
		return otRate;
	}
	public void setOtRate(double otRate) {
		this.otRate = otRate;
	}
	public double getSalHours() {
		return salHours;
	}
	public void setSalHours(double salHours) {
		this.salHours = salHours;
	}
	public double getSalRate() {
		return salRate;
	}
	public void setSalRate(double salRate) {
		this.salRate = salRate;
	}
	public double getAdvHours() {
		return advHours;
	}
	public void setAdvHours(double advHours) {
		this.advHours = advHours;
	}
	public double getAdvRate() {
		return advRate;
	}
	public void setAdvRate(double advRate) {
		this.advRate = advRate;
	}
	public double getRoyaltyRate() {
		return royaltyRate;
	}
	public void setRoyaltyRate(double royaltyRate) {
		this.royaltyRate = royaltyRate;
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
	public double getAddStateTax() {
		return AddStateTax;
	}
	public void setAddStateTax(double addStateTax) {
		AddStateTax = addStateTax;
	}
	public double getAddFedTax() {
		return AddFedTax;
	}
	public void setAddFedTax(double addFedTax) {
		AddFedTax = addFedTax;
	}
	
	
	
	
}
