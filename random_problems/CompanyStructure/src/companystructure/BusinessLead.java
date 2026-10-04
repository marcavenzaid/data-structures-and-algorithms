package companystructure;

import java.util.LinkedList;
import java.util.List;

public class BusinessLead extends BusinessEmployee {
	private static float baseSalaryMultiplier = 2f;
	private int headCount;
	private int directReportsCount;
	private List<Accountant> directReports = new LinkedList<Accountant>();
	
	public BusinessLead(String name) {
		super(name, Accountant.getDefaultBaseSalary() * baseSalaryMultiplier);
		this.headCount = 10;
	}
	
	public boolean hasHeadCount() {
		return directReportsCount < headCount;
	}
	
	public boolean addReport(Accountant accountant, TechnicalLead teamToSupport) {
		if(hasHeadCount()) {
			directReports.add(accountant);
			increaseBonusBudget(accountant);
			accountant.supportTeam(teamToSupport);
			teamToSupport.setAccountantSupport(accountant);
			return true;
		}
		
		return false;
	}
	
	private void increaseBonusBudget(Accountant accountant) {
		super.setBonusBudget(super.getBonusBudget() + accountant.getBaseSalary() * 1.1); 
	}
	
	public boolean requestBonus(Employee employee, double bonus) {
		if(bonus < super.getBonusBudget()) {
			employee.setBonus(bonus);
			super.setBonusBudget(super.getBonusBudget() - bonus);
			return true;
		}
		
		return false;
	}
	
	public boolean approveBonus(Employee employee, double bonus) {
		for(Accountant a : directReports) {
			if(a.getTeamSupported() == employee.getManager()) {
				if(a.approveBonus(bonus)) {
					employee.setBonus(bonus);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public String getTeamStatus() {
		StringBuilder teamStatus = new StringBuilder(super.employeeStatus() + "\n\n");
		
		for(Accountant e : directReports) {
			teamStatus.append(e.employeeStatus() + "\n\n");
		}
		
		return teamStatus.toString();
	}
}
