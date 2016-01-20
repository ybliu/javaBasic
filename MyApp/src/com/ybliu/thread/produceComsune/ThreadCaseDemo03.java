package com.ybliu.thread.produceComsune;

public class ThreadCaseDemo03 {
	 public static void main(String args[]){  
	        Info info = new Info(); // 实例化Info对象  
	        Producer pro = new Producer(info) ; // 生产者  
	        Consumer con = new Consumer(info) ; // 消费者  
	        new Thread(pro).start() ;  
	        //启动了生产者线程后，再启动消费者线程  
	        try{  
	            Thread.sleep(500) ;  
	        }catch(InterruptedException e){  
	            e.printStackTrace() ;  
	        }  
	  
	        new Thread(con).start() ;  
	    }  
}
class Info{	// 定义信息类
	private String name = "name";//定义name属性，为了与下面set的name属性区别开
	private String content = "content" ;// 定义content属性，为了与下面set的content属性区别开
	private boolean flag = true ;	// 设置标志位,初始时先生产
	public synchronized void set(String name,String content){
		while(!flag){
			try{
				super.wait() ;
			}catch(InterruptedException e){
				e.printStackTrace() ;
			}
		}
		this.setName(name) ;	// 设置名称
		try{
			Thread.sleep(300) ;
		}catch(InterruptedException e){
			e.printStackTrace() ;
		}
		this.setContent(content) ;	// 设置内容
		flag  = false ;	// 改变标志位，表示可以取走
		super.notify();
	}
	public synchronized void get(){
		while(flag){
			try{
				super.wait() ;
			}catch(InterruptedException e){
				e.printStackTrace() ;
			}
		}
		try{
			Thread.sleep(300) ;
		}catch(InterruptedException e){
			e.printStackTrace() ;
		}
		System.out.println(this.getName() + 
			" --> " + this.getContent()) ;
		flag  = true ;	// 改变标志位，表示可以生产
		super.notify();
	}
	public void setName(String name){
		this.name = name ;
	}
	public void setContent(String content){
		this.content = content ;
	}
	public String getName(){
		return this.name ;
	}
	public String getContent(){
		return this.content ;
	}
}
class Producer implements Runnable{	// 通过Runnable实现多线程
	private Info info = null ;		// 保存Info引用
	public Producer(Info info){
		this.info = info ;
	}
	public void run(){
		boolean flag = true ;	// 定义标记位
		for(int i=0;i<10;i++){
			if(flag){
				this.info.set("姓名--1","内容--1") ;	// 设置名称
				flag = false ;
			}else{
				this.info.set("姓名--2","内容--2") ;	// 设置名称
				flag = true ;
			}
		}
	}
}
class Consumer implements Runnable{
	private Info info = null ;
	public Consumer(Info info){
		this.info = info ;
	}
	public void run(){
		for(int i=0;i<10;i++){
			this.info.get() ;
		}
	}
}
 

/* http://blog.csdn.net/ns_code/article/details/17225469
  深入理解：

如果线程调用了对象的wait（）方法，那么线程便会处于该对象的等待池中，等待池中的线程不会去竞争该对象的锁。

当有线程调用了对象的notifyAll（）方法（唤醒所有wait线程）或notify（）方法（只随机唤醒一个wait线程），被唤醒的的线程便会进入该对象的锁池中，锁池中的线程会去竞争该对象锁。

优先级高的线程竞争到对象锁的概率大，假若某线程没有竞争到该对象锁，它还会留在锁池中，唯有线程再次调用wait（）方法，它才会重新回到等待池中。而竞争到对象锁的线程则继续往下执行，直到执行完了synchronized代码块，它会释放掉该对象锁，这时锁池中的线程会继续竞争该对象锁。*/