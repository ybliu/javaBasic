package com.ybliu.thread.produceComsune;

public class ThreadCaseDemo03 {
	 public static void main(String args[]){  
	        Info info = new Info(); // ʵ����Info����  
	        Producer pro = new Producer(info) ; // ������  
	        Consumer con = new Consumer(info) ; // ������  
	        new Thread(pro).start() ;  
	        //�������������̺߳��������������߳�  
	        try{  
	            Thread.sleep(500) ;  
	        }catch(InterruptedException e){  
	            e.printStackTrace() ;  
	        }  
	  
	        new Thread(con).start() ;  
	    }  
}
class Info{	// ������Ϣ��
	private String name = "name";//����name���ԣ�Ϊ��������set��name��������
	private String content = "content" ;// ����content���ԣ�Ϊ��������set��content��������
	private boolean flag = true ;	// ���ñ�־λ,��ʼʱ������
	public synchronized void set(String name,String content){
		while(!flag){
			try{
				super.wait() ;
			}catch(InterruptedException e){
				e.printStackTrace() ;
			}
		}
		this.setName(name) ;	// ��������
		try{
			Thread.sleep(300) ;
		}catch(InterruptedException e){
			e.printStackTrace() ;
		}
		this.setContent(content) ;	// ��������
		flag  = false ;	// �ı��־λ����ʾ����ȡ��
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
		flag  = true ;	// �ı��־λ����ʾ��������
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
class Producer implements Runnable{	// ͨ��Runnableʵ�ֶ��߳�
	private Info info = null ;		// ����Info����
	public Producer(Info info){
		this.info = info ;
	}
	public void run(){
		boolean flag = true ;	// ������λ
		for(int i=0;i<10;i++){
			if(flag){
				this.info.set("����--1","����--1") ;	// ��������
				flag = false ;
			}else{
				this.info.set("����--2","����--2") ;	// ��������
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
  ������⣺

����̵߳����˶����wait������������ô�̱߳�ᴦ�ڸö���ĵȴ����У��ȴ����е��̲߳���ȥ�����ö��������

�����̵߳����˶����notifyAll������������������wait�̣߳���notify����������ֻ�������һ��wait�̣߳��������ѵĵ��̱߳�����ö���������У������е��̻߳�ȥ�����ö�������

���ȼ��ߵ��߳̾������������ĸ��ʴ󣬼���ĳ�߳�û�о������ö����������������������У�Ψ���߳��ٴε���wait�������������Ż����»ص��ȴ����С������������������߳����������ִ�У�ֱ��ִ������synchronized����飬�����ͷŵ��ö���������ʱ�����е��̻߳���������ö�������*/