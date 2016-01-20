package com.ybliu.main;

import java.lang.reflect.Field;
import java.util.Map;

import com.ybliu.annotation.Column;
import com.ybliu.annotation.Table;
import com.ybliu.criteria.Filter;
import com.ybliu.criteria.KV;
import com.ybliu.entity.Apple;
/**注解模拟hibernate生成sql查询语句**/
public class hibernateSql {

    public static void main(String[] args) throws NoSuchFieldException, SecurityException {
    	
		        Filter f = new Filter();
		        f.setValue("name", "苹果", null);
		        f.setValue("price", "5", Filter.GT);
		        String sql = query(f, Apple.class); //这里查询name=苹果并且价格大于 5 的商品信息的语句
		        System.out.println(sql); // 打印生成的sql语句
    }

    /**
     * 生成sql语句
     * 
     * @param f
     *            条件对象
     * @param clzz
     *            实体表的类型
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
     public static String query(Filter f, Class<?> clzz) throws NoSuchFieldException, SecurityException {
        // 1:获取实体上的注解信息,先生成基本的查询语句
    	 if (!clzz.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("请传入一个实体类");
        }
        Table table = clzz.getAnnotation(Table.class);
        String tableName = table.value();
        boolean flag = false; // 追加sql的时候，标记sql语句后面是否已经有sql了
        StringBuffer sb = new StringBuffer();
        sb.append("select * from ").append(tableName);

        // 2:获取条件所有的条件
        Map<KV, String> map = f.getMap();
        for (Map.Entry<KV, String> ent : map.entrySet()) {
            KV kv = ent.getKey();
            String type = ent.getValue();

            Field field = clzz.getDeclaredField(kv.getK()); // 获取条件属性对应的属性对象
            if (!field.isAnnotationPresent(Column.class)) { // 如果此属性不包含column注解标识，抛出异常
            	throw new RuntimeException("传入的参数有误");
            }

            Column column = field.getAnnotation(Column.class);
            if (flag) {
                sb.append(" and ").append(parse(column.value(), kv.getV(), type));
            } else {
                flag = true;
                sb.append(" where ").append(parse(column.value(), kv.getV(), type));
            }

        }

        return sb.toString();
    }

    /**
     * 解析条件sql
     * 
     * @param k
     *            数据库表中的字段名称
     * @param v
     *            值
     * @param type
     *            条件类型
     * @return
     */
     public static String parse(String k, String v, String type) {
        String sql = "";
        if (type != null) {
            if (type.equals(Filter.GT)) {
                sql = k + " > " + v;
            }
        } else {
            sql = k + " = " + v;
        }
        return sql;
    }

}