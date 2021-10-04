package fr.m2i.myagenda.test;

public class TestProcess extends Thread {

	public static void main(String[] args) {
		TestProcess testProcess = new TestProcess();
		testProcess.start();
		
		testProcess = new TestProcess();
		testProcess.start();
		
		testProcess = new TestProcess();
		testProcess.start();
		
		System.out.println("Suite de l'application");
	}

	public TestProcess() {
		System.out.println("Test process");
	}

	@Override
	public void run() {
		System.out.println("Run est appelé");

		boolean loop = true;
		while (loop) {
			System.out.println("traitement quelconque " + this.hashCode());
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
