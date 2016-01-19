package com.ybliu.tree;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

/**
 * �������
 * http://www.iteye.com/topic/1119961
*/
public class MultipleTree {
 public static void main(String[] args) {
  // ��ȡ������ݽ�����б� 
  List dataList = VirtualDataGenerator.getVirtualResult();  
  
  // �ڵ��б�ɢ�б�������ʱ�洢�ڵ����
  HashMap nodeList = new HashMap();
  // ���ڵ�
  Node root = null;
  // ���ݽ��������ڵ��б�����ɢ�б�
  for (Iterator it = dataList.iterator(); it.hasNext();) {
   Map dataRecord = (Map) it.next();
   Node node = new Node();
   node.id = (String) dataRecord.get("id");
   node.text = (String) dataRecord.get("text");
   node.parentId = (String) dataRecord.get("parentId");
   nodeList.put(node.id, node);
  }
  // ��������Ķ����
  Set entrySet = nodeList.entrySet();
  for (Iterator it = entrySet.iterator(); it.hasNext();) {
   Node node = (Node) ((Map.Entry) it.next()).getValue();
   if (node.parentId == null || node.parentId.equals("")) {
    root = node;
   } else {
    ((Node) nodeList.get(node.parentId)).addChild(node);
   }
  }
  // �����������β˵���JSON�ַ���
  System.out.println(root.toString());    
  // �Զ�������к�������
  root.sortChildren();
  // �����������β˵���JSON�ַ���
  System.out.println(root.toString()); 
  
  // �������������£���������β˵�������ʽ����Ľ������  
  //  {
  //   id : '100000', 
  //   text : '�ȷ���������', 
  //   children : [
  //     {
  //     id : '110000', 
  //     text : '�ȷ�����', 
  //     children : [
  //       {
  //       id : '113000', 
  //       text : '�ȷ����п�����֧��', 
  //       leaf : true
  //       },
  //       {
  //       id : '111000', 
  //       text : '�ȷ����н���֧��', 
  //       leaf : true
  //       },
  //       {
  //       id : '112000', 
  //       text : '�ȷ����н�ŵ�֧��', 
  //       children : [
  //         {
  //         id : '112200', 
  //         text : '�ȷ����������֧��', 
  //         leaf : true
  //         },
  //         {
  //         id : '112100', 
  //         text : '�ȷ����й�����֧��', 
  //         leaf : true
  //         }
  //       ]
  //       }
  //     ]
  //     }
  //   ]
  //  }

  // �������������£���������β˵�������ʽ����Ľ������
  //  {
  //   id : '100000', 
  //   text : '�ȷ���������', 
  //   children : [
  //     {
  //     id : '110000', 
  //     text : '�ȷ�����', 
  //     children : [
  //       {
  //       id : '111000', 
  //       text : '�ȷ����н���֧��', 
  //       leaf : true
  //       },
  //       {
  //       id : '112000', 
  //       text : '�ȷ����н�ŵ�֧��', 
  //       children : [
  //         {
  //         id : '112100', 
  //         text : '�ȷ����й�����֧��', 
  //         leaf : true
  //         },
  //         {
  //         id : '112200', 
  //         text : '�ȷ����������֧��', 
  //         leaf : true
  //         }
  //       ]
  //       },
  //       {
  //       id : '113000', 
  //       text : '�ȷ����п�����֧��', 
  //       leaf : true
  //       }
  //     ]
  //     }
  //   ]
  //  }  
  
 }
   
}


/**
* �ڵ���
*/
class Node {
 /**
  * �ڵ���
  */
 public String id;
 /**
  * �ڵ�����
  */
 public String text;
 /**
  * ���ڵ���
  */
 public String parentId;
 /**
  * ���ӽڵ��б�
  */
 private Children children = new Children();
 
