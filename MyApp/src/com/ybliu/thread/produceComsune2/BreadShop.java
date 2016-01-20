package com.ybliu.thread.produceComsune2;

import java.util.ArrayList;
import java.util.Random;

public class BreadShop {
	// 装包子的盘子
	private ArrayList<Bread> breadList = new ArrayList<Bread>();
	// 包子的种类：肉包和菜包
	private BreadType breadTypes[] = { BreadType.MEAT, BreadType.VEGETABLES };
	// 已经出炉的包子总数
	private int totalCount = 0;
	// 点的包子数
	private final int MAX_COUNT = 20;
	 
	enum BreadType {
	    MEAT, VEGETABLES
	}
	
	public static void main(String[] args) {
	    BreadShop bs = new BreadShop();
	    // 5个coder来到包子店点包子
	    for(int i = 0; i < 5; ++ i)
	    {
	        Thread t = new Thread(bs.new Consumer(i));
	        t.start();
	    }
	    // 老板娘开始做包子
	    Thread productThread = new Thread(bs.new Product());
	    productThread.setPriority(Thread.MAX_PRIORITY);
	    productThread.start();
	}
	
	 
	// 包子
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
		 
		 // 把包子蒸熟后放到盘子里
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
						     // 他们还没吃完，继续等待
						     if(breadList.size() > 0)
						      breadList.wait();
						     // 一次蒸10个包子
						     for(int i = 0; i < 10; ++ i)
						     {
						      int type = new Random().nextInt(2);
						      Bread bread = new Bread(breadTypes[type]);
						      this.makeBread(bread);
						     }
						     totalCount += 10;
						     // 通知他们可以吃包子了
						     breadList.notifyAll();
					    }
					    // 做完了20个包子
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
	    // 吃包子
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
	                    // 包子还没做好
	                    if(breadList.size() == 0)
	                    {
	                        // 吃完了所有包子
	                        if(totalCount >= MAX_COUNT)
	                            break;
	                        // 通知老板娘赶快做包子
	                        breadList.notifyAll();
	                        // 等老板娘做包子
	                        breadList.wait();
	                    }
	                    else
	                    {
	                        // 从盘子里拿包子吃
	                        Bread bread = breadList.remove(0);
	                        this.eat(bread);
	 
	                    }
	                }
	                // 这里模拟吃包子的时间，也可以增大其他线程获得锁的概率，提高公平性
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
