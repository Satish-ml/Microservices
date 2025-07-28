package com.example.demo.Service;

//import com.example.demo.Repositories.EmployeeRecordsRepository;
import com.example.demo.Entity.EmployeeRecords;
import com.example.demo.Entity.EmployeeRecords2;
import com.example.demo.Repositories.EmployeeRecordsRepository;
import com.example.demo.Repositories.EmployeeRecordsRepository2;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class EmployeeRecordsService {

    @Autowired
    private EmployeeRecordsRepository employeeRecordsRepository;

    @Autowired
    private EmployeeRecordsRepository2 employeeRecordsRepository2;

    public String populateData() {
        String filePath = "C:/Users/s559/Downloads/PSS 15 JUL HC.xlsx";
       // String filePath = "C:/Users/s559/Downloads/PSS 15 JUL HC - Copy.xlsx";
        List<EmployeeRecords> employeeRecordsList= new LinkedList<>();
          try(FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fis)){

            Sheet sheet = workbook.getSheetAt(1);
            int count=0;
            for (Row row : sheet) {
                EmployeeRecords employeeRecords = new EmployeeRecords();
                if (row.getRowNum() == 0) continue;
               /* if(count<10){
                    System.out.println(getCellValue(row.getCell(1)));
                    count++;
                }*/
             employeeRecords.setTalent_Talent_local_ID(getCellValue(row.getCell(0)));
             employeeRecords.setTalent_External_ID(Integer.parseInt(getCellValue(row.getCell(1))));
             employeeRecords.setTalent_Talent(getCellValue(row.getCell(2)));
             employeeRecords.setTalent_Local_Grade(getCellValue(row.getCell(3)));
             employeeRecords.setDate_of(parseToDate(getCellValue(row.getCell(4))));
             employeeRecords.setTalent_Circle(getCellValue(row.getCell(5)));
             employeeRecords.setTalent_Circle_Name(getCellValue(row.getCell(6)));
             employeeRecords.setTalent_Manager(getCellValue(row.getCell(7)));
             employeeRecords.setTalent_Mentor(getCellValue(row.getCell(8)));
             employeeRecords.setLead(getCellValue(row.getCell(9)));
             employeeRecords.setTask_Posting_indicator_external_ID(getCellValue(row.getCell(10)));
             employeeRecords.setTask_Name(getCellValue(row.getCell(11)));
             employeeRecords.setAccount_Name(getCellValue(row.getCell(12)));
             employeeRecords.setTask_Start_date(parseToDate(getCellValue(row.getCell(13))));
             employeeRecords.setTask_End_date(parseToDate(getCellValue(row.getCell(14))));
             employeeRecords.setTalent_City(getCellValue(row.getCell(15)));
             employeeRecords.setTask_Posting_Indicator_end_Date(parseToDate(getCellValue(row.getCell(16))));
             employeeRecords.setTask_Posting_Indicator_description(getCellValue(row.getCell(17)));
             employeeRecords.setBillable(Float.parseFloat(isValueDateType(getCellValue(row.getCell(18)))));
             employeeRecords.setNon_billable(Float.parseFloat(isValueDateType(getCellValue(row.getCell(19)))));
             employeeRecords.setAvailable(Float.parseFloat(isValueDateType(getCellValue(row.getCell(20)))));
             employeeRecords.setAbsence(Float.parseFloat(isValueDateType(getCellValue(row.getCell(21)))));
             employeeRecords.setLeave(Float.parseFloat(isValueDateType(getCellValue(row.getCell(22)))));
             employeeRecords.setJoining_Date(parseToDate(getCellValue(row.getCell(23))));
             employeeRecords.setFinal_Client_name(getCellValue(row.getCell(24)));
             employeeRecords.setM_M(Float.parseFloat(isValueDateType(getCellValue(row.getCell(25)))));
             employeeRecords.setEngineering_Unit(getCellValue(row.getCell(26)));
             employeeRecords.setCluster(getCellValue(row.getCell(27)));
             employeeRecords.setEL_Mapping(getCellValue(row.getCell(28)));
             employeeRecords.setBillability(getCellValue(row.getCell(29)));
             employeeRecords.setType(getCellValue(row.getCell(30)));
             employeeRecords.setSource(getCellValue(row.getCell(31)));
             employeeRecords.setFinal_Grade(getCellValue(row.getCell(32)));
             employeeRecords.setRemarks(getCellValue(row.getCell(33)));
             employeeRecords.setDiversity(getCellValue(row.getCell(34)));
             employeeRecords.setGrade_Pyramid(getCellValue(row.getCell(35)));

            // employeeRecords.setCurrent_Date(parseToDate(LocalDateTime.now().toString()));
             employeeRecords.setTime(LocalTime.now());
             employeeRecords.setFinal_status("Active");

             //employeeRecordsRepository.save(employeeRecords);
                employeeRecordsList.add(employeeRecords);
            }
              employeeRecordsRepository.saveAll(employeeRecordsList);
            return "Data Inserted Successfully";
        } catch (IOException e) {
              return "Internal server Error while inserting data";
           // throw new RuntimeException(e);
        }

    }

    private String isValueDateType(String cellValue) {
        if(cellValue.length()>5 || cellValue.isEmpty()){
            return "0.0";
        }
        return cellValue;
    }

    public void data(){
        //import com.monitorjbl.xlsx.StreamingReader;
        String filePath = "C:/Users/s559/Downloads/PSS 15 JUL HC - Copy.xlsx";
        List<String> li =new ArrayList<>();
       // String filePath = "C:/Users/s559/Downloads/ex.xlsx";
        try (InputStream is = new FileInputStream(new File(filePath));
            // Workbook workbook = new XSSFWorkbook(is).builder()

                Workbook workbook = StreamingReader.builder()
                     .rowCacheSize(100)     // number of rows to keep in memory
                     .bufferSize(4096)      // buffer size to use when reading InputStream
                     .open(is)) {

            Sheet sheet = workbook.getSheetAt(1);
            int count=0;
            for (Row row : sheet) {
               // if (row.getRowNum() == 2) {
                  //  System.out.println("Cell type "+row.getCell(1).getCellType() + "\t");

                   // li.add(row.getCell(1).getValue);
                    for (Cell cell : row) {
                        System.out.print(getCellValue(cell) + "\t");
                       // li.add(cell.getStringCellValue());

                    }
                    System.out.println();
                //}
            }
            System.out.println("List "+li);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // or format with SimpleDateFormat
                } else {

                    // Format to avoid scientific notation
                    DecimalFormat df = new DecimalFormat("#");
                    df.setMaximumFractionDigits(0);
                    return df.format(cell.getNumericCellValue());

                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                // Evaluate the formula and return the result
                FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                return getCellValue(evaluator.evaluateInCell(cell));

            case BLANK:
                return "";

            case ERROR:
                return "ERROR";

            default:
                return "UNKNOWN";
        }
    }


    public static Date parseToDate(String dateTimeString) {
        if(dateTimeString == null || dateTimeString.trim().equals("-") ||
                dateTimeString.trim().equals("") || dateTimeString.trim().equals("ERROR") ){
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        try {
            Date date = formatter.parse(dateTimeString);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }



    public String saveFileData(InputStream inputStream) throws IOException {
        List<EmployeeRecords2> employeeRecordsList = new LinkedList<>();
        try{
        Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet1 = workbook.getSheetAt(1);
            int count=0;
                EmployeeRecords2 employeeRecords = new EmployeeRecords2();
                for(Row row : sheet1){
                if (row.getRowNum() == 0) continue;
               /* if(count<10){
                    System.out.println(getCellValue(row.getCell(1)));
                    count++;
                }*/
                employeeRecords.setTalent_Talent_local_ID(getCellValue(row.getCell(0)));
                employeeRecords.setTalent_External_ID(Integer.parseInt(getCellValue(row.getCell(1))));
                employeeRecords.setTalent_Talent(getCellValue(row.getCell(2)));
                employeeRecords.setTalent_Local_Grade(getCellValue(row.getCell(3)));
                employeeRecords.setDate_of(parseToDate(getCellValue(row.getCell(4))));
                employeeRecords.setTalent_Circle(getCellValue(row.getCell(5)));
                employeeRecords.setTalent_Circle_Name(getCellValue(row.getCell(6)));
                employeeRecords.setTalent_Manager(getCellValue(row.getCell(7)));
                employeeRecords.setTalent_Mentor(getCellValue(row.getCell(8)));
                employeeRecords.setLead(getCellValue(row.getCell(9)));
                employeeRecords.setTask_Posting_indicator_external_ID(getCellValue(row.getCell(10)));
                employeeRecords.setTask_Name(getCellValue(row.getCell(11)));
                employeeRecords.setAccount_Name(getCellValue(row.getCell(12)));
                employeeRecords.setTask_Start_date(parseToDate(getCellValue(row.getCell(13))));
                employeeRecords.setTask_End_date(parseToDate(getCellValue(row.getCell(14))));
                employeeRecords.setTalent_City(getCellValue(row.getCell(15)));
                employeeRecords.setTask_Posting_Indicator_end_Date(parseToDate(getCellValue(row.getCell(16))));
                employeeRecords.setTask_Posting_Indicator_description(getCellValue(row.getCell(17)));
                employeeRecords.setBillable(Float.parseFloat(isValueDateType(getCellValue(row.getCell(18)))));
                employeeRecords.setNon_billable(Float.parseFloat(isValueDateType(getCellValue(row.getCell(19)))));
                employeeRecords.setAvailable(Float.parseFloat(isValueDateType(getCellValue(row.getCell(20)))));
                employeeRecords.setAbsence(Float.parseFloat(isValueDateType(getCellValue(row.getCell(21)))));
                employeeRecords.setLeave(Float.parseFloat(isValueDateType(getCellValue(row.getCell(22)))));
                employeeRecords.setJoining_Date(parseToDate(getCellValue(row.getCell(23))));
                employeeRecords.setFinal_Client_name(getCellValue(row.getCell(24)));
                employeeRecords.setM_M(Float.parseFloat(isValueDateType(getCellValue(row.getCell(25)))));
                employeeRecords.setEngineering_Unit(getCellValue(row.getCell(26)));
                employeeRecords.setCluster(getCellValue(row.getCell(27)));
                employeeRecords.setEL_Mapping(getCellValue(row.getCell(28)));
                employeeRecords.setBillability(getCellValue(row.getCell(29)));
                employeeRecords.setType(getCellValue(row.getCell(30)));
                employeeRecords.setSource(getCellValue(row.getCell(31)));
                employeeRecords.setFinal_Grade(getCellValue(row.getCell(32)));
                employeeRecords.setRemarks(getCellValue(row.getCell(33)));
                employeeRecords.setDiversity(getCellValue(row.getCell(34)));
                employeeRecords.setGrade_Pyramid(getCellValue(row.getCell(35)));

                // employeeRecords.setCurrent_Date(parseToDate(LocalDateTime.now().toString()));
                employeeRecords.setTime(LocalTime.now());
                employeeRecords.setFinal_status("Active");

                //employeeRecordsRepository.save(employeeRecords);
                employeeRecordsList.add(employeeRecords);
            }
            employeeRecordsRepository2.saveAll(employeeRecordsList);
            return "Data Inserted Successfully";
        } catch (IOException e) {
            return "Internal server Error while inserting data";
            // throw new RuntimeException(e);
        }
    }
}
