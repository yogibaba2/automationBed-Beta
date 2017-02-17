package Ituple.automationBed_Beta.mrrSmokeTest;

public class ReviewerThread implements Runnable {

	private String name;
	
	public ReviewerThread(String id)
    {
        name = id;
    }
	
	@Override
	public void run() {
		try {
			
				System.out.println(name+"login done");
				SmokeTest.mgr_Thread.interrupt();
				try {
					System.out.println(name+"Sleeping");
					Thread.sleep(10000000);
				} catch (Exception e) {
					System.out.println(name+"Started");
				}
				System.out.println(name+"Case Escalated");
				System.out.println(name+"Case Discrepancy");
				SmokeTest.qa_Thread.interrupt();
				
				/*SmokeTest.mgr_Thread.notify();
				wait();
				System.out.println(name+"Case Escalated");
				System.out.println(name+"Case Discrepancy");
				SmokeTest.qa_Thread.notify();
				wait();*/
				 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
