package com.ybliu.thread.produceComsune2;

import java.util.ArrayList;
import java.util.Random;

public class BreadShop {
	// װ���ӵ�����
	private ArrayList<Bread> breadList = new ArrayList<Bread>();
	// ���ӵ����ࣺ����Ͳ˰�
	private BreadType breadTypes[] = { BreadType.MEAT, BreadType.VEGETABLES };
	// �Ѿ���¯�İ�������
	private int totalCount = 0;
	// ��İ�����
	private final int MAX_COUNT = 20;
	 
	enum BreadType {
	    MEAT, VEGETABLES
	}
	
	public static void main(String[] args) {
	    BreadShop bs = new BreadShop();
	    // 5��coder�������ӵ�����
	    for(int i = 0; i < 5; ++ i)
	    {
	        Thread t = new Thread(bs.new Consumer(i));
	        t.start();
	    }
	    // �ϰ��￪ʼ������
	    Thread productThread = new Thread(bs.new Product());
	    productThread.setPriority(Thread.MAX_PRIORITY);
	    productThread.start();
	}
	
	 
	// ����
	class Bread {
	    public BreadType type;
	 
	    public Bread(BreadType type) {
	        this.type = type;
	    }
	}
	class Product implements Runnable {
		 private boolean isWork = false;
		 
		 public Product()
		 {
			 this.isWork = true;
		 }
		 
		 // �Ѱ��������ŵ�������
		 public void makeBread(Bread bread) {
			 breadList.add(bread);
			  switch(bread.type)
			  {
			  	case MEAT:
			  		System.out.println("make a meat bread");
			  		break;
			 
			  	case VEGETABLES:
			  		System.out.println("make a vegetables bread");
			  		break;
			  }
		 }
		 
		 @Override
		 public void run() {
			  while(isWork)
			  {
				   try {
					    synchronized(breadList)
					    {
						     // ���ǻ�û���꣬�����ȴ�
						     if(breadList.size() > 0)
						      breadList.wait();
						     // һ����10������
						     for(int i = 0; i < 10; ++ i)
						     {
						      int type = new Random().nextInt(2);
						      Bread bread = new Bread(breadTypes[type]);
						      this.makeBread(bread);
						     }
						     totalCount += 10;
						     // ֪ͨ���ǿ��Գ԰�����
						     breadList.notifyAll();
					    }
					    // ������20������
					    if(totalCount >= MAX_COUNT)
					    {
					     isWork = false;
					    }
				    }catch(Exception e) {
					    e.printStackTrace();
					    isWork = false;
				    }
			  }
		  }
		}
	class Consumer implements Runnable
	{
	 
	    private int id;
	    public Consumer(int id)
	    {
	        this.id = id;
	    }
	    // �԰���
	    public void eat(Bread bread)
	    {
	        BreadType type = bread.type;
	        switch(type)
	        {
	        case MEAT:
	            System.out.println("AlexZhou " + id + " eat a meat bread");
	            break;
	 
	        case VEGETABLES:
	            System.out.println("AlexZhou " + id + " eat a vegetables bread");
	            break;
	        }
	    }
	    @Override
	    public void run() {
	        while(true)
	        {
	            try{
	                synchronized(breadList)
	                {
	                    // ���ӻ�û����
	                    if(breadList.size() == 0)
	                    {
	                        // ���������а���
	                        if(totalCount >= MAX_COUNT)
	                            break;
	                        // ֪ͨ�ϰ���Ͽ�������
	                        breadList.notifyAll();
	                        // ���ϰ���������
	                        breadList.wait();
	                    }
	                    else
	                    {
	                        // ���������ð��ӳ�
	                        Bread bread = breadList.remove(0);
	                        this.eat(bread);
	 
	                    }
	                }
	                // ����ģ��԰��ӵ�ʱ�䣬Ҳ�������������̻߳�����ĸ��ʣ���߹�ƽ��
	                Thread.sleep(100);
	 
	            }catch(Exception e)
	            {
	                e.printStackTrace();
	                break;
	            }
	        }
	 
	    }
	}

}
