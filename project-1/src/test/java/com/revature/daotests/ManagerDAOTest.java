package com.revature.daotests;

import com.revature.model.Manager;
import org.junit.jupiter.api.*;

import com.revature.dao.ManagerDAO;
import com.revature.dao.ManagerDAOJavaImp;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManagerDAOTest
{

	private static ManagerDAO mdao = ManagerDAOJavaImp.getMdao();
    @Test
    @Order(1)
    void createManager()
    {
        Manager e = new Manager(1,
                "testM@gmail.com",
                "test123",
                "Test",
                "Tester");

        Manager result = mdao.createManager(e);

        Assertions.assertNotEquals(0, result.getMid());
    }

    @Test
    @Order(2)
    void getManagerById()
    {
        List<Manager> managerList = mdao.getAllManagers();
        Manager manager = managerList.get(managerList.size()-1);
        Manager result = mdao.getManagerById(manager.getMid());

        Assertions.assertEquals(manager.getEmail(), result.getEmail());
    }

    @Test
    @Order(3)
    void getAllManagers()
    {
        List<Manager> result = mdao.getAllManagers();

        Assertions.assertNotEquals(0, result.size());
    }

    @Test
    @Order(4)
    void updateManager()
    {
        List<Manager> managerList = mdao.getAllManagers();
        Manager manager = managerList.get(managerList.size()-1);
        manager.setEmail("testUpdatedM@gmail.com");
        Manager result = mdao.updateManager(manager);

        Assertions.assertEquals("testUpdatedM@gmail.com",result.getEmail());
    }

    @Test
    @Order(5)
    void deleteManager()
    {
        List<Manager> managerList = mdao.getAllManagers();
        Manager manager = managerList.get(managerList.size()-1);

        boolean result = mdao.deleteManager(manager);

        Assertions.assertEquals(true, result);

    }
}