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
		//定义在数据库中的表名与实体类的对应关系,相当于是hibernate的配置文件
		//key对应数据库中的表的属性列。value 就是在实体类中的属性类，使用map将他们与数据库中属性列进行一一对应。
		
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
			//加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wuser","root","962464");
			PreparedStatement state =  (PreparedStatement) con.prepareStatement(sql);
			
			for(int i=0;i<methodNames.length;i++)
			{
				//得到每一个方法的对象
				Method method = user.getClass().getMethod(methodNames[i]);
				//得到他的返回类型
				Class cla = method.getReturnType();
				//根据返回类型来设置插入数据库中的每个属性值。
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
	 * 得到sql语句
	 * @return 返回sql语句
	 */
	private String createSql() {
		// TODO Auto-generated method stub
		//str1代表数据库中表中的属性列。并将其连接起来。
		String str1 = "";
		int index=0;
		for(String key :map.keySet())
		{
			str1 +=key+",";
			String v = map.get(key);
			//下面是得到对应的bean中的get方法。也就是得到User类中的getId()、getUsername()、getPwd()方法。
			//注意每个属性的get后面的第一个字母是大写，需要转换。
			v = "get" + Character.toUpperCase(v.charAt(0)) + v.substring(1);
			methodNames[index] = v; //将方法保存在数组中。
			index++;
		}
		str1  = str1.substring(0, str1.length()-1);
		System.out.println(str1);
		
		//得到插入数据的表达式，也就是？，？，？
		String str2 = "";
		for(int i=0;i<map.size();i++)
			str2 +="?,";
		str2 = str2.substring(0,str2.length()-1);
		System.out.println(str2);
		String sql = "insert into " + tableName +"(" + str1 + ")" + " values (" + str2 + ")"; 
		return sql;
	}

}

