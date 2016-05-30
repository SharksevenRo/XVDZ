package com.xiaov.web.support;

public class TokenThread implements Runnable{

	public void run() {

		try {
			System.out.println("线程启动");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AuthenticationCahce.check();
	}

}
