package yh.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

/**\
 * 从Excel中读取数据，进行相关计算再写入到Excel中
 * @author YH
 *
 */
public class FareTest {
	
	@Test
	public void fareTest(){
		try {
			fare("D:\\电费.xls");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void fare(String filePath) throws IOException {
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
					double allFare = Double.valueOf(getValue(hssfSheet.getRow(rowNum-1).getCell(1)));
					double count1 = Double.valueOf(getValue(hssfSheet.getRow(rowNum-2).getCell(2)));
					double count2 = Double.valueOf(getValue(hssfSheet.getRow(rowNum-2).getCell(3)));
					double count3 = Double.valueOf(getValue(hssfSheet.getRow(rowNum-2).getCell(4)));
					double count4 = Double.valueOf(getValue(hssfSheet.getRow(rowNum-2).getCell(5)));
					double oldCount1 = Double.valueOf(getValue(hssfSheet.getRow(rowNum-4).getCell(2)));
					double oldCount2 = Double.valueOf(getValue(hssfSheet.getRow(rowNum-4).getCell(3)));
					double oldCount3 = Double.valueOf(getValue(hssfSheet.getRow(rowNum-4).getCell(4)));
					double oldCount4 = Double.valueOf(getValue(hssfSheet.getRow(rowNum-4).getCell(5)));
					
					double elecCount1 = count1 - oldCount1;//1室的度数
					double elecCount2 = count2 - oldCount2;//2室的度数
					double elecCount3 = count3 - oldCount3;//3室的度数
					double elecCount4 = count4 - oldCount4;//4室的度数
					double allElecCount = elecCount1 + elecCount2 + elecCount3 + elecCount4;
					String fare1 = String.format("%.2f", elecCount1/allElecCount * allFare);
					String fare2 = String.format("%.2f", elecCount2/allElecCount * allFare);
					String fare3 = String.format("%.2f", elecCount3/allElecCount * allFare);
					String fare4 = String.format("%.2f", elecCount4/allElecCount * allFare);
					for (int i = 2; i < 6; i++) {
						HSSFCell cell = hssfSheet.getRow(rowNum-1).getCell(i);
						if(cell == null) cell = hssfSheet.getRow(rowNum-1).createCell(i);
						if(i == 2) cell.setCellValue(fare1);
						if(i == 3) cell.setCellValue(fare2);
						if(i == 4) cell.setCellValue(fare3);
						if(i == 5) cell.setCellValue(fare4);
					}
					
					FileOutputStream out = new FileOutputStream(filePath);
					hssfWorkbook.write(out);
					out.close();
				return;
				}
			}
		}
	}
	
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

}
