package com.ccautomation.utils;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelManager {

	
	private static String inputFile;
    private static Workbook workBookObj;
    static String TESTDATA_FILELOC = "/Data";

    /**
     * @param inputFile Sets the input file
     */
    public void setInputFile( String inputFile ) {
        this.inputFile = inputFile;
    }

    /**
     * @param filename
     * @param sheetName
     * @param rowValue
     * @return Map<String,String> this will read the data and it even reads
     *         empty cell
     * @throws BiffException
     * @throws IOException
     */

    public static Workbook loadFile( String filename ) {
        String pathAppend = TESTDATA_FILELOC;
        String completepath = pathAppend + "/" + filename + ".xls";
        try {
            System.out.println( "Loading the Excel file - " + filename );
            return Workbook.getWorkbook( new File( completepath ) );
        } catch ( Exception e ) {
            System.out.println( "Failure in loading the Excel file - " + filename );
        }
        return null;
    }

    /**
     * This method returns values in key value pair from excel file
     * @param filename
     * @param sheetName
     * @param rowValue
     * @return
     * @throws BiffException
     * @throws IOException
     */
    public static Map<String, String> getXlsRowDataAsMap1( String filename, String sheetName, String rowValue ) throws BiffException, IOException {

        Map<String, String> hmRowData = new HashMap<String, String>();
        try {

            Sheet sheetObject = null;
            Cell cellObj = null;
            int columnNumber = 0;
            int rowNumber = 0;

            //RowNumber of Column Headings
            int rowHeader = rowNumber;

            if ( inputFile != filename ) {
                workBookObj = loadFile( filename );
                inputFile = filename;
            }

            //        logScriptInfo("[INFO]WorkBook Object Created:"+workBookObj);
            sheetObject = workBookObj.getSheet( sheetName );

            //        Log.logScriptInfo("[INFO] Sheet Object Created:"+sheetObject);
            cellObj = sheetObject.findCell( rowValue );
            //        Log.logScriptInfo("[INFO]Cell Object Created:"+cellObj);
            columnNumber = cellObj.getColumn();
            rowNumber = cellObj.getRow();
            //getting no of columns in the sheet
            int totalColumns = sheetObject.getColumns();

            for ( int i = columnNumber + 1; i < totalColumns; i++ ) {
                if ( StringUtils.isEmpty(sheetObject.getCell( i, rowNumber ).getContents())){
                    hmRowData.put( sheetObject.getCell( i, rowHeader ).getContents().trim(), sheetObject.getCell( i, rowNumber ).getContents() );
                }
                if ( StringUtils.isNotEmpty(sheetObject.getCell( i, rowNumber ).getContents())) {
                    hmRowData.put( sheetObject.getCell( i, rowHeader ).getContents().trim(), sheetObject.getCell( i, rowNumber ).getContents() );
                }

            }
            //        Log.logScriptInfo("[INFO]sucessfulCompletion:getXlsRowData");
            return hmRowData;
        } catch ( Exception e ) {
        	e.printStackTrace();
        }
        return hmRowData;
    }

    /**
     * This function reads Data from an Excel Sheet in Hash Map Type
     * 
     * @parameter is: InputStream file
     * @parameter sheetName: sheetName
     */
    public static List<HashMap<String, String>> getTestDataFromExcel( InputStream is, String sheetName ) {

        Sheet sheet = getSheet( is, sheetName );
        int lastRow = sheet.getRows();
        int lastcolumn = sheet.getColumns();

        List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>( lastRow - 1 );

        for ( int i = 1; i < lastRow; i++ ) {
            HashMap<String, String> testdata = new HashMap<String, String>();
            for ( int j = 0; j < lastcolumn; j++ ){
                testdata.put( sheet.getCell( j, 0 ).getContents(), sheet.getCell( j, i ).getContents() );
            }
            result.add( testdata );
        }

        return result;
    }

    /**
     * This method returns the Sheet object which will contain all the data from
     * the specified sheet of the xls file
     * 
     * @param is
     * @param sheetName
     * @return
     */
    public static Sheet getSheet( InputStream is, String sheetName ) {
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook( is );
        } catch ( BiffException e ) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheet( sheetName );
        return sheet;
    }
}
