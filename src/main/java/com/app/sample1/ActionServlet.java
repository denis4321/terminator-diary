/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.sample1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Администратор
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        System.out.println("name="+name);
        String path=req.getContextPath();
        System.out.println("ContextPath="+path);
        Cookie[] cookies=req.getCookies();
        for(Cookie c: cookies){
            System.out.println(c.getName()+"="+c.getValue());
        }
        Enumeration<String> paramsNames=req.getParameterNames();
        while(paramsNames.hasMoreElements()){
            String value=paramsNames.nextElement();
            System.out.println("value="+value);
            if("age".equalsIgnoreCase(name)){
                int age = Integer.parseInt(req.getParameter("age"));
                System.out.println("age="+age);
            }
        }
        //---------------------------RESPONSE
        resp.setContentType("application/zip");    
        resp.setHeader("Content-Disposition","attachment;filename=\"MyFile.zip\"");
        byte[] data=getZip();
        resp.setContentLength(data.length);
        resp.setHeader("file-name", "MyFile");
        try(OutputStream out = resp.getOutputStream();){
            
            out.write(data);
        }    
        
        
    }
    
    
    private byte[] getZip(){
        try(InputStream in = new FileInputStream(new File("D:\\test.zip"))){
            byte[] data = new byte[in.available()];
            in.read(data);
            return data;
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
    
    
}