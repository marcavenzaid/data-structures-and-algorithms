package companystructure;

public class Accountant extends BusinessEmployee {
	private static float defaultBaseSalary = 50000;
	private TechnicalLead teamSupported;
	
	public Accountant(String name) {
		super(name, defaultBaseSalary);
	}
	
	public static float getDefaultBaseSalary() {
		return defaultBaseSalary;
	}
	
	public TechnicalLead getTeamSupported() {
		return teamSupported;
	}
	
	public void supportTeam(TechnicalLead technicalLead) {
		teamSupported = technicalLead;

		int directReportsCount = technicalLead.getDirectReportsCount();
		double seDefaultBaseSalary = SoftwareEngineer.getDefaultBaseSalary();
		double bonusBudget = directReportsCount * (seDefaultBaseSalary + seDefaultBaseSalary * 0.1);
		
		super.setBonusBudget(bonusBudget);
	}
	
	public boolean approveBonus(double bonus) {
		if(getTeamSupported() != null) {
			return bonus > super.getBonusBudget();			
		}
		
		return false;
	}
	
	@Override
	public String employeeStatus() {
		return super.employeeStatus() + "\nSupporting: " + teamSupported;
	}
}
