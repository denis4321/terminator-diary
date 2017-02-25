/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.sample1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Администратор
 */
public class HelloServlet extends HttpServlet {

    /*static {
        System.out.println("\tstatic init block");
    }

    {
        System.out.println("\t\tinit block");
    }

    public HelloServlet() {
        System.out.println("\t\tConstructor");
    }*/

    @Override
    public void init() {
        System.out.println("\t\t\tinit method");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("START####SERVICE#"+hashCode());
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis()-start<10000){            
        }
        System.out.println("FINISH####SERVICE#"+hashCode());        
    }

    @Override
    public void destroy() {
        System.out.println("\t\t\tdestroy method");
    }

}
