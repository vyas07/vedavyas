package com.Birst.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Birst.Pages.BirstFilterBy_Resources;
import com.Birst.utility.BrowserFactory;

import GenericFunctions.BaseClass;

public class FilterBy_Validation extends BaseClass {

	@Test

	public void NavigateToBirst() {
		logger = report.createTest("Birst Webinar Verificarion");
		

		BirstFilterBy_Resources birstfilter = PageFactory.initElements(driver, BirstFilterBy_Resources.class);
		
		birstfilter.VerifyGoogleTitle();
		birstfilter.GoogleSearch(excel.getData("BirstData", 1, 0));
		birstfilter.GetBirstTitle();
		birstfilter.AcceptCookies();
		birstfilter.VerifyLogoExistOnPage();
		birstfilter.NavigateToResources();
		birstfilter.VerifyResourcePageLoad();
		birstfilter.FilterBy();
		SelectDropDown(2, excel.getData("BirstData", 2, 0));
		SelectDropDown(3, excel.getData("BirstData", 3, 0));
		birstfilter.VerifyWebinar();

	}

}
