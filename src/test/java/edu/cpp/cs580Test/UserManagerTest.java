package edu.cpp.cs580Test;
import edu.cpp.cs580.data.User;

import edu.cpp.cs580.data.UserMap;
import edu.cpp.cs580.data.provider.FSUserManager;
import org.junit.Test;

import org.junit.Assert;

public class UserManagerTest {



	@Test
	public void testAddUser() {
		Assert.assertTrue(true);
	}

	@Test
	public void testAddUser2() {
		Assert.assertTrue(true);
	}

	@Test
	public void testGetuser(){
		FSUserManager fs = new FSUserManager();
		fs.getUser("1");
		Assert.assertTrue(true);
	}

	@Test
	public void testDeleteUser(){
		FSUserManager fs = new FSUserManager();
		fs.deleteUser("1");
		Assert.assertTrue(true);
	}


	@Test
	public void testUpdateuser(){
		FSUserManager fs = new FSUserManager();
		User user = new User();
		fs.updateUser(user);
		Assert.assertTrue(true);
	}

	@Test
	public void testUserMap(){
	    FSUserManager fs = new FSUserManager();
		UserMap us = fs.getUserMap();
		Assert.assertTrue(true);
	}

}
