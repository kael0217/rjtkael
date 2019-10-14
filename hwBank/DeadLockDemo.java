package hwBank;


public class DeadLockDemo {
	public static void main(String[] ars) throws InterruptedException{
		Integer a = 100;
		Integer b = 100;
		Thread t1 = new Thread(()->{
			move(a,b);
		}); 
		Thread t2 = new Thread(()->{
			move(b,a);
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
}

