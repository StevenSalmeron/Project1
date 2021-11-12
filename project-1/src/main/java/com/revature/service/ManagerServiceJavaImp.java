package com.revature.service;

import java.util.List;

import javax.inject.Inject;

import com.revature.dao.ManagerDAO;
import com.revature.model.Manager;

public class ManagerServiceJavaImp implements ManagerService {
	private static ManagerService mserv = null;

	@Inject
	private ManagerDAO mdao;

	private ManagerServiceJavaImp() {
		super();
	}

	public static ManagerService getMserv() {
		if (mserv == null)
			mserv = new ManagerServiceJavaImp();

		return mserv;
	};

	@Override
	public Manager createManager(Manager manager) {
		return mdao.createManager(manager);
	}

	@Override
	public Manager getManagerById(int eid) {
		return mdao.getManagerById(eid);
	}

	@Override
	public Manager getManagerByEmail(String email) {
		List<Manager> managers = mdao.getAllManagers();
		for (Manager m : managers) {
			if (m.getEmail().compareToIgnoreCase(email) == 0) {
				return m;
			}
		}

		return null;
	}
	
	@Override
	public List<Manager> getAllManagers() {
		return mdao.getAllManagers();
	}


	@Override
	public Manager updateManager(Manager manager) {
		return mdao.updateManager(manager);
	}

	@Override
	public boolean deleteManager(Manager manager) {
		return mdao.deleteManager(manager);
	}
}
