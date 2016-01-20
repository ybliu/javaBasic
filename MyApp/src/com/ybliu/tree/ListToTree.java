package com.ybliu.tree;

//http://www.iteye.com/topic/1119961
//http://blog.csdn.net/lpy3654321/article/details/38928701
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Properties;  
import java.util.Set;  
  
import org.apache.commons.dbutils.DbUtils;  
import org.apache.commons.dbutils.QueryRunner;  
import org.apache.commons.dbutils.handlers.BeanListHandler;  
import org.apache.commons.dbutils.handlers.ColumnListHandler;  
  
/** 
 * ���ݿ������ṹ����,ת��ΪJava�������ṹ( ������ṹ ) 
 * @author ybliu 
 * 
 */  
public class ListToTree {  
      
    /** 
     * No.0: 
     * @param args 
     * @throws Exception 
     */  
    public static void main(String[] args) throws Exception {  
        long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��  
          
          
        // �����ݿ��в�ѯ���з���Ľڵ�  
//      Map<String, List<Tree>> arrMap = queryGroupToMap(); //No.1  (���Ƽ�ʹ�� ����ʱ��ϳ�)  
        Map<String, List<Tree>> arrMap = queryListToMap();  //No.1.1  (�Ƽ�ʹ�� ����ʱ��϶�)  
  
        System.out.println(arrMap.size());
        //  No.2:�ýڵ����ӽڵ�֮��˴˹���,������ȫ�еĸ�.(û�и��ڵ�Ķ�Ϊ��)  
        List<Tree> rootTreeList = MapForTree(arrMap);  
  
        //  ��map��Ѹ��ҵ�.����List . �����ж����  
        List<Tree> list = arrMap.get("0");  
        System.out.println(list.size());  
          
        for(int i=0;i<rootTreeList.size();i++)
        {
        System.out.println(rootTreeList.get(i).getAREA_NAME());  
        }

        //��ȡ����ʱ��  
        long endTime=System.currentTimeMillis();  
        System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ms");  
        System.out.println(arrMap.size());
    }  
      
      
    /**  
     * No.1:  
     * ͨ������sql��ѯ���,���ݿ�ѹ����( ���Ƽ�ʹ�� ). 
     * �ø�ID����,��Map��װ. keyΪ��ID, value�����и�IDΪKEY�Ľڵ�����. 
     * ÿ�������ﶼ��һ���ӽڵ�,���ǵĸ���ͬһ��. ���仰˵���ǵĸ�ID��ͬ, ��Map��Key���������Ǹ�ID. 
     * @return 
     * @throws SQLException 
     */  
    @SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })  
    public static Map<String,List<Tree>> queryGroupToMap() throws SQLException{  
          
        /* 
         * �ñ�Ϊ�й�������֯,�� ���ؼ� 
         * ����,�й��·�:������,�ӱ�ʡ,ɽ��ʡ... 
         * ɽ���·�:������,�ൺ��,��̨��... 
         *  
         */  
          
        // QueryRunner ��������ݿ⹤��,ֻҪ�����������ؾͿ��� commons-dbutils-1.5.jar  
        QueryRunner qr = new QueryRunner();  
        Connection connection = getJdbcConnection("jdbc:oracle:thin:@192.168.3.34:1521:ORCL", "DSD_ZJK", "DSD_ZJK", "oracle.jdbc.driver.OracleDriver");  
        /* 
         * �ø�Id�����ѯ,�ҵ����еĸ�ID 
         * Ȼ��ѭ�����List��ѯ 
         */  
        String sqlGroup = "select parent_id from  HM_F_AREA t group by t.parent_id";  
        List<String> sqlGroupList = (List<String>)qr.query(connection, sqlGroup, new String[]{}, new ColumnListHandler("PARENT_ID"));  
  
        Map<String,List<Tree>> arrMap = new HashMap<String,List<Tree>>(sqlGroupList.size());  
        for(int i=0; i <sqlGroupList.size(); i++){  
            String _pid = sqlGroupList.get(i);  
            String sql = "select area_id , area_name , parent_id from  HM_F_AREA t where t.parent_id = '"+_pid + "'";  
            List<Tree> listTree = (List<Tree>) qr.query(connection, sql, new String[]{} , new BeanListHandler(Tree.class));  
            arrMap.put( _pid , listTree );  
        }  
        DbUtils.close(connection);  
        return arrMap;  
    }  
      
      
    /**  
     * No.1.1:  
     * ͨ������sql��ѯ���.���ݿ�ѹ����СЩ(�Ƽ�ʹ���������,���е�ʱ��� queryGroupToMap ��һ��). 
     * �ø�ID����,��Map��װ. keyΪ��ID, value�����и�IDΪKEY�Ľڵ�����. 
     * ÿ�������ﶼ��һ���ӽڵ�,���ǵĸ���ͬһ��. ���仰˵���ǵĸ�ID��ͬ, ��Map��Key���������Ǹ�ID. 
     * @return 
     * @throws SQLException 
     */  
    @SuppressWarnings({ "unchecked", "deprecation", "rawtypes"})  
    public static Map<String,List<Tree>> queryListToMap() throws SQLException{  
          
        /* 
         * �ñ�Ϊ�й�������֯,�� ���ؼ� 
         * ����,�й��·�:������,�ӱ�ʡ,ɽ��ʡ... 
         * ɽ���·�:������,�ൺ��,��̨��... 
         *  
         */  
          
        // QueryRunner ��������ݿ⹤��,ֻҪ�����������ؾͿ��� commons-dbutils-1.5.jar  
        QueryRunner qr = new QueryRunner();  
        Connection connection = getJdbcConnection("jdbc:mysql://localhost:3306/test", "root", "root", "com.mysql.jdbc.Driver");  
          
        //�ø�Id�����ѯ,�ҵ����еĸ�IDȻ��ѭ�����List��ѯ  
        String sqlGroup = "select parent_id from  as_areas t group by t.parent_id   ";  
        List<String> sqlGroupList = (List<String>)qr.query(connection, sqlGroup, new String[]{}, new ColumnListHandler("PARENT_ID"));  
  
        //��ѯ�����еĽڵ�  
        Map<String,List<Tree>> arrMap = new HashMap<String,List<Tree>>(sqlGroupList.size());  
        String sql = "select id , area_name , parent_id from  as_areas t ";  
        List<Tree> listTree = (List<Tree>) qr.query(connection, sql, new String[]{} , new BeanListHandler(Tree.class));  
        DbUtils.close(connection);  
          
          
        /* 
         * ͨ�� ��ID �� ���еĽڵ� �ȶ� 
         */  
        for(int k=0;k<sqlGroupList.size();k++){  
            String pid = sqlGroupList.get(k);  
              
            List<Tree> tempTreeList = new ArrayList<Tree>();  
            for(int i=0; i < listTree.size();i++){  
                Tree tree = listTree.get(i);  
                  
                /* 
                 * ��ͬһ��ID��tree��ӵ�ͬһ��List��,���List����Map..   arrMap.put(pid, tempTreeList); 
                 * �����Ȼ������,����������˼��������, 
                 */  
                if(pid.equals(tree.getPARENT_ID())){  
                    tempTreeList.add(tree);  
                }  
            }  
              
            // ���List����Map..  key��������List<Tree>��ID, value��������List  
            arrMap.put(pid, tempTreeList);  
        }  
          
        return arrMap;  
    }  
      
      
    /** 
     * No.2: 
     * �ýڵ����ӽڵ�֮��˴˹���,���������ĸ� 
     * ���ݿ��ʽ��û�л�,ֻ�ǽ����˹��� 
     * @param arrMap 
     */  
    public static List<Tree> MapForTree(Map<String, List<Tree>> arrMap){  
          
        //����pid�ļ���  
        Set<String> pidSet = arrMap.keySet();  
          
        //�������еĸ�ID,Ȼ�������еĽڵ�ȶ�,��id��id��ͬ��    //�ҳ���Ӧ��tree�ڵ�,Ȼ�󽫸ýڵ��childrenList ����pid���ڵ�child list
        for (Iterator<String> it = pidSet.iterator(); it.hasNext();) {  
              
            String pid = (String) it.next();  
              
            /* 
             * ������ķ�ʽ��pid�ȶ�. 
             * ����ҵ�,��ô����pid�����List,��Ϊ�ӽڵ� ��ֵ�����ҵ��Ľڵ�� setChildrenList(list),ͬʱҲ���ҵ��ڵ㸳ֵList�������ӽڵ��parentObj 
             *  
             */  
            for (Iterator<String> it2 = pidSet.iterator(); it2.hasNext();) {  
                  
                String key = (String) it2.next();  
                //�������Լ��ķ���  
                if(pid.equals(key)){  
                //  break;  
                	continue;//ybliu
                }  
                  
                List<Tree> list = arrMap.get(key);  
                  
                //  No.3:�ҳ���Ӧpid��tree���ڵ����  
                Tree parentTree = indexOfList(list , pid);  
                  
                  
                if(parentTree!=null){  
                    //ͨ��pid��Map���ҳ��ڵ���ӽڵ�.  
                   /* if("338".equals(pid)){  
                        System.out.println(pid);  
                    }  */
                    List<Tree> childrenHereList = arrMap.get(pid);  
                      
                    //TODO  ���������Լ�����ı�ɳ�,����һ��.������Ҫ�Զ���  
                    // ���ӽڵ�List��ֵ��Tree�ڵ��Children  
                    parentTree.setChildrenList(childrenHereList);  
                      
                    //TODO  ���������Լ�����ı���,����һ��.������Ҫ�Զ���  
                    // �������෴,���� �Ѹ��ڵ����ֵ��Tree�ڵ��parentObj  
                    for(int i=0; i<childrenHereList.size(); i++){  
                        Tree childrenHereTree = childrenHereList.get(i);  
                        childrenHereTree.setParentObj(parentTree);  
                    }  
                }  
            }  
        }  
          
          
          
        // �ҵ� childrenHereTree.getParentObj(); Ϊnull�ľ��Ǹ�  return rootTreeList  
        List<Tree> rootTreeList = new ArrayList<Tree>();  
        for (Iterator<String> it2 = pidSet.iterator(); it2.hasNext();) {  
            String key = (String) it2.next();  
            List<Tree> list = arrMap.get(key);  
            for(int i=0; i<list.size(); i++){  
                Tree tree = list.get(i);  
                if(null == tree.getParentObj()){  
                    rootTreeList.add(tree);  
                }  
            }  
        }  
        return rootTreeList;  
          
    }  
      
      
      
    /** 
     * No.3: 
     * �ҳ� list ��Ԫ�ص�id��pid��ͬ�Ľڵ� �Ĳ�����.��Ӧ��ϵΪ: id�븸id��ͬ 
     * @param list 
     * @param pid 
     * @return 
     */  
    public static Tree  indexOfList(List<Tree> list , String pid){  
        for(int i=0 ;i<list.size();i++){  
            Tree tree = list.get(i);  
            /* 
             * pid:�� ��ID 
             * area_id:�� ID 
             */  
            //TODO  ���������Լ�����ı�ɳ�,����һ��.������Ҫ�Զ���  
            if(pid.equals(tree.getID())){  
                return tree;  
            }  
        }  
        return null;  
    }  
      
    /** 
     * ���ݿ����� 
     * @param url 
     * @param username 
     * @param password 
     * @param driverClassName 
     * @return 
     */  
    public static Connection getJdbcConnection(String url, String username, String password, String driverClassName){  
        Connection connection = null;  
        try {  
              
            Properties props =new Properties();  
               
            props.put("user",username);  
            props.put("password",password);  
            props.put("remarksReporting","true");  
  
            try {  
                Class.forName(driverClassName).newInstance();  
            } catch (InstantiationException e) {  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                e.printStackTrace();  
            } catch (ClassNotFoundException e) {  
                e.printStackTrace();  
            }  
            connection=DriverManager.getConnection(url,props);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
          
        return connection;  
          
    }  
      
}  