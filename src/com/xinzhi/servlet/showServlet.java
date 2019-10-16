package com.xinzhi.servlet;

import com.xinzhi.pojo.Data;
import com.xinzhi.thread.CallableDemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@WebServlet(name = "showServlet")
public class showServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ExecutorService service = Executors.newFixedThreadPool(2);
        CallableDemo cd = new CallableDemo();
        Future<List<Data>> future1 = service.submit(cd);
        Future<List<Data>> future2 = service.submit(cd);
        List<Data> list1 = null;
        List<Data> list2 = null;
        try {
            list1 = future1.get();
            list2 = future2.get();
            for (Data data : list2){
                list1.add(data);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        request.setAttribute("list1",list1);
        request.setAttribute("list2",list2);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
