package yh.web;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import yh.model.Student;
import yh.utils.ExcelUtils;

@Controller
public class MyController  {
	

	@RequestMapping("/login")
	//http://localhost:8080/ExcelAPI/login
	public String login()throws Exception{
		return "login";
	}

	/**
	 * Excel下载
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/download")
	//http://localhost:8080/ExcelAPI/download
	public void excelDownload(HttpServletResponse response) throws Exception{
		ArrayList<Student> stuList = new ArrayList<Student>();
		Student stu1 = new Student("张三", "男", 18, new Date());
		Student stu2 = new Student("李四", "男", 50, new Date());
		Student stu3 = new Student("王五", "男", 19, new Date());
		stuList.add(stu1);
		stuList.add(stu2);
		stuList.add(stu3);
		String fileName = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
		fileName="D:/"+fileName+".xls";
		System.out.println("fileName:"+fileName);
		OutputStream out = new FileOutputStream(fileName);
		//放入一行头文件
		String[] headers = {"姓名","性别","年龄","日期"};
		String[] params = {"name","sex","age","date"};
		String str = "age:18,青年,50,中年;name:张三,张二";
		String timestr = "yyyy-MM-dd";
		ExcelUtils.exportExcel("测试", headers, params, stuList, Student.class, out, str, timestr);
		//上面已经将生成额excel放入到out中了;这个方法中的一个方法response.getOutputStream()可以得到out
		ExcelUtils.download(fileName, response); 
	}

}