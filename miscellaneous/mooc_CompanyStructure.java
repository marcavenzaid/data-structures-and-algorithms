import java.util.LinkedList;
import java.util.List;

public class mooc_CompanyStructure {
    
	public static void main(String[] args) {
		MOOCCompanyStructureTechnicalLead CTO = new MOOCCompanyStructureTechnicalLead("Satya Nadella");
        MOOCCompanyStructureSoftwareEngineer seA = new MOOCCompanyStructureSoftwareEngineer("Kasey");
        MOOCCompanyStructureSoftwareEngineer seB = new MOOCCompanyStructureSoftwareEngineer("Breana");
        MOOCCompanyStructureSoftwareEngineer seC = new MOOCCompanyStructureSoftwareEngineer("Eric");
        CTO.addReport(seA);
        CTO.addReport(seB);
        CTO.addReport(seC);
        System.out.println(CTO.getTeamStatus());

        MOOCCompanyStructureTechnicalLead VPofENG = new MOOCCompanyStructureTechnicalLead("Bill Gates");
        MOOCCompanyStructureSoftwareEngineer seD = new MOOCCompanyStructureSoftwareEngineer("Winter");
        MOOCCompanyStructureSoftwareEngineer seE = new MOOCCompanyStructureSoftwareEngineer("Libby");
        MOOCCompanyStructureSoftwareEngineer seF = new MOOCCompanyStructureSoftwareEngineer("Gizan");
        MOOCCompanyStructureSoftwareEngineer seG = new MOOCCompanyStructureSoftwareEngineer("Zaynah");
        VPofENG.addReport(seD);
        VPofENG.addReport(seE);
        VPofENG.addReport(seF);
        VPofENG.addReport(seG);
        System.out.println(VPofENG.getTeamStatus());

        MOOCCompanyStructureBusinessLead CFO = new MOOCCompanyStructureBusinessLead("Amy Hood");
        MOOCCompanyStructureAccountant actA = new MOOCCompanyStructureAccountant("Niky");
        MOOCCompanyStructureAccountant actB = new MOOCCompanyStructureAccountant("Andrew");
        CFO.addReport(actA, CTO);
        CFO.addReport(actB, VPofENG);
        System.out.println(CFO.getTeamStatus());
	}
}

class MOOCCompanyStructureAccountant extends MOOCCompanyStructureBusinessEmployee {
	private static float defaultBaseSalary = 50000;
	private MOOCCompanyStructureTechnicalLead teamSupported;
	
	public MOOCCompanyStructureAccountant(String name) {
		super(name, defaultBaseSalary);
	}
	
	public static float getDefaultBaseSalary() {
		return defaultBaseSalary;
	}
	
	public MOOCCompanyStructureTechnicalLead getTeamSupported() {
		return teamSupported;
	}
	
