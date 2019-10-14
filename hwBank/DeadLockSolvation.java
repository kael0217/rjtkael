package hwBank;


public class DeadLockSolvation {
	public static void main(String[] ars) throws InterruptedException{
		Integer a = 100;
		Integer b = 100;
		Thread t1 = new Thread(()->{
			move(a,b);
		}); 
		Thread t2 = new Thread(()->{
			reverse(a, b);
		}); 
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
	
	public static void move(Integer a,Integer b) {
		synchronized (a) {
			while(a!=0) {
				a--;
				synchronized (b) {
					b++;
				}
			}
		}
	}
	public static void reverse(Integer a,Integer b) {
		synchronized (a) {
			while(a!=0) {
				synchronized (b) {
					b--;
				}
				a++;
			}
		}
	}
}