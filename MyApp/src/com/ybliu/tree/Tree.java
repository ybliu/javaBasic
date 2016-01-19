package com.ybliu.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {
	private String ID;	// ����ID
	private String AREA_NAME;	// ������ʾ������
	private String PARENT_ID;	// ��ID	����AREA_ID
	private Tree parentObj;	// ���ڵ����
	private List<Tree> childrenList = new ArrayList<Tree>();	// �ӽڵ�
	 
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String area_name) {
		AREA_NAME = area_name;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String parent_id) {
		PARENT_ID = parent_id;
	}
	public Tree getParentObj() {
		return parentObj;
	}
	public void setParentObj(Tree parentObj) {
		this.parentObj = parentObj;
	}
	public List<Tree> getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(List<Tree> childrenList) {
		this.childrenList = childrenList;
	}
	

}
