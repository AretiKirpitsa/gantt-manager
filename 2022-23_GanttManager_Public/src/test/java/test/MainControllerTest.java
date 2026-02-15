package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import backend.MainController;
import backend.ReportType;
import backend.IMainController;
import backend.MainControllerFactory;
import dom.Task;
import dom2app.SimpleTableModel;

public class MainControllerTest {
	private static IMainController mainController;
	private static MainControllerFactory factory;
	private static ArrayList<Task> objCollection;
	static private String inputFilename;
	static private String outputFilename;
	static private String delimeter;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = new MainControllerFactory();
		mainController = factory.createMainController();
		objCollection = new ArrayList<Task>();

		inputFilename = "./Resources/TestInput/eggs.tsv";
		delimeter = "\t";
		boolean hasHeader = false;
		objCollection = new ArrayList<Task>();
	}

	@After
	public void tearDown() throws Exception {
		objCollection = new ArrayList<Task>();
		Object result = null;
	}

	@Test
	public final void testLoadData() {
		SimpleTableModel numRows = mainController.load("./Resources/TestInput/hld_with_emptyCells.txt", "\t");	
		
	}

	@Test
	public final void testReportResultInFile() {
		outputFilename = "./Resources/TestOutput/Eggs.txt";
		SimpleTableModel numRows = mainController.load(inputFilename, delimeter);
		System.out.println("Size to process: " + numRows);

		int results = mainController.createReport(inputFilename,ReportType.HTML) ;
	}

}

