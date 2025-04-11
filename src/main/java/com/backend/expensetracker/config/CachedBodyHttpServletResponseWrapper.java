//package com.backend.expensetracker.config;
//
//import jakarta.servlet.ServletOutputStream;
//import jakarta.servlet.WriteListener;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpServletResponseWrapper;
//import java.io.CharArrayWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class CachedBodyHttpServletResponseWrapper extends HttpServletResponseWrapper {
//
//    private final CharArrayWriter charArrayWriter = new CharArrayWriter();
//    private final PrintWriter writer = new PrintWriter(charArrayWriter);
//    private int statusCode;
//
//    public CachedBodyHttpServletResponseWrapper(HttpServletResponse response) {
//        super(response);
//    }
//
//    @Override
//    public PrintWriter getWriter() throws IOException {
//        return writer;
//    }
//
//    @Override
//    public void setStatus(int sc) {
//        this.statusCode = sc;
//        super.setStatus(sc);
//    }
//
//    public String getCaptureAsString() {
//        writer.flush();
//        return charArrayWriter.toString();
//    }
//
//    public int getStatus() {
//        return this.statusCode;
//    }
//}
