package Ituple.automationBed_Beta.utility.base;

public enum Constant{
	URL(""), 
	ReportPath(System.getProperty("user.dir")+"\\test-output\\AdvanceReports\\"),
	ResourcePath(System.getProperty("user.dir") + "\\src\\main\\resources\\datafiles\\"),
	SuiteName(""),
	TestCase("");
	 private String constant;
	 
	 private Constant(String constant){
	    this.constant = constant;
	  }

	

	  public String getConstant(){
	    return this.constant;
	  }

	  public void setConstant(String constant){
		Utils.addLog.traceEntry();
		Utils.addLog.info("setting up {} with : {}",this.name(),constant);
	    this.constant = constant;
	    Utils.addLog.traceExit();
	  }

	  public String toString(){
	    return this.constant;
	  }
	}