package com.example.demo.Service;

import com.example.demo.Entity.EmployeeRecords2;
import com.example.demo.Repositories.EmployeeRecordsRepository2;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@Service
public class EmployeeRecordsService2 {

    @Autowired
    private EmployeeRecordsRepository2 employeeRecordsRepository;

    public String populateData() {
        String filePath = "C:/Users/s559/Downloads/PSS 15 JUL HC.xlsx";
        // String filePath = "C:/Users/s559/Downloads/PSS 15 JUL HC - Copy.xlsx";
        List<EmployeeRecords2> employeeRecordsList= new LinkedList<>();
        try(FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fis)){

            long count = employeeRecordsRepository.count();

            Sheet sheet = workbook.getSheetAt(1);
            for (Row row : sheet) {
                if(row.getRowNum()>count) {
                    EmployeeRecords2 employeeRecords = new EmployeeRecords2();
                    if (row.getRowNum() == 0) continue;

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

                    employeeRecords.setCurrent_Date(new Date());
                    employeeRecords.setTime(LocalTime.now());
                    if (row.getRowNum() > 3104) {
                        employeeRecords.setFinal_status("In-Active");
                    } else {
                        employeeRecords.setFinal_status("Active");

                    }

                    //employeeRecordsRepository.save(employeeRecords);
                    employeeRecordsList.add(employeeRecords);
                }
            }
            employeeRecordsRepository.saveAll(employeeRecordsList);
            return "Data Inserted Successfully";
        } catch (IOException e) {
            return "Internal server Error while inserting data";
            // throw new RuntimeException(e);
        }
        catch (RuntimeException e){
            return "Run Time Exception Occurred while inserting data";
        }


    }

    private String isValueDateType(String cellValue) {
        if(cellValue.length()>5 || cellValue.isEmpty()){
            return "0.0";
        }
        return cellValue;
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
                    return cell.getDateCellValue().toString();
                } else {
                    DecimalFormat df = new DecimalFormat("#");
                    df.setMaximumFractionDigits(0);
                    return df.format(cell.getNumericCellValue());

                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
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
}
