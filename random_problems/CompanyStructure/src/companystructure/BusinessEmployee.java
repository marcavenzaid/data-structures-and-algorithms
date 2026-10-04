package companystructure;

public abstract class BusinessEmployee extends Employee {
	private double bonusBudget;
	
	public BusinessEmployee(String name, double baseSalary) {
		super(name, baseSalary);
	}
	
	public double getBonusBudget() {
		return bonusBudget;
	}
	
	public void setBonusBudget(double bonusBudget) {
		this.bonusBudget = bonusBudget;
	}
	
	@Override
	public String employeeStatus() {
		return super.toString() + "\nBudget: " + getBonusBudget();
	}
}
