package com.ybliu.pattern.Strategy;

/**
* ���ˣ���ҿ�����������������ˣ�����Ҫ�и��ط�����Щ���ѽ���Ž���ѽ��
* @author cbf4Life cbf4life@126.com
* I'm glad to share my knowledge with you all.
* ��ı���ˣ��ǻ�Ҫ�н���
*/
public class Context {
	
	//���캯������Ҫʹ���Ǹ����
	private IStrategy straegy;
	public Context(IStrategy strategy){
	this.straegy = strategy;
	}
	//ʹ�ü�ı�ˣ����ҳ�����
	public void operate(){
	this.straegy.operate();
	}

}
