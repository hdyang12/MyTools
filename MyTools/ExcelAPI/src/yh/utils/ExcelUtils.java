package yh.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * Excel的工具类 可分为4种：
 * 1.包括：表格名,标题行内容,各标题对应的参数名,数据(用了泛型,保证通用性),实体对象的类型,用于反射,输出流(exportExcel)
 * 2.包括：表格名,标题行内容,各标题对应的参数名,数据(用了泛型,保证通用性),实体对象的类型,用于反射,输出流,需要改变的内容(
 * exportExcelChangeValue)
 * 3.包括：表格名,标题行内容,各标题对应的参数名,数据(用了泛型,保证通用性),实体对象的类型,用于反射,输出流
 * ,改变日期格式(exportExcelChangeDateFormat)
 * 4.包括：表格名,标题行内容,各标题对应的参数名,数据(用了泛型,保证通用性),实体对象的类型
 * ,用于反射,输出流,需要改变的内容,日期格式(exportExcel)
 * 
 * @author YH
 *
 */
public class ExcelUtils {

	/**
	 * 
	 * @param title
	 *            表格名
	 * @param headers
	 *            标题行内容
	 * @param params
	 *            各标题对应的参数名
	 * @param stuList
	 *            数据(用了泛型,保证通用性)
	 * @param clazz
	 *            实体对象的类型,用于反射
	 * @param out
	 *            输出流
	 * @throws Exception
	 */
	public static <T> void exportExcel(String title, String[] headers,
			String[] params, ArrayList<T> stuList, Class<T> clazz,
			OutputStream out) throws Exception {
		exportExcel(title, headers, params, stuList, clazz, out, null, null);
	}

	/**
	 * 
	 * @param title
	 *            表格名
	 * @param headers
	 *            标题行内容
	 * @param params
	 *            各标题对应的参数名
	 * @param stuList
	 *            数据(用了泛型,保证通用性)
	 * @param clazz
	 *            实体对象的类型,用于反射
	 * @param out
	 *            输出流
	 * @param str
	 *            需要改变的内容;比如:String str =
	 *            "age:18,青年,50,中年;name:张三,张二";如果age属性的值为18
	 *            ,改为青年;为50,改为中年。name属性值为张三,改为张二。
	 * @throws Exception
	 */
	public static <T> void exportExcelChangeValue(String title,
			String[] headers, String[] params, ArrayList<T> stuList,
			Class<T> clazz, OutputStream out, String str) throws Exception {
		exportExcel(title, headers, params, stuList, clazz, out, str, null);
	}

	/**
	 * 
	 * @param title
	 *            表格名
	 * @param headers
	 *            标题行内容
	 * @param params
	 *            各标题对应的参数名
	 * @param stuList
	 *            数据(用了泛型,保证通用性)
	 * @param clazz
	 *            实体对象的类型,用于反射
	 * @param out
	 *            输出流
	 * @param str
	 *            日期格式
	 * @throws Exception
	 */
	public static <T> void exportExcelChangeDateFormat(String title,
			String[] headers, String[] params, ArrayList<T> stuList,
			Class<T> clazz, OutputStream out, String str) throws Exception {
		exportExcel(title, headers, params, stuList, clazz, out, null, str);
	}

	/**
	 * 
	 * @author YH
	 * @param title
	 *            表格名
	 * @param headers
	 *            标题行内容
	 * @param params
	 *            各标题对应的参数名
	 * @param stuList
	 *            数据(用了泛型,保证通用性)
	 * @param clazz
	 *            实体对象的类型,用于反射
	 * @param out
	 *            输出流
	 * @param str
	 *            需要改变的内容;比如:String str = "age:18,青年,50,中年;name:张三,张二";
	 *            如果age属性的值为18,改为青年;为50,改为中年。
	 * @param timestr
	 *            日期格式
	 * @throws Exception
	 */
	public static <T> void exportExcel(String title, String[] headers,
			String[] params, ArrayList<T> stuList, Class<T> clazz,
			OutputStream out, String str, String timestr) throws Exception {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		// style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		// style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		try {
			// 遍历集合数据，产生数据行
			int index = 0;
			Field field = null;
			if (str == null) {
				for (T t : stuList) {
					index++;
					row = sheet.createRow(index);
					for (int i = 0; i < params.length; i++) {
						HSSFCell cell = row.createCell(i);
						cell.setCellStyle(style2);
						field = clazz.getDeclaredField(params[i]);
						field.setAccessible(true);

						String value = "";
						if (field.get(t) != null) {
							if (field.get(t) instanceof Date && timestr != null) {
								SimpleDateFormat sdf = new SimpleDateFormat(
										timestr);
								value = sdf.format(field.get(t));
							} else {
								value = field.get(t).toString();
							}
						}
						HSSFRichTextString richString = new HSSFRichTextString(
								value);
						HSSFFont font3 = workbook.createFont();
						font3.setColor(HSSFColor.BLUE.index);
						richString.applyFont(font3);
						cell.setCellValue(richString);
					}

				}
			} else {
				ArrayList<String> changeParamsList = new ArrayList<String>();
				Map<String, Map<String, String>> changeParams = new HashMap<String, Map<String, String>>();
				String[] strs = str.split(";");
				for (int i = 0; i < strs.length; i++) {
					String[] strs1 = strs[i].split(":");
					String[] strs2 = strs1[1].split(",");
					Map<String, String> map = new HashMap<String, String>();
					for (int j = 0; j < strs2.length; j += 2) {
						map.put(strs2[j], strs2[j + 1]);
					}
					changeParamsList.add(strs1[0]);
					changeParams.put(strs1[0], map);
				}
				for (T t : stuList) {
					index++;
					row = sheet.createRow(index);
					for (int i = 0; i < params.length; i++) {
						HSSFCell cell = row.createCell(i);
						cell.setCellStyle(style2);
						field = clazz.getDeclaredField(params[i]);
						field.setAccessible(true);
						String value = "";
						if (field.get(t) != null) {
							if (field.get(t) instanceof Date && timestr != null) {
								SimpleDateFormat sdf = new SimpleDateFormat(
										timestr);
								value = sdf.format(field.get(t));
							} else {
								value = field.get(t).toString();
							}
						}
						for (String param : changeParamsList) {
							if (param.equals(params[i])) {
								Map<String, String> map = changeParams
										.get(param);
								if (map.get(value) != null) {
									value = map.get(value);
								}
							}
						}
						HSSFRichTextString richString = new HSSFRichTextString(
								value);
						HSSFFont font3 = workbook.createFont();
						font3.setColor(HSSFColor.BLUE.index);
						richString.applyFont(font3);
						cell.setCellValue(richString);
					}

				}
			}
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件的下载
	 * 
	 * @param path
	 * @param response
	 */
	public static void download(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new FileInputStream(path);
			byte[] buffer = new byte[(int) file.length()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
