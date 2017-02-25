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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Администратор
 */
@WebServlet(name = "MixedServlet", urlPatterns = {"/MixedServlet"})
public class MixedServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        super.service(req, resp);
        System.out.println("time=" + (System.currentTimeMillis() - start));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = "{'name':'Denis','age':30}";
        resp.setContentType("application/json");
        resp.setContentLength(json.getBytes().length);
        resp.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/zip");
        resp.setHeader("Content-Disposition", "attachment;filename=\"MyFile.zip\"");
        byte[] data = getZip();
        resp.setContentLength(data.length);
        resp.setHeader("file-name", "MyFile");
        try (OutputStream out = resp.getOutputStream();) {
            out.write(data);
        }
    }

    private byte[] getZip() {
        try (InputStream in = new FileInputStream(new File("D:\\test.zip"))) {
            byte[] data = new byte[in.available()];
            in.read(data);
            return data;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
