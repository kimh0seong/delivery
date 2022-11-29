package com.java.ex.dto;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private static Map<String, Object> session = new HashMap<String, Object>();
    public static void setSession(String name, Object value) {
        session.put(name, value);
    } 
    public static Object getSession(String name) {
        return session.get(name);
    }
}

/*
Session.setSession("member", memberDTO);

MemberDTO member = (MemberDTO)Session.getSession("member");

System.out.println("member : " + member);
*/