package com.ybliu.session;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import com.mysql.jdbc.PreparedStatement;
import com.ybliu.entity.User;

public class Session {
	String tableName = "user";
	
	Map<String ,String > map = new HashMap<String ,String >();
	String methodNames[];
	public Session ()
	{
		//���������ݿ��еı�����ʵ����Ķ�Ӧ��ϵ,�൱����hibernate�������ļ�
		//key��Ӧ���ݿ��еı�������С�value ������ʵ�����е������࣬ʹ��map�����������ݿ��������н���һһ��Ӧ��
		
		map.put("id", "id");
		map.put("username", "username");
		map.put("pwd", "pwd");
		methodNames = new String[map.size()];
	}
	public void save(User user) {
		// TODO Auto-generated method stub 
		
		String sql = createSql();
		System.out.println(sql);
		
		try {
			//�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wuser","root","962464");
			PreparedStatement state =  (PreparedStatement) con.prepareStatement(sql);
			
			for(int i=0;i<methodNames.length;i++)
			{
				//�õ�ÿһ�������Ķ���
				Method method = user.getClass().getMethod(methodNames[i]);
				//�õ����ķ�������
				Class cla = method.getReturnType();
				//���ݷ������������ò������ݿ��е�ÿ������ֵ��
				if(cla.getName().equals("java.lang.String"))
				{
					String returnValue = (String)method.invoke(user);
					state.setString(i+1, returnValue);
				}
				else if(cla.getName().equals("int"))
				{
					Integer returnValue = (Integer) method.invoke(user);
					state.setInt(i+1, returnValue);
				}
				System.out.println(method.getName() + "--" + method.getReturnType());
				
			}
			state.executeUpdate();
			state.close();
			con.close();
			
		} catch (ClassNotFoundException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/**
	 * �õ�sql���
	 * @return ����sql���
	 */
	private String createSql() {
		// TODO Auto-generated method stub
		//str1�������ݿ��б��е������С�����������������
		String str1 = "";
		int index=0;
		for(String key :map.keySet())
		{
			str1 +=key+",";
			String v = map.get(key);
			//�����ǵõ���Ӧ��bean�е�get������Ҳ���ǵõ�User���е�getId()��getUsername()��getPwd()������
			//ע��ÿ�����Ե�get����ĵ�һ����ĸ�Ǵ�д����Ҫת����
			v = "get" + Character.toUpperCase(v.charAt(0)) + v.substring(1);
			methodNames[index] = v; //�����������������С�
			index++;
		}
		str1  = str1.substring(0, str1.length()-1);
		System.out.println(str1);
		
		//�õ��������ݵı��ʽ��Ҳ���ǣ���������
		String str2 = "";
		for(int i=0;i<map.size();i++)
			str2 +="?,";
		str2 = str2.substring(0,str2.length()-1);
		System.out.println(str2);
		String sql = "insert into " + tableName +"(" + str1 + ")" + " values (" + str2 + ")"; 
		return sql;
	}

}

