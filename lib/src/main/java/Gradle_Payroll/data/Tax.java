package Gradle_Payroll.data;



public class Tax {

	
	
	public Tax() {
		super();
	}
	public Tax(String name, int iD, String type, double ammount, boolean fedTaxExempt, boolean stateTaxExempt,
			boolean statePATaxExempt, boolean sscTaxExempt, boolean medicareTaxeExempt, boolean localTaxExempt,
			double yTD) {
		super();
		this.name = name;
		ID = iD;
		this.type = type;
		this.ammount = ammount;
		this.fedTaxExempt = fedTaxExempt;
		this.stateTaxExempt = stateTaxExempt;
		this.statePATaxExempt = statePATaxExempt;
		this.sscTaxExempt = sscTaxExempt;
		this.medicareTaxeExempt = medicareTaxeExempt;
		this.localTaxExempt = localTaxExempt;
		YTD = yTD;
	}
	private String name;
	private int ID;
	private String type;
	private double ammount;
	private boolean fedTaxExempt;
	private boolean stateTaxExempt;
	private boolean statePATaxExempt;
	private boolean sscTaxExempt;
	private boolean medicareTaxeExempt;
	private boolean localTaxExempt;
	private double YTD;
	
	public double getYTD() {
		return YTD;
	}
	public void setYTD(double yTD) {
		YTD = yTD;
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
	public boolean isStatePATaxExempt() {
		return statePATaxExempt;
	}
	public void setStatePATaxExempt(boolean statePATaxExempt) {
		this.statePATaxExempt = statePATaxExempt;
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
	
}