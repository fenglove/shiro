package com.itcpay.shiro.test;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

/**
 * 基础类
 * @author:liufeng
 * @date:2017年5月25日
 */
public abstract class BaseTest {

	private static final Logger log = Logger.getLogger(BaseTest.class);
	
	public void login(String ini, String username, String password) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(ini);
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		try {
			subject.login(token);
		} catch (Exception e) {
			log.debug("登录失败====>" + e);
		}
		log.debug("是否登录====" + subject.isAuthenticated());
	}

	public Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	@After
	public void tearDown(){
		ThreadContext.unbindSubject();//退出时请解除绑定subject到线程，否则对下次测试造成影响
	}
}
