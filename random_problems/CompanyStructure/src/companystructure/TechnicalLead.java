package companystructure;

import java.util.LinkedList;
import java.util.List;

public class TechnicalLead extends TechnicalEmployee {	
	private static float baseSalaryMultplier = 1.3f;
	private int headCount;
	private int directReportsCount;
	private List<SoftwareEngineer> directReports = new LinkedList<SoftwareEngineer>();
	private Accountant accountantSupport;
	
	public TechnicalLead(String name) {
		super(name, SoftwareEngineer.getDefaultBaseSalary() * baseSalaryMultplier);		
		this.headCount = 4;
	}
	
	public Accountant getAccountantSupport() {
		return accountantSupport;
	}
	
	public void setAccountantSupport(Accountant accountant) {
		accountantSupport = accountant;
	}
	
	public boolean hasHeadCount() {
		return directReportsCount < headCount;
	}
	
	public boolean addReport(SoftwareEngineer se) {
		if(hasHeadCount()) {
			directReports.add(se);
			se.setManager(this);
			return true;
		}
		
		return false;
	}
	
	public boolean approveCheckIn(SoftwareEngineer se) {
		if(directReports.contains(se) && se.getCodeAccess() == true) {
			return true;
		}
		
		return false;
	}
	
	public int getDirectReportsCount() {
		return directReports.size();
	}
	
	public boolean requestBonus(Employee employee, double bonus) {
		BusinessLead businessLeadSupport = (BusinessLead) accountantSupport.getManager();
		
		if(businessLeadSupport.requestBonus(employee, bonus)) {
			employee.setBonus(bonus);
			return true;
		}
		
		return false;
	}
	
	public String getTeamStatus() {
		StringBuilder teamStatus = new StringBuilder(super.employeeStatus() + "\n\n");
		
		for(SoftwareEngineer e : directReports) {
			teamStatus.append(e.employeeStatus() + "\n\n");
		}
		
		return teamStatus.toString();
	}
}
