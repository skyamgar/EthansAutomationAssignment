package com.ethans.automation.poianddb;

import java.util.HashMap;

public class TestDB {

	public static void main(String[] args) {
		HashMap<String, Employee> map = new HashMap();
		XlsUtil util = new XlsUtil();
		map = util.readDataSheetAsMap();
		DBUtil.createDataBase();
		DBUtil.createTable();
		
	}

}
