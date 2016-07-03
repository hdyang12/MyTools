package yh.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * 通过poi实现对Excel文件的读取 对xls的操作只需要poi的jar包
 * 对xlsx的操作需要：
 * poi项目的：	1.poi-3.9-20121203.jar
 * 				2.poi-ooxml-3.9-20121203.jar
 * 				3.poi-ooxml-schemas-3.9-20121203.jar
 * dom4j项目的：dom4j-1.6.1.jar
 * xmlbeans项目的：xbean.jar
 * @author UCMED
 * 
 */
public class ExcelTest {
  

	/**
	 * 写入到Excel中(新建再写入信息)
	 * 可以判断是03版或者07版，再写入
	 * @throws IOException
	 */
	@Test
	public void writeExcelTest() throws IOException {
		File file = new File("D:/"+System.currentTimeMillis()+".xlsx");
		List<String> mList = getList();
		writeExcel(file, mList);
	}
	

	/**
	 * 读取Excel中内容
	 * 可以判断是03版或者07版，再读取
	 * @throws IOException
	 */
	@Test
	public void readExcelTest() throws IOException {
		String filePath = "D:/1467540680723.xlsx";
		String extension = filePath.lastIndexOf(".") == -1 ? "" : filePath
				.substring(filePath.lastIndexOf(".") + 1);
		// 判断文件类型
		if("xls".equals(extension)){
			readXls(filePath);
		}else if("xlsx".equals(extension)){
			readXlsx(filePath);
		}else{
			throw new IOException("不支持的文件类型");
		}
	}
	
	
	/**
	 * 追加信息写入到Excel中(03和07版)
	 */
	@Test
	public void addWriteExcelTest() throws IOException {
		String filePath = "D:/1467540680723.xlsx";
		String extension = filePath.lastIndexOf(".") == -1 ? "" : filePath
				.substring(filePath.lastIndexOf(".") + 1);
		// 判断文件类型
		if("xls".equals(extension)){
			addWriteXls(filePath,getList());
		}else if("xlsx".equals(extension)){
			addWriteXlsx(filePath,getList());
		}else{
			throw new IOException("不支持的文件类型");
		}
	}
	
	
	/**
	 * 追加内容到2003版Excel
	 * @param filePath
	 * @param mList
	 */
	private void addWriteXls(String filePath,List<String> mList) {
		try {
			InputStream is = new FileInputStream(filePath);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

			// 循环工作表Sheet
	 		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}

				int finalRowNum = hssfSheet.getLastRowNum();
				// 循环行Row
				for (int rowNum = finalRowNum; rowNum <= finalRowNum+1; rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow == null) {
						hssfSheet.createRow(rowNum);
						for (int i = 0; i < mList.size(); i++) {
							HSSFCell cell = hssfSheet.getRow(rowNum).getCell(i);
							if(cell == null) cell = hssfSheet.getRow(rowNum).createCell(i);
							cell.setCellValue(mList.get(i));
						}
						
						FileOutputStream out = new FileOutputStream(filePath);
						hssfWorkbook.write(out);
						out.close();
						
					}
				}
	 		}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		
	}


	/**
	 * 追加内容到2007版Excel
	 * @param filePath
	 * @param mList
	 */
	private void addWriteXlsx(String filePath,List<String> mList) {
		try {
		InputStream is = new FileInputStream(filePath);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

		// 循环工作表Sheet
 		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
 			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}

			int finalRowNum = xssfSheet.getLastRowNum();
			// 循环行Row
			for (int rowNum = finalRowNum; rowNum <= finalRowNum+1; rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					xssfSheet.createRow(rowNum);
					for (int i = 0; i < mList.size(); i++) {
						XSSFCell cell = xssfSheet.getRow(rowNum).getCell(i);
						if(cell == null) cell = xssfSheet.getRow(rowNum).createCell(i);
						cell.setCellValue(mList.get(i));
					}
					
					FileOutputStream out = new FileOutputStream(filePath);
					xssfWorkbook.write(out);
					out.close();
					
				}
			}
 		}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}


	/**
	 * 写入到excel 的方法
	 */

	public static void writeExcel(File file, List<String> mList)
			throws IOException {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
				.substring(fileName.lastIndexOf(".") + 1);
		// 判断文件类型
		if ("xls".equals(extension)) {
				write2003Excel(file.getPath(), mList);
		} else if ("xlsx".equals(extension)) {
			write2007Excel(file.getPath(), mList);
		} else {
			throw new IOException("不支持的文件类型");
		}
	}
	

	/**
	 * 写入07excel 
	 * 
	 * @param filePath
	 */
	public static void write2007Excel(String filePath, List<String> list) {
		try {
			OutputStream os = new FileOutputStream(filePath);
        //工作区       
        SXSSFWorkbook wb = new SXSSFWorkbook();       
        SXSSFSheet sheet= (SXSSFSheet)wb.createSheet("test");  
        SXSSFRow row =null;  
        for(int i=0;i<1;i++){    
            row = (SXSSFRow) sheet.createRow(i);      
            for(int j=0;j<list.size();j++){  
                 row.createCell(j).setCellValue(list.get(j));      
            }      
        }     
        //写文件       
        wb.write(os);       
        //关闭输出流       
        os.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * 写入03excel
	 * 
	 * @param filePath
	 */
	public static void write2003Excel(String filePath, List<String> list) {
		try {
			OutputStream os = new FileOutputStream(filePath);
        //工作区       
        HSSFWorkbook wb = new HSSFWorkbook();       
        HSSFSheet sheet= (HSSFSheet)wb.createSheet("test");  
        HSSFRow row =null;  
        for(int i=0;i<1;i++){    
            row = (HSSFRow) sheet.createRow(i);      
            for(int j=0;j<list.size();j++){  
                 row.createCell(j).setCellValue(list.get(j));      
            }      
        }     
        //写文件       
        wb.write(os);       
        //关闭输出流       
        os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 读取2003版Excel
	 * @throws IOException
	 */
	private void readXls(String filePath) throws IOException {
		InputStream is = new FileInputStream(filePath);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}

			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}

				// 循环列Cell
				for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
					HSSFCell hssfCell = hssfRow.getCell(cellNum);
					if (hssfCell == null) {
						continue;
					}

					System.out.print("    " + getValue(hssfCell));
				}
				System.out.println();
			}
		}
	}

	/**
	 * 得到2003版Excel的值
	 * @param hssfCell
	 * @return
	 */
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	
	/**
	 * 读取2007版Excel
	 * @throws IOException
	 */
	private void readXlsx(String filePath) throws IOException {
		InputStream is = new FileInputStream(filePath);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet XSSFSheet = xssfWorkbook.getSheetAt(numSheet);
			if (XSSFSheet == null) {
				continue;
			}

			// 循环行Row
			for (int rowNum = 0; rowNum <= XSSFSheet.getLastRowNum(); rowNum++) {
				XSSFRow XSSFRow = XSSFSheet.getRow(rowNum);
				if (XSSFRow == null) {
					continue;
				}

				// 循环列Cell
				for (int cellNum = 0; cellNum <= XSSFRow.getLastCellNum(); cellNum++) {
					XSSFCell XSSFCell = XSSFRow.getCell(cellNum);
					if (XSSFCell == null) {
						continue;
					}

					System.out.print("    " + getValue(XSSFCell));
				}
				System.out.println();
			}
		}
	}

	/**
	 * 得到2007版Excel的值
	 * @param XSSFCell
	 * @return
	 */
	private String getValue(XSSFCell XSSFCell) {
		if (XSSFCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(XSSFCell.getBooleanCellValue());
		} else if (XSSFCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(XSSFCell.getNumericCellValue());
		} else {
			return String.valueOf(XSSFCell.getStringCellValue());
		}
	}
	
	private List<String> getList() {
		List<String> mList = new LinkedList<String>();
		mList.add("aa");
		mList.add("bb");
		mList.add("cc");
		mList.add("dd");
		mList.add("ee");
		return mList;
	}
	
	
	
}
