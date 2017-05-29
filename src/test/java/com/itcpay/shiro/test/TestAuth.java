package com.itcpay.shiro.test;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * 
 * @author:liufeng
 * @date:2017年5月25日
 */
public class TestAuth extends BaseTest {

	private static final Logger log = Logger.getLogger(TestAuth.class);
	
	@Test
	public void testRole(){
		login("classpath:shiro-role.ini","lfeng","123456");
		//subject.hasRole()方法
		log.debug("hasRole：是否拥有role1角色====>"+getSubject().hasRole("role1"));
		//subject.hasAllRoles()方法
		log.debug("hasAllRoles：是否拥有role1和role2角色====>"+getSubject().hasAllRoles(Arrays.asList("role1","role2")));
		//subject.hasRoles()方法
		boolean[] result = getSubject().hasRoles(Arrays.asList("role1","role2","role3"));
		log.debug("hasRoles：是否拥有role1角色====>"+result[0]);
		log.debug("hasRoles：是否拥有role2角色====>"+result[1]);
		log.debug("hasRoles：是否拥有role3角色====>"+result[2]);
	}
	
	@Test
	public void testPermission(){
		login("classpath:shiro-permission.ini","feng","123456");
		log.debug("feng：是否拥有user:create权限====>"+getSubject().isPermitted("user:create"));
		log.debug("feng：是否拥有user:create,user:update权限====>"+getSubject().isPermittedAll("user:create","user:update"));
		log.debug("feng：是否拥有user:view权限====>"+getSubject().isPermitted("user:view"));
		
		login("classpath:shiro-permission.ini","lfeng","123456");
		log.debug("lfeng：是否拥有user:create权限====>"+getSubject().isPermitted("user:create"));
		log.debug("lfeng：是否拥有user:create,user:delete权限====>"+getSubject().isPermittedAll("user:create","user:delete"));
		log.debug("lfeng：是否拥有user:view权限====>"+getSubject().isPermitted("user:view"));
		
	}
	
	
}
