package com.ybliu.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * ABA����ģ�⣬�̲߳����У�����ABA���⣬���������ʹ��|AtomicMarkableReference
 * ��ο���Ӧ�����ӣ�AtomicStampedReferenceTest��AtomicMarkableReferenceTest
 *
 */
public class AtomicReferenceABATest {
	
	public final static AtomicReference <String>ATOMIC_REFERENCE = new AtomicReference<String>("abc");

	public static void main(String []args) {
		for(int i = 0 ; i < 100 ; i++) {
			final int num = i;
			new Thread() {
				public void run() {
					try {
						Thread.sleep(Math.abs((int)(Math.random() * 100)));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(ATOMIC_REFERENCE.compareAndSet("abc" , "abc2")) {
						System.out.println("�����̣߳�" + num + ",�һ�����������˶����޸ģ�");
					}
				}
			}.start();
		}
		new Thread() {
			public void run() {
				while(!ATOMIC_REFERENCE.compareAndSet("abc2", "abc"));
				System.out.println("�Ѿ���Ϊԭʼֵ��");
			}
		}.start();
	}
} 