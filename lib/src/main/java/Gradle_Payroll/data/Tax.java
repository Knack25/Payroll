package Gradle_Payroll.data;



public class Tax {

	
	
	public Tax() {
		super();
		this.name = "Name";
		this.ID = 0;
		this.type = "Type";
		this.ammount = 00.00;
		this.netAmmount = 00.00;
		this.fedTaxExempt = false;
		this.stateTaxExempt = false;
		this.state2TaxExempt = false;
		this.sscTaxExempt = false;
		this.medicareTaxeExempt = false;
		this.localTaxExempt = false;
		this.primaryTax = false;
		this.InitYTD = 00.00;
	}
	
	private String name;
	private int ID;
	private String type;
	private double ammount;
	private double netAmmount;
	private boolean fedTaxExempt;
	private boolean stateTaxExempt;
	private boolean state2TaxExempt;
	private boolean sscTaxExempt;
	private boolean medicareTaxeExempt;
	private boolean localTaxExempt;
	private boolean primaryTax;
	private double InitYTD;
	private double FinalYTD;
	private int employee_id;
	
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public double getInitYTD() {
		return InitYTD;
	}
	public void setInitYTD(double yTD) {
		InitYTD = yTD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmmount() {
		return ammount;
	}
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	public boolean isFedTaxExempt() {
		return fedTaxExempt;
	}
	public void setFedTaxExempt(boolean fedTaxExempt) {
		this.fedTaxExempt = fedTaxExempt;
	}
	public boolean isStateTaxExempt() {
		return stateTaxExempt;
	}
	public void setStateTaxExempt(boolean stateTaxExempt) {
		this.stateTaxExempt = stateTaxExempt;
	}
	public boolean isState2TaxExempt() {
		return state2TaxExempt;
	}
	public void setState2TaxExempt(boolean state2TaxExempt) {
		this.state2TaxExempt = state2TaxExempt;
	}
	public boolean isSscTaxExempt() {
		return sscTaxExempt;
	}
	public void setSscTaxExempt(boolean sscTaxExempt) {
		this.sscTaxExempt = sscTaxExempt;
	}
	public boolean isMedicareTaxeExempt() {
		return medicareTaxeExempt;
	}
	public void setMedicareTaxeExempt(boolean medicareTaxeExempt) {
		this.medicareTaxeExempt = medicareTaxeExempt;
	}
	public boolean isLocalTaxExempt() {
		return localTaxExempt;
	}
	public void setLocalTaxExempt(boolean localTaxExempt) {
		this.localTaxExempt = localTaxExempt;
	}
	public boolean isPrimaryTax() {
		return primaryTax;
	}
	public void setPrimaryTax(boolean primaryTax) {
		this.primaryTax = primaryTax;
	}
	public double getNetAmmount() {
		return netAmmount;
	}
	public void setNetAmmount(double netAmmount) {
		this.netAmmount = netAmmount;
	}
	public double getFinalYTD() {
		return FinalYTD;
	}
	public void setFinalYTD(double finalYTD) {
		FinalYTD = finalYTD;
	}
	
}