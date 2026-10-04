package companystructure;

public abstract class Employee {
	private int id;
	private String name;
	private double baseSalary;
	private static int employeesCount;
	private Employee manager;
	private double bonus;
		
	public Employee(String name, double baseSalary) {
		this.name = name;
		this.baseSalary = baseSalary;
		this.id = ++employeesCount;
	}
	
	public double getBaseSalary() {
		return baseSalary;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "ID: " + id + "\nName: " + name;
	}
	
	public double getBonus() {
		return bonus;
	}
	
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	public Employee getManager() {
		return manager;
	}
	
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	public boolean equals(Employee other) {
		return getID() == other.getID();
	}
	
	public abstract String employeeStatus();
}
