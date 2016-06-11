/**
 * 
 */
package chapter6;

import java.util.Random;

/**
 * @author Administrator
 * 
 */
public class SyHighDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		SyHighDemo demo = new SyHighDemo();
		demo.runTest();
	}

	private Random random = new Random();

	private Object[] locks;

	private void runTest() throws Exception {
		int cpuCount = Runtime.getRuntime().availableProcessors() * 150;
		locks = new Object[cpuCount];
		for (int i = 0; i < cpuCount; i++) {
			locks[i] = new Object();
		}
		for (int i = 0; i < cpuCount; i++) {
			new Thread(new ATask(i)).start();
		}
		for (int i = 0; i < cpuCount; i++) {
			new Thread(new BTask(i)).start();
		}
	}

	class ATask implements Runnable {

		private Object lockObject = null;

		public ATask(int i) {
			lockObject = locks[i];
		}

		@Override
		public void run() {
			while (true) {
				try {
					synchronized (lockObject) {
						lockObject.wait(random.nextInt(10));
					}
				} catch (Exception e) {
					;
				}
			}
		}
	}

	class BTask implements Runnable {

		private Object lockObject = null;

		public BTask(int i) {
			lockObject = locks[i];
		}

		@Override
		public void run() {
			while (true) {
				synchronized (lockObject) {
					lockObject.notifyAll();
				}
				try {
					Thread.sleep(random.nextInt(10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}