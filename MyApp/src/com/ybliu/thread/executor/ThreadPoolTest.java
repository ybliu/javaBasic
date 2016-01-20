package com.ybliu.thread.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

 public class ThreadPoolTest{ 
	/*
	 http://blog.csdn.net/aitangyong/article/details/38822505
	  corePoolSize��
	 �̳߳صĻ�����С������û��������Ҫִ�е�ʱ���̳߳صĴ�С������ֻ���ڹ����������˵�����²Żᴴ����������������̡߳�������Ҫע����ǣ��ڸոմ���ThreadPoolExecutor��ʱ���̲߳�������������������Ҫ�ȵ��������ύʱ�Ż����������ǵ�����prestartCoreThread/prestartAllCoreThreads�������������̡߳��ٿ��ǵ�keepAliveTime��allowCoreThreadTimeOut��ʱ������Ӱ�죬����û��������Ҫִ�е�ʱ���̳߳صĴ�С��һ����corePoolSize��
	 maximumPoolSize��
	 �̳߳������������߳������̳߳��еĵ�ǰ�߳���Ŀ���ᳬ����ֵ������������������������ҵ�ǰ�̸߳���С��maximumPoolSize����ô�ᴴ���µ��߳���ִ����������ֵ��һ�����largestPoolSize���ñ�����¼���̳߳������������������������ֵ�����̸߳�����Ϊʲô˵�������أ���Ϊ�̳߳ش���֮�󣬿��Ե���setMaximumPoolSize()�ı����е�����̵߳���Ŀ��
	 poolSize��
	 �̳߳��е�ǰ�̵߳�����������ֵΪ0��ʱ����ζ��û���κ��̣߳��̳߳ػ���ֹ��ͬһʱ�̣�poolSize���ᳬ��maximumPoolSize��
	*/
	    public static void main(String[] args){  
	    	////�Զ����̳߳�
	        //�����ȴ�����   
	        BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);   
	        //�����̳߳أ����б�����߳���Ϊ3�����������߳���Ϊ5  
	        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,5,50,TimeUnit.MILLISECONDS,bqueue);   
	        //�����߸�����   
	        Runnable t1 = new MyThread();   
	        Runnable t2 = new MyThread();   
	        Runnable t3 = new MyThread();   
	        Runnable t4 = new MyThread();   
	        Runnable t5 = new MyThread();   
	        Runnable t6 = new MyThread();   
	        Runnable t7 = new MyThread();   
	        //ÿ���������һ���߳���ִ��  
	        pool.execute(t1);   
	        pool.execute(t2);   
	        pool.execute(t3);   
	        pool.execute(t4);   
	        pool.execute(t5);   
	        pool.execute(t6);   
	        pool.execute(t7);   
	        //�ر��̳߳�   
	        pool.shutdown();   
	    }   
	}   
	  
	class MyThread implements Runnable{   
	    @Override   
	    public void run(){   
	        System.out.println(Thread.currentThread().getName() + "����ִ�С�����");   
	        try{   
	            Thread.sleep(100);   
	        }catch(InterruptedException e){   
	            e.printStackTrace();   
	        }   
	    }   
	}  
