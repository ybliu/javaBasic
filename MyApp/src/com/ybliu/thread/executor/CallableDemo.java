package com.ybliu.thread.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
	 public static void main(String[] args){
		 
		 ExecutorService executorService =Executors.newCachedThreadPool();
		 List<Future<String>> resultList=new ArrayList<Future<String>>();
		 
		 for(int i=0;i<100;i++)
		 {
			 Future<String> future= executorService.submit(new TaskWithResult(i));
			 resultList.add(future);
		 }
		 
		//��������Ľ��   
	        for (Future<String> fs : resultList){   
	                try{   
	                    while(!fs.isDone());//Future�������û����ɣ���һֱѭ���ȴ���ֱ��Future�������  
	                    System.out.println(fs.get());     //��ӡ�����̣߳�����ִ�еĽ��   
	                }catch(InterruptedException e){   
	                    e.printStackTrace();   
	                }catch(ExecutionException e){   
	                    e.printStackTrace();   
	                }finally{   
	                    //����һ��˳��رգ�ִ����ǰ�ύ�����񣬵�������������  
	                    executorService.shutdown();   
	                }   
	        }   

	 }
	 
	

}
class TaskWithResult  implements Callable<String>
{
	private int id;   
	  
	public TaskWithResult(int id){   
	        this.id = id;   
	    }  

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("call()�������Զ����ã�����    " + Thread.currentThread().getName());   
       //�÷��ؽ������Future��get�����õ�  
       return "call()�������Զ����ã����񷵻صĽ���ǣ�" + id + "    " + Thread.currentThread().getName();  
	}
	 
	 
}
