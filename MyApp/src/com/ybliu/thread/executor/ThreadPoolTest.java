package com.ybliu.thread.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

 public class ThreadPoolTest{ 
	/*
	 http://blog.csdn.net/aitangyong/article/details/38822505
	  corePoolSize：
	 线程池的基本大小，即在没有任务需要执行的时候线程池的大小，并且只有在工作队列满了的情况下才会创建超出这个数量的线程。这里需要注意的是：在刚刚创建ThreadPoolExecutor的时候，线程并不会立即启动，而是要等到有任务提交时才会启动，除非调用了prestartCoreThread/prestartAllCoreThreads事先启动核心线程。再考虑到keepAliveTime和allowCoreThreadTimeOut超时参数的影响，所以没有任务需要执行的时候，线程池的大小不一定是corePoolSize。
	 maximumPoolSize：
	 线程池中允许的最大线程数，线程池中的当前线程数目不会超过该值。如果队列中任务已满，并且当前线程个数小于maximumPoolSize，那么会创建新的线程来执行任务。这里值得一提的是largestPoolSize，该变量记录了线程池在整个生命周期中曾经出现的最大线程个数。为什么说是曾经呢？因为线程池创建之后，可以调用setMaximumPoolSize()改变运行的最大线程的数目。
	 poolSize：
	 线程池中当前线程的数量，当该值为0的时候，意味着没有任何线程，线程池会终止；同一时刻，poolSize不会超过maximumPoolSize。
	*/
	    public static void main(String[] args){  
	    	////自定义线程池
	        //创建等待队列   
	        BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);   
	        //创建线程池，池中保存的线程数为3，允许的最大线程数为5  
	        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,5,50,TimeUnit.MILLISECONDS,bqueue);   
	        //创建七个任务   
	        Runnable t1 = new MyThread();   
	        Runnable t2 = new MyThread();   
	        Runnable t3 = new MyThread();   
	        Runnable t4 = new MyThread();   
	        Runnable t5 = new MyThread();   
	        Runnable t6 = new MyThread();   
	        Runnable t7 = new MyThread();   
	        //每个任务会在一个线程上执行  
	        pool.execute(t1);   
	        pool.execute(t2);   
	        pool.execute(t3);   
	        pool.execute(t4);   
	        pool.execute(t5);   
	        pool.execute(t6);   
	        pool.execute(t7);   
	        //关闭线程池   
	        pool.shutdown();   
	    }   
	}   
	  
	class MyThread implements Runnable{   
	    @Override   
	    public void run(){   
	        System.out.println(Thread.currentThread().getName() + "正在执行。。。");   
	        try{   
	            Thread.sleep(100);   
	        }catch(InterruptedException e){   
	            e.printStackTrace();   
	        }   
	    }   
	}  
