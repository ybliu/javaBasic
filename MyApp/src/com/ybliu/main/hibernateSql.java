package com.ybliu.main;

import java.lang.reflect.Field;
import java.util.Map;

import com.ybliu.annotation.Column;
import com.ybliu.annotation.Table;
import com.ybliu.criteria.Filter;
import com.ybliu.criteria.KV;
import com.ybliu.entity.Apple;
/**ע��ģ��hibernate����sql��ѯ���**/
public class hibernateSql {

    public static void main(String[] args) throws NoSuchFieldException, SecurityException {
    	
		        Filter f = new Filter();
		        f.setValue("name", "ƻ��", null);
		        f.setValue("price", "5", Filter.GT);
		        String sql = query(f, Apple.class); //�����ѯname=ƻ�����Ҽ۸���� 5 ����Ʒ��Ϣ�����
		        System.out.println(sql); // ��ӡ���ɵ�sql���
    }

    /**
     * ����sql���
     * 
     * @param f
     *            ��������
     * @param clzz
     *            ʵ��������
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
     public static String query(Filter f, Class<?> clzz) throws NoSuchFieldException, SecurityException {
        // 1:��ȡʵ���ϵ�ע����Ϣ,�����ɻ����Ĳ�ѯ���
    	 if (!clzz.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("�봫��һ��ʵ����");
        }
        Table table = clzz.getAnnotation(Table.class);
        String tableName = table.value();
        boolean flag = false; // ׷��sql��ʱ�򣬱��sql�������Ƿ��Ѿ���sql��
        StringBuffer sb = new StringBuffer();
        sb.append("select * from ").append(tableName);

        // 2:��ȡ�������е�����
        Map<KV, String> map = f.getMap();
        for (Map.Entry<KV, String> ent : map.entrySet()) {
            KV kv = ent.getKey();
            String type = ent.getValue();

            Field field = clzz.getDeclaredField(kv.getK()); // ��ȡ�������Զ�Ӧ�����Զ���
            if (!field.isAnnotationPresent(Column.class)) { // ��������Բ�����columnע���ʶ���׳��쳣
            	throw new RuntimeException("����Ĳ�������");
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
     * ��������sql
     * 
     * @param k
     *            ���ݿ���е��ֶ�����
     * @param v
     *            ֵ
     * @param type
     *            ��������
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