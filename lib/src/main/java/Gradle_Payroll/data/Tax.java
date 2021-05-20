package Gradle_Payroll.data;

import java.math.BigDecimal;

public class Tax {

	
	
	public Tax() {
		super();
		this.name = "Name";
		this.ID = 0;
		this.type = 0;
		this.ammount = new BigDecimal(0);
		this.netAmmount = new BigDecimal(0);
		this.fedTaxExempt = false;
		this.stateTaxExempt = false;
		this.state2TaxExempt = false;
		this.sscTaxExempt = false;
		this.medicareTaxeExempt = false;
		this.localTaxExempt = false;
		this.primaryTax = false;
		this.InitYTD = new BigDecimal(0);
	}
	
	private String name;
	private int ID;
	private int type;
	private BigDecimal ammount;
	private BigDecimal netAmmount;
	private boolean fedTaxExempt;
	private boolean stateTaxExempt;
	private boolean state2TaxExempt;
	private boolean sscTaxExempt;
	private boolean medicareTaxeExempt;
	private boolean localTaxExempt;
	private boolean primaryTax;
	private BigDecimal InitYTD;
	private BigDecimal FinalYTD;
	private int employee_id;
	
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public BigDecimal getInitYTD() {
		return InitYTD;
	}
	public void setInitYTD(BigDecimal yTD) {
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public BigDecimal getAmmount() {
		return ammount;
	}
	public void setAmmount(BigDecimal ammount) {
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
	public BigDecimal getNetAmmount() {
		return netAmmount;
	}
	public void setNetAmmount(BigDecimal netAmmount) {
		this.netAmmount = netAmmount;
	}
	public BigDecimal getFinalYTD() {
		return FinalYTD;
	}
	public void setFinalYTD(BigDecimal finalYTD) {
		FinalYTD = finalYTD;
	}
	
}