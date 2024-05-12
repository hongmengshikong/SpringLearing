package cn.edu.xcu.springboot.fileupload.controller;

import cn.edu.xcu.springboot.fileupload.entity.User;
import cn.edu.xcu.springboot.fileupload.service.UserService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {
    @Value("${file.downloadDir}")
    private String downloadDir;
    @Value("${file.uploadDir}")
    private String uploadDir;
    @Autowired
    private UserService userService;
    @GetMapping("/download/{filename}")
    public ResponseEntity download(@PathVariable String filename)throws Exception {
        File file=new File(downloadDir+filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(filename,"utf-8"));
        return new ResponseEntity(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }
    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file)throws Exception{
        if (file.isEmpty()){
            throw new RuntimeException("上传⽂件为空");
        }
        String originalFilename = file.getOriginalFilename();
        String filename= UUID.randomUUID().toString().replaceAll("-","")+"_"+originalFilename;
        File uploadFile = new File(uploadDir + filename);
        file.transferTo(uploadFile);
        return "⽂件上传成功";
    }
    @GetMapping("/export/user/poi")
    public void exportBypoi(HttpServletResponse response) throws IOException {
        List<User> users = userService.list();
        SXSSFWorkbook wb=new SXSSFWorkbook();
        Sheet sheet = wb.createSheet("⽤户信息");
        Row header = sheet.createRow(0);
        Cell cellUserId = header.createCell(0);
        cellUserId.setCellValue("⽤户ID");
        Cell cellUsername = header.createCell(1);
        cellUsername.setCellValue("⽤户名");
        Cell cellEmail = header.createCell(2);
        cellEmail.setCellValue("⽤户邮箱");
        Cell CellUserId = header.createCell(3);
        CellUserId.setCellValue("⽤户年龄");
        for (int i=0;i<users.size();i++){
            User user = users.get(i);
            Row row = sheet.createRow(i+1);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(user.getId());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(user.getUsername());
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(user.getEmail());
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(user.getAge());
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition","attachment;filename=user_excel"+System.currentTimeMillis()+".xlsx");
        wb.write(response.getOutputStream());
        wb.dispose();
    }
    @PostMapping("/import/user/poi")
    @ResponseBody
    public String importByPoi(MultipartFile file){
        if (file.isEmpty()){
            throw new RuntimeException("导⼊⽂件不能为空");
        }
        List<User> users= null;
        try {
            InputStream in = file.getInputStream();
            XSSFWorkbook wb = new XSSFWorkbook(in);
            in.close();
            XSSFSheet sheet = wb.getSheetAt(0);
            users = new ArrayList<>();
            for (int i=1;i<sheet.getLastRowNum();i++){
                XSSFRow row = sheet.getRow(i);
                User user=User.builder()
                        .id((long)row.getCell(0).getNumericCellValue())
                        .username(row.getCell(1).getStringCellValue())
                        .email(row.getCell(2).getStringCellValue())
                        .age((int)row.getCell(3).getNumericCellValue())
                        .password("123456").build();
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        users.stream().forEach(System.out::println);
//        userService.saveBatch(users);
        return "⽤户信息导⼊成功";
    }
    @GetMapping("/export/user/easy")
    public void exportByEasyExcel(HttpServletResponse response) throws IOException {
        List<User> users = userService.list();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition","attachment;filename=user_excel"+System.currentTimeMillis()+".xlsx");
        ServletOutputStream out = response.getOutputStream();
        EasyExcel.write(out,User.class).sheet("⽤户信息").doWrite(users);
        out.close();
    }
    @PostMapping("/import/user/easy")
    @ResponseBody
    public String importByEasyExcel(MultipartFile file)throws IOException{
        if (file.isEmpty()){
            throw new RuntimeException("导⼊⽂件不能为空");
        }
        InputStream in = file.getInputStream();
        EasyExcel.read(in, User.class, new PageReadListener<User>(users->{
            users.stream().forEach(System.out::println);
//            userService.saveBatch(users);
        })).sheet(0).doRead();
        return "⽤户信息导⼊成功";
    }
}
