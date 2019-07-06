package com.ethans.automation.poianddb;

import java.util.HashMap;
import java.util.Map;

public class TestDB {

	public static void main(String[] args) {
		HashMap<String, Employee> map = new HashMap();
		XlsUtil util = new XlsUtil();
		map = util.readDataSheetAsMap();
		DBUtil.createDataBase();
		DBUtil.createTable();
		for(Map.Entry<String, Employee> entry : map.entrySet()){
			DBUtil.insertRecord(entry.getKey(), entry.getValue().getName(), entry.getValue().getAge(), entry.getValue().getGender(), entry.getValue().getRole());
		}
		DBUtil.clean();
	}

}
