package com.ybliu.pattern.Strategy;

/**
* 好了，大家看看，三个妙计是有了，那需要有个地方放这些妙计呀，放锦囊呀：
* @author cbf4Life cbf4life@126.com
* I'm glad to share my knowledge with you all.
* 计谋有了，那还要有锦囊
*/
public class Context {
	
	//构造函数，你要使用那个妙计
	private IStrategy straegy;
	public Context(IStrategy strategy){
	this.straegy = strategy;
	}
	//使用计谋了，看我出招了
	public void operate(){
	this.straegy.operate();
	}

}