 // ���������ƴ��JSON�ַ���
 public String toString() {  
  String result = "{"
   + "id : '" + id + "'"
   + ", text : '" + text + "'";
  
  if (children != null && children.getSize() != 0) {
   result += ", children : " + children.toString();
  } else {
   result += ", leaf : true";
  }
    
  return result + "}";
 }
 
 // �ֵܽڵ��������
 public void sortChildren() {
  if (children != null && children.getSize() != 0) {
   children.sortChildren();
  }
 }
 
 // ��Ӻ��ӽڵ�
 public void addChild(Node node) {
  this.children.addChild(node);
 }
}

/**
* �����б���
*/
class Children {
 private List list = new ArrayList();
 
 public int getSize() {
  return list.size();
 }
 
 public void addChild(Node node) {
  list.add(node);
 }
 
 // ƴ�Ӻ��ӽڵ��JSON�ַ���
 public String toString() {
  String result = "[";  
  for (Iterator it = list.iterator(); it.hasNext();) {
   result += ((Node) it.next()).toString();
   result += ",";
  }
  result = result.substring(0, result.length() - 1);
  result += "]";
  return result;
 }
 
 // ���ӽڵ�����
 public void sortChildren() {
  // �Ա���ڵ��������
  // �ɸ��ݲ�ͬ���������ԣ����벻ͬ�ıȽ��������ﴫ��ID�Ƚ���
  Collections.sort(list, new NodeIDComparator());
  // ��ÿ���ڵ����һ��ڵ��������
  for (Iterator it = list.iterator(); it.hasNext();) {
   ((Node) it.next()).sortChildren();
  }
 }
}

/**
 * �ڵ�Ƚ���
 */
class NodeIDComparator implements Comparator {
 // ���սڵ��űȽ�
 public int compare(Object o1, Object o2) {
  int j1 = Integer.parseInt(((Node)o1).id);
     int j2 = Integer.parseInt(((Node)o2).id);
     return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
 } 
}

/**
 * ��������Ĳ������
 */
class VirtualDataGenerator {
 // ��������Ľ�����б�ʵ��Ӧ���У�������Ӧ�ô����ݿ��в�ѯ��ã�
 public static List getVirtualResult() {    
  List dataList = new ArrayList();
  
  HashMap dataRecord1 = new HashMap();
  dataRecord1.put("id", "112000");
  dataRecord1.put("text", "�ȷ����н�ŵ�֧��");
  dataRecord1.put("parentId", "110000");
  
  HashMap dataRecord2 = new HashMap();
  dataRecord2.put("id", "112200");
  dataRecord2.put("text", "�ȷ����������֧��");
  dataRecord2.put("parentId", "112000");
  
  HashMap dataRecord3 = new HashMap();
  dataRecord3.put("id", "112100");
  dataRecord3.put("text", "�ȷ����й�����֧��");
  dataRecord3.put("parentId", "112000");
      
  HashMap dataRecord4 = new HashMap();
  dataRecord4.put("id", "113000");
  dataRecord4.put("text", "�ȷ����п�����֧��");
  dataRecord4.put("parentId", "110000");
      
  HashMap dataRecord5 = new HashMap();
  dataRecord5.put("id", "100000");
  dataRecord5.put("text", "�ȷ���������");
  dataRecord5.put("parentId", "");
  
  HashMap dataRecord6 = new HashMap();
  dataRecord6.put("id", "110000");
  dataRecord6.put("text", "�ȷ�����");
  dataRecord6.put("parentId", "100000");
  
  HashMap dataRecord7 = new HashMap();
  dataRecord7.put("id", "111000");
  dataRecord7.put("text", "�ȷ����н���֧��");
  dataRecord7.put("parentId", "110000");  
    
  dataList.add(dataRecord1);
  dataList.add(dataRecord2);
  dataList.add(dataRecord3);
  dataList.add(dataRecord4);
  dataList.add(dataRecord5);
  dataList.add(dataRecord6);
  dataList.add(dataRecord7);
  
  return dataList;
 } 
}