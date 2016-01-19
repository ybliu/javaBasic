package com.ybliu.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ABA问题模拟，线程并发中，导致ABA问题，解决方案是使用|AtomicMarkableReference
 * 请参看相应的例子：AtomicStampedReferenceTest、AtomicMarkableReferenceTest
 *
 */
public class fAtomicReferenceABATest2 {
	
	public final static AtomicReference <String>ATOMIC_REFERENCE = new AtomicReference<String>("abc");

	private final static Random RANDOM_OBJECT=new Random();
	
	public static void main(String []args) throws InterruptedException {
		
		final CountDownLatch startCountDownLatch=new CountDownLatch(1);
		
		Thread [] threads =new Thread[20];
		
		for(int i=0;i<20;i++)
		{
			final  int num=i;
			threads[i]=new Thread(){
				public void run(){
					String oldValue=ATOMIC_REFERENCE.get();
					try {
						startCountDownLatch.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						Thread.sleep(RANDOM_OBJECT.nextInt()&200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(ATOMIC_REFERENCE.compareAndSet(oldValue, oldValue+num))
					{
						System.out.println("我是线程："+num+",进行对象修改!");
						
					}
					
				}
				
			};
			threads[i].start();
			
		}
		Thread.sleep(200);
		startCountDownLatch.countDown();
		new Thread(){
			public void run(){
				try {
					Thread.sleep(RANDOM_OBJECT.nextInt()&200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(!ATOMIC_REFERENCE.compareAndSet(ATOMIC_REFERENCE.get(), "adc"));
				System.out.println("已经修改为原始值！");
			}
		}.start();
	}
} 