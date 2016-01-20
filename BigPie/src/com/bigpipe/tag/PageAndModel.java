package com.bigpipe.tag;

public class PageAndModel<T1, T2> {

	private T1 page;
	
	private T2 model;

	public T1 getPage() {
		return page;
	}

	public T2 getModel() {
		return model;
	}

	public void setPage(T1 page) {
		this.page = page;
	}

	public void setModel(T2 model) {
		this.model = model;
	}
}
