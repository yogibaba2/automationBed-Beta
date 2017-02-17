package Ituple.automationBed_Beta.mrrSmokeTest;

public class ManagerThread implements Runnable {

	private String name;
	
	public ManagerThread(String id)
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
				
				System.out.println(name+"case uploaded");
				SmokeTest.revwr_Thread.interrupt();
				
				try {
					System.out.println(name+"Sleeping");
					Thread.sleep(10000000);
				} catch (Exception e) {
					System.out.println(name+"Started");
				}
				System.out.println(name+"Case completed");
				
				
				/*wait();
				System.out.println(name+"case uploaded");
				SmokeTest.revwr_Thread.notify();
				wait();
				System.out.println(name+"Case completed");*/
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
