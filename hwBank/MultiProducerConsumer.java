package hwBank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiProducerConsumer {
	public static void main(String[] args) throws InterruptedException {
		Inventory inventory = new Inventory();
		
		
		Thread producer1 = new Thread(()->{
			for( int i = 0; i < 1000; ++i ) {
				inventory.add();
			}
		}) ;
		Thread producer2 = new Thread(()->{
			for( int i = 0; i < 1000; ++i ) {
				inventory.add();
			}
		}) ;
		Thread producer3 = new Thread(()->{
			for( int i = 0; i < 1000; ++i ) {
				inventory.add();
			}
		}) ;
		Thread consumer1 = new Thread(()->{
			for( int i = 0; i < 1000; ++i ) {
				inventory.remove();;
			}
		}) ;
		Thread consumer2 = new Thread(()->{
			for( int i = 0; i < 1000; ++i ) {
				inventory.remove();;
			}
		}) ;
		Thread consumer3 = new Thread(()->{
			for( int i = 0; i < 1000; ++i ) {
				inventory.remove();;
			}
		}) ;
		
		producer1.start();
		producer2.start();
		producer3.start();
		consumer1.start();
		consumer2.start();
		consumer3.start();
		producer1.join();
		producer2.join();
		producer3.join();
		consumer1.join();
		consumer2.join();
		consumer3.join();
		
		System.out.println(inventory.list.size());// 0
	}
	
	
}

class Inventory {
	List<Integer> list = new ArrayList<>();
	Lock lock = new ReentrantLock();
	Condition add = lock.newCondition();
	Condition remove= lock.newCondition();
	public void add() {
		while (true) {
			try {				
				if (lock.tryLock(1, TimeUnit.SECONDS)) {// lock was acquired on obj
					if (list.size() < 10) { 
						list.add((int) (Math.random() * 1000));
						remove.signalAll();
						lock.unlock();
						break;
					}else {
						add.await();
						lock.unlock();
					}			
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

	public void remove() {
		while (true) {
			try {
				if (lock.tryLock(1, TimeUnit.SECONDS)) {
					if (list.size() > 0) {
						list.remove(0);
						add.signalAll();
						lock.unlock();
						break;
					} else {
						remove.await();
						lock.unlock();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}