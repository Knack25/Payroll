package Gradle_Payroll.data;



public class Employee {

	
	
	
	public Employee(int iD, Name name, String status, Address address, String telnum, String email, String sex,
		String ssn, String jobTitle, String dOB, String dOH, String dOT, double salary, double regPay,
		double regHour, double otPay, double otHour, double ptoPay, double ptoHour, double localTaxCode,
		double addStateTax, double addFedTax, double vacationTimeUsed, double vacationTimeRemaining,
		int department, double[] yTD, Tax[] tax) {
	super();
	ID = iD;
	this.name = name;
	this.status = status;
	this.address = address;
	this.telnum = telnum;
	this.email = email;
	this.sex = sex;
	this.ssn = ssn;
	this.jobTitle = jobTitle;
	DOB = dOB;
	DOH = dOH;
	DOT = dOT;
	this.salary = salary;
	this.regPay = regPay;
	this.regHour = regHour;
	this.otPay = otPay;
	this.otHour = otHour;
	this.ptoPay = ptoPay;
	this.ptoHour = ptoHour;
	this.localTaxCode = localTaxCode;
	this.addStateTax = addStateTax;
	this.addFedTax = addFedTax;
	this.vacationTimeUsed = vacationTimeUsed;
	this.vacationTimeRemaining = vacationTimeRemaining;
	this.department = department;
	YTD = yTD;
	this.tax = tax;
	
}
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	private int ID;
	private Name name = new Name();
	private String status;
	private Address address = new Address();
	private String telnum;
	private String email;
	private String sex;
	private String ssn;
	private String jobTitle;
	private String DOB;
	private String DOH;
	private String DOT;
	private double salary;
	private double regPay;
	private double regHour;
	private double otPay;
	private double otHour;
	private double ptoPay;
	private double ptoHour;
	private double localTaxCode;
	private double addStateTax;
	private double addFedTax;
	private double vacationTimeUsed;
	private double vacationTimeRemaining;
	private int department;
	private double YTD[] = new double[27];
	private Tax tax[] = new Tax[27];
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String string) {
		this.sex = string;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getDOH() {
		return DOH;
	}
	public void setDOH(String dOH) {
		DOH = dOH;
	}
	public String getDOT() {
		return DOT;
	}
	public void setDOT(String dOT) {
		DOT = dOT;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getRegPay() {
		return regPay;
	}
	public void setRegPay(double regPay) {
		this.regPay = regPay;
	}
	public double getRegHour() {
		return regHour;
	}
	public void setRegHour(double regHour) {
		this.regHour = regHour;
	}
	public double getOtPay() {
		return otPay;
	}
	public void setOtPay(double otPay) {
		this.otPay = otPay;
	}
	public double getOtHour() {
		return otHour;
	}
	public void setOtHour(double otHour) {
		this.otHour = otHour;
	}
	public double getPtoPay() {
		return ptoPay;
	}
	public void setPtoPay(double ptoPay) {
		this.ptoPay = ptoPay;
	}
	public double getPtoHour() {
		return ptoHour;
	}
	public void setPtoHour(double ptoHour) {
		this.ptoHour = ptoHour;
	}
	public double getLocalTaxCode() {
		return localTaxCode;
	}
	public void setLocalTaxCode(double localTaxCode) {
		this.localTaxCode = localTaxCode;
	}
	public double getAddStateTax() {
		return addStateTax;
	}
	public void setAddStateTax(double addStateTax) {
		this.addStateTax = addStateTax;
	}
	public double getAddFedTax() {
		return addFedTax;
	}
	public void setAddFedTax(double addFedTax) {
		this.addFedTax = addFedTax;
	}
	public double getVacationTimeUsed() {
		return vacationTimeUsed;
	}
	public void setVacationTimeUsed(double vacationTimeUsed) {
		this.vacationTimeUsed = vacationTimeUsed;
	}
	public double getVacationTimeRemaining() {
		return vacationTimeRemaining;
	}
	public void setVacationTimeRemaining(double vacationTimeRemaining) {
		this.vacationTimeRemaining = vacationTimeRemaining;
	}
	public int getDepartment() {
		return department;
	}
	public void setDepartment(int department) {
		this.department = department;
	}
	public double[] getYTD() {
		return YTD;
	}
	public void setYTD(double[] yTD) {
		YTD = yTD;
	}
	public Tax[] getTax() {
		return tax;
	}
	public void setTax(Tax[] tax) {
		this.tax = tax;
	}
	
	
	
}
