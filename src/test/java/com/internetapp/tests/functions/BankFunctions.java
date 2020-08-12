package com.internetapp.tests.functions;

import java.util.ArrayList;
import java.util.HashMap;

import com.codoid.products.exception.FilloException;
import com.internetapp.pages.OpenAirHome;
import com.internetapp.pages.OutlookHome;
import com.internetapp.pages.OutlookLogin;
import com.maveric.core.utils.data.ExcelDataReader;

public class BankFunctions {

	public void loginToOutlook(String tcId) throws FilloException {
		new OutlookLogin()
				.outlookNavigate(ExcelDataReader.getData("Sheet2", tcId, "url"),
						ExcelDataReader.getData("Sheet2", tcId, "driverType"))
				.enterUserName(ExcelDataReader.getData("Sheet2", tcId, "userName")).clickNext()
				.enterPassword(ExcelDataReader.getData("Sheet2", tcId, "password")).clickNext().clickStaySignNo();
	}

	public void openAirFromOutlook() {
		try {
			System.out.println("Inside openAirFromOutlook");
			new OutlookHome().clickMenu().clickMenuOpenAir();
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}

	public void updateTimesheet(String tcId) throws FilloException {
//		try {

			if (ExcelDataReader.getData("Sheet2", tcId, "timesheetCreated").equalsIgnoreCase("Yes")) {
				new OpenAirHome().clickTimesheetsMenu()
						.clickTimeSheetCategory(ExcelDataReader.getData("Sheet2", tcId, "timesheetCategory"))
						.clickTargetedTimesheet(ExcelDataReader.getData("Sheet2", tcId, "weekstarting"));
			} else {
				new OpenAirHome().clickCreateMenu().clickTimesheetNew()
						.selectTimesheet(ExcelDataReader.getData("Sheet2", tcId, "weekstarting")).clickSaveToCreate();
			}

			HashMap<String, Integer> weekColumn = new HashMap<String, Integer>();
			weekColumn.put("mon", 5);
			weekColumn.put("tue", 6);
			weekColumn.put("wed", 7);
			weekColumn.put("thu", 8);
			weekColumn.put("fri", 9);
			weekColumn.put("sat", 10);
			weekColumn.put("sun", 11);

			ArrayList<String> rowExecution = new ArrayList<String>();
			String[] elementList = ExcelDataReader.getData("Sheet2", tcId, "rowDriver").split("_");
			int rowCount = elementList.length;

			for (int i = 0; i < elementList.length; i++) {
				rowExecution.add(elementList[i]);
				System.out.println(elementList[i]);
			}
			int i = 1;
			for (String weekInput : rowExecution) {
				new OpenAirHome().selectTSGrid("ts_c1_r" + i, ExcelDataReader.getData("Sheet2", tcId, "project" + i))
						.selectTSGrid("ts_c2_r" + i, ExcelDataReader.getData("Sheet2", tcId, "task" + i))
						.selectTSGrid("ts_c3_r" + i, ExcelDataReader.getData("Sheet2", tcId, "timeType" + i))
						.selectTSGrid("ts_c4_r" + i, ExcelDataReader.getData("Sheet2", tcId, "location" + i));

				String[] weeks = weekInput.split(";");

				for (String week : weeks) {
					String column = "ts_c" + weekColumn.get(week) + "_r" + i;
					new OpenAirHome().inputHours(column, ExcelDataReader.getData("Sheet2", tcId, "week" + i))
							.clickAddtlInfoLink(column)
							.selectPremise(ExcelDataReader.getData("Sheet2", tcId, "premise")).clickAddtlInfoSubmit();
				}
				i++;
				if (i > rowCount)
					break;

			}
//			new OpenAirHome().clickSubmitTS();
		 
	}

}
