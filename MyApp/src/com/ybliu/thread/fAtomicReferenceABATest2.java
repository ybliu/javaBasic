package com.ybliu.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ABA����ģ�⣬�̲߳����У�����ABA���⣬���������ʹ��|AtomicMarkableReference
 * ��ο���Ӧ�����ӣ�AtomicStampedReferenceTest��AtomicMarkableReferenceTest
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
						System.out.println("�����̣߳�"+num+",���ж����޸�!");
						
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
				System.out.println("�Ѿ��޸�Ϊԭʼֵ��");
			}
		}.start();
	}
} 