	public void supportTeam(MOOCCompanyStructureTechnicalLead technicalLead) {
		teamSupported = technicalLead;

		int directReportsCount = technicalLead.getDirectReportsCount();
		double seDefaultBaseSalary = MOOCCompanyStructureSoftwareEngineer.getDefaultBaseSalary();
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

abstract class MOOCCompanyStructureBusinessEmployee extends MOOCCompanyStructureEmployee {
	private double bonusBudget;
	
	public MOOCCompanyStructureBusinessEmployee(String name, double baseSalary) {
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

class MOOCCompanyStructureBusinessLead extends MOOCCompanyStructureBusinessEmployee {
	private static float baseSalaryMultiplier = 2f;
	private int headCount;
	private int directReportsCount;
	private List<MOOCCompanyStructureAccountant> directReports = new LinkedList<MOOCCompanyStructureAccountant>();
	
	public MOOCCompanyStructureBusinessLead(String name) {
		super(name, MOOCCompanyStructureAccountant.getDefaultBaseSalary() * baseSalaryMultiplier);
		this.headCount = 10;
	}
	
	public boolean hasHeadCount() {
		return directReportsCount < headCount;
	}
	
	public boolean addReport(MOOCCompanyStructureAccountant accountant, MOOCCompanyStructureTechnicalLead teamToSupport) {
		if(hasHeadCount()) {
			directReports.add(accountant);
			increaseBonusBudget(accountant);
			accountant.supportTeam(teamToSupport);
			teamToSupport.setAccountantSupport(accountant);
			return true;
		}
		
		return false;
	}
	
	private void increaseBonusBudget(MOOCCompanyStructureAccountant accountant) {
		super.setBonusBudget(super.getBonusBudget() + accountant.getBaseSalary() * 1.1); 
	}
	
	public boolean requestBonus(MOOCCompanyStructureEmployee employee, double bonus) {
		if(bonus < super.getBonusBudget()) {
			employee.setBonus(bonus);
			super.setBonusBudget(super.getBonusBudget() - bonus);
			return true;
		}
		
		return false;
	}
	
	public boolean approveBonus(MOOCCompanyStructureEmployee employee, double bonus) {
		for(MOOCCompanyStructureAccountant a : directReports) {
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
		
		for(MOOCCompanyStructureAccountant e : directReports) {
			teamStatus.append(e.employeeStatus() + "\n\n");
		}
		
		return teamStatus.toString();
	}
}

abstract class MOOCCompanyStructureEmployee {
	private int id;
	private String name;
	private double baseSalary;
	private static int employeesCount;
	private MOOCCompanyStructureEmployee manager;
	private double bonus;
		
	public MOOCCompanyStructureEmployee(String name, double baseSalary) {
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
	
	public MOOCCompanyStructureEmployee getManager() {
		return manager;
	}
	
	public void setManager(MOOCCompanyStructureEmployee manager) {
		this.manager = manager;
	}
	
	public boolean equals(MOOCCompanyStructureEmployee other) {
		return getID() == other.getID();
	}
	
	public abstract String employeeStatus();
}

class MOOCCompanyStructureSoftwareEngineer extends MOOCCompanyStructureTechnicalEmployee {
	private static float defaultBaseSalary = 75000;
	private boolean codeAccess;
	
	public MOOCCompanyStructureSoftwareEngineer(String name) {
		super(name, defaultBaseSalary);
	}
	
	public static double getDefaultBaseSalary() {
		return defaultBaseSalary;
	}
	
	public boolean getCodeAccess() {
		return codeAccess;
	}
	
	public void setCodeAcess(boolean access) {
		codeAccess = access;
	}
	
	public boolean checkInCode() {
		MOOCCompanyStructureTechnicalLead manager = (MOOCCompanyStructureTechnicalLead) super.getManager();
				
		if(manager.approveCheckIn(this)) {
			super.increaseSuccessfulCheckIns();
			return true;
		}
		
		codeAccess = false;
		return false;		
	}
}

abstract class MOOCCompanyStructureTechnicalEmployee extends MOOCCompanyStructureEmployee {
	private int successfulCheckIns;
	
	public MOOCCompanyStructureTechnicalEmployee(String name, double baseSalary) {
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

class MOOCCompanyStructureTechnicalLead extends MOOCCompanyStructureTechnicalEmployee {	
	private static float baseSalaryMultplier = 1.3f;
	private int headCount;
	private int directReportsCount;
	private List<MOOCCompanyStructureSoftwareEngineer> directReports = new LinkedList<MOOCCompanyStructureSoftwareEngineer>();
	private MOOCCompanyStructureAccountant accountantSupport;
	
	public MOOCCompanyStructureTechnicalLead(String name) {
		super(name, MOOCCompanyStructureSoftwareEngineer.getDefaultBaseSalary() * baseSalaryMultplier);		
		this.headCount = 4;
	}
	
	public MOOCCompanyStructureAccountant getAccountantSupport() {
		return accountantSupport;
	}
	
	public void setAccountantSupport(MOOCCompanyStructureAccountant accountant) {
		accountantSupport = accountant;
	}
	
	public boolean hasHeadCount() {
		return directReportsCount < headCount;
	}
	
	public boolean addReport(MOOCCompanyStructureSoftwareEngineer se) {
		if(hasHeadCount()) {
			directReports.add(se);
			se.setManager(this);
			return true;
		}
		
		return false;
	}
	
	public boolean approveCheckIn(MOOCCompanyStructureSoftwareEngineer se) {
		if(directReports.contains(se) && se.getCodeAccess() == true) {
			return true;
		}
		
		return false;
	}
	
	public int getDirectReportsCount() {
		return directReports.size();
	}
	
	public boolean requestBonus(MOOCCompanyStructureEmployee employee, double bonus) {
		MOOCCompanyStructureBusinessLead businessLeadSupport = (MOOCCompanyStructureBusinessLead) accountantSupport.getManager();
		
		if(businessLeadSupport.requestBonus(employee, bonus)) {
			employee.setBonus(bonus);
			return true;
		}
		
		return false;
	}
	
	public String getTeamStatus() {
		StringBuilder teamStatus = new StringBuilder(super.employeeStatus() + "\n\n");
		
		for(MOOCCompanyStructureSoftwareEngineer e : directReports) {
			teamStatus.append(e.employeeStatus() + "\n\n");
		}
		
		return teamStatus.toString();
	}
}