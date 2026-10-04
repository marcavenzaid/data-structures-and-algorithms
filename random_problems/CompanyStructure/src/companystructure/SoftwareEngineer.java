package companystructure;

public class SoftwareEngineer extends TechnicalEmployee {
	private static float defaultBaseSalary = 75000;
	private boolean codeAccess;
	
	public SoftwareEngineer(String name) {
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
		TechnicalLead manager = (TechnicalLead) super.getManager();
				
		if(manager.approveCheckIn(this)) {
			super.increaseSuccessfulCheckIns();
			return true;
		}
		
		codeAccess = false;
		return false;		
	}
}
