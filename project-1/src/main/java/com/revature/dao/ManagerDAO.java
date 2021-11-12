package com.revature.dao;

import java.util.List;

import com.revature.model.Manager;

public interface ManagerDAO {

//	CRUD
	Manager createManager(Manager manager);

//	READ
	Manager getManagerById(int mid);

	List<Manager> getAllManagers();

//	Update
	Manager updateManager(Manager manager);

//	DELETE
	boolean deleteManager(Manager manager);
}
