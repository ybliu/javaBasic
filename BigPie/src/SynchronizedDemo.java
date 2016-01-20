

public class  SynchronizedDemo {
	//�������
    private boolean ready = false;
    private int result = 0;
    private int number = 1;   
    //д����
    public   void  write(){
    	ready = true;	      				 //1.1				
    	number = 2;		                    //1.2			    
    }
    //������
    public   void read(){			   	 
    	if(ready){						     //2.1
    		result = number*3;	 	//2.2
    	}   	
    	System.out.println("result��ֵΪ��" + result);
    }

    //�ڲ��߳���
    private class ReadWriteThread extends Thread {
    	//���ݹ��췽���д����flag������ȷ���߳�ִ�ж���������д����
    	private boolean flag;
    	public ReadWriteThread(boolean flag){
    		this.flag = flag;
    	}
        @Override                                                                    
        public void run() {
        	if(flag){
        		//���췽���д���true��ִ��д����
        		write();
        	}else{
        		//���췽���д���false��ִ�ж�����
        		read();
        	}
        }
    }

    public static void main(String[] args)  {
    	SynchronizedDemo synDemo = new SynchronizedDemo();
    	//�����߳�ִ�ж�����
    	synDemo.new ReadWriteThread(false).start();
    	//�����߳�ִ��д����
    	synDemo .new ReadWriteThread(true).start();
    
    	
    }
}

