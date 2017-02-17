package Ituple.automationBed_Beta.mrrSmokeTest;

public class QAThread implements Runnable {

	private String name;
	
	public QAThread(String id)
    {
        name = id;
    }
	
	@Override
	public void run() {
		try {
			
				System.out.println(name+"login done");
				try {
					System.out.println(name+"Sleeping");
					Thread.sleep(10000000);
				} catch (Exception e) {
					System.out.println(name+"Started");
				}
				System.out.println(name+"Discrepancy Resolved");
				System.out.println(name+"Escalation Answered");
				SmokeTest.mgr_Thread.interrupt();
				
				/*wait();
				System.out.println(name+"Discrepancy Resolved");
				System.out.println(name+"Escalation Answered");
				SmokeTest.mgr_Thread.notify();*/
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
