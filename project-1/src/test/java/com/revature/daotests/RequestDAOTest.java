package com.revature.daotests;

import com.revature.model.Request;
import org.junit.jupiter.api.*;

import com.revature.dao.RequestDAO;
import com.revature.dao.RequestDAOJavaImp;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RequestDAOTest
{

	private static RequestDAO rdao = RequestDAOJavaImp.getRdao();
    @Test
    @Order(1)
    void createRequest()
    {
        Request e = new Request(1,
                1,
                2.14,
                "Reason",
                "Pending");

        Request result = rdao.createRequest(e);

        Assertions.assertNotEquals(0, result.getEid());
    }

    @Test
    @Order(2)
    void getRequestById()
    {
        List<Request> RequestList = rdao.getAllRequests();
        Request Request = RequestList.get(RequestList.size()-1);
        Request result = rdao.getRequestById(Request.getRid());

        Assertions.assertEquals(Request.getReason(), result.getReason());
    }

    @Test
    @Order(3)
    void getAllRequests()
    {
        List<Request> result = rdao.getAllRequests();

        Assertions.assertNotEquals(0, result.size());
    }

    @Test
    @Order(4)
    void updateRequest()
    {
        List<Request> RequestList = rdao.getAllRequests();
        Request Request = RequestList.get(RequestList.size()-1);
        Request.setReason("updatedReason");
        Request result = rdao.updateRequest(Request);

        Assertions.assertEquals("updatedReason",result.getReason());
    }

    @Test
    @Order(5)
    void deleteRequest()
    {
        List<Request> RequestList = rdao.getAllRequests();
        Request Request = RequestList.get(RequestList.size()-1);

        boolean result = rdao.deleteRequest(Request);

        Assertions.assertEquals(true, result);

    }
}