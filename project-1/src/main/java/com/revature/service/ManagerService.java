package com.revature.service;

import java.util.List;

import com.revature.model.Manager;

public interface ManagerService {
// CREATE
	Manager createManager(Manager manager);

//	READ
	Manager getManagerById(int eid);
	
	Manager getManagerByEmail(String email);

	List<Manager> getAllManagers();

//	UPDATE
	Manager updateManager(Manager manager);

//	DELETE
	boolean deleteManager(Manager manager);
}
