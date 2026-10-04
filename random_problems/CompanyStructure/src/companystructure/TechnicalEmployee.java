package companystructure;

public abstract class TechnicalEmployee extends Employee {
	private int successfulCheckIns;
	
	public TechnicalEmployee(String name, double baseSalary) {
		super(name, baseSalary);
	}	
	
	public void increaseSuccessfulCheckIns() {
		successfulCheckIns++;
	}
	
	public int getSuccessfulCheckIns() {
		return successfulCheckIns;
	}
	
	@Override
	public String employeeStatus() {
		return super.toString() + "\nSuccessful Check-ins: " + getSuccessfulCheckIns();
	}
}
