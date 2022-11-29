
package com.java.ex.main;

import java.util.HashMap;
import java.util.Map;

import com.java.ex.dao.BusinessDAO;
import com.java.ex.dto.BusinessDTO;

public class MainClass {
	public static void main(String[] args) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("kyo", "Çã´Ï");
		map.get("kyo");
		System.out.println(map.get("kyo"));
	}
}
