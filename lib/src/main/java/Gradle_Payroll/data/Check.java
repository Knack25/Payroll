package Gradle_Payroll.data;

public class Check {
	
	//NOTICE: ALL tax values are handled separately in the Tax type in order to facilitate a dynamic amount of taxes for each individual person.
	
	private int ID = 0;
	private double checkNum = 0;
	private double grossAmmnt = 0;
	private double fedGrossAmmnt = 0;
	private double stateGrossAmmnt = 0;
	private double state2GrossAmmnt = 0;
	private double sscGrossAmmnt = 0;
	private double medicareGrossAmmnt = 0;
	private double localGrossAmmnt = 0;
	private double netAmmnt = 0;
	private double regHours = 0;
	private double regRate = 0;
	private double regAmmnt = 0;
	private double ptoHours = 0;
	private double ptoRate = 0;
	private double ptoAmmnt = 0;
	private double otHours = 0;
	private double otRate = 0;
	private double otAmmnt = 0;
	private double salAmmnt =0;
	private double advAmmnt =0;
	private double royaltyAmmnt = 0;
	private double AddStateTax = 0;
	private double AddFedTax = 0;
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
	
	public double getRoyaltyRate() {
		return royaltyAmmnt;
	}
	public void setRoyaltyRate(double royaltyRate) {
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
	public double getFedGrossAmmnt() {
		return fedGrossAmmnt;
	}
	public void setFedGrossAmmnt(double fedGrossAmmnt) {
		this.fedGrossAmmnt = fedGrossAmmnt;
	}
	public double getStateGrossAmmnt() {
		return stateGrossAmmnt;
	}
	public void setStateGrossAmmnt(double stateGrossAmmnt) {
		this.stateGrossAmmnt = stateGrossAmmnt;
	}
	public double getState2GrossAmmnt() {
		return state2GrossAmmnt;
	}
	public void setState2GrossAmmnt(double state2GrossAmmnt) {
		this.state2GrossAmmnt = state2GrossAmmnt;
	}
	public double getSscGrossAmmnt() {
		return sscGrossAmmnt;
	}
	public void setSscGrossAmmnt(double sscGrossAmmnt) {
		this.sscGrossAmmnt = sscGrossAmmnt;
	}
	public double getMedicareGrossAmmnt() {
		return medicareGrossAmmnt;
	}
	public void setMedicareGrossAmmnt(double medicareGrossAmmnt) {
		this.medicareGrossAmmnt = medicareGrossAmmnt;
	}
	public double getLocalGrossAmmnt() {
		return localGrossAmmnt;
	}
	public void setLocalGrossAmmnt(double localGrossAmmnt) {
		this.localGrossAmmnt = localGrossAmmnt;
	}
	public double getRegAmmnt() {
		return regAmmnt;
	}
	public void setRegAmmnt(double regAmmnt) {
		this.regAmmnt = regAmmnt;
	}
	public double getPtoAmmnt() {
		return ptoAmmnt;
	}
	public void setPtoAmmnt(double ptoAmmnt) {
		this.ptoAmmnt = ptoAmmnt;
	}
	public double getOtAmmnt() {
		return otAmmnt;
	}
	public void setOtAmmnt(double otAmmnt) {
		this.otAmmnt = otAmmnt;
	}
	public double getSalAmmnt() {
		return salAmmnt;
	}
	public void setSalAmmnt(double salAmmnt) {
		this.salAmmnt = salAmmnt;
	}
	public double getAdvAmmnt() {
		return advAmmnt;
	}
	public void setAdvAmmnt(double advAmmnt) {
		this.advAmmnt = advAmmnt;
	}
	public double getRoyaltyAmmnt() {
		return royaltyAmmnt;
	}
	public void setRoyaltyAmmnt(double royaltyAmmnt) {
		this.royaltyAmmnt = royaltyAmmnt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
}
