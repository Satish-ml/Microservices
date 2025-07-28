package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employeerecords")
public class EmployeeRecords {

    @Id
    @Column(name = "talent_Talent_local_ID")
    private String talent_Talent_local_ID;

    @Column(name = "talent_External_ID")
    private int talent_External_ID;

    @Column(name = "talent_Talent")
    private String talent_Talent;

    @Column(name = "talent_Local_Grade")
    private String talent_Local_Grade;

    @Column(name = "date_of")
    private Date date_of;

    @Column(name = "talent_Circle")
    private String talent_Circle;

    @Column(name = "talent_Circle_Name")
    private String talent_Circle_Name;

    @Column(name = "talent_Manager")
    private String talent_Manager;

    @Column(name = "talent_Mentor")
    private String talent_Mentor;

    @Column(name = "Lead")
    private String Lead;

    @Column(name = "task_Posting_indicator_external_ID")
    private String task_Posting_indicator_external_ID;

    @Column(name = "task_Name")
    private String task_Name;

    @Column(name = "account_Name")
    private String account_Name;

    @Column(name = "task_Start_date")
    private Date task_Start_date;

    @Column(name = "task_End_date")
    private Date task_End_date;

    @Column(name = "talent_City")
    private String talent_City;

    @Column(name = "task_Posting_Indicator_end_Date")
    private Date task_Posting_Indicator_end_Date;

    @Column(name = "task_Posting_Indicator_description")
    private String task_Posting_Indicator_description;

    @Column(name = "billable")
    private float billable;

    @Column(name = "non_billable")
    private float non_billable;

    @Column(name = "available")
    private float available;

    @Column(name = "absence")
    private float absence;

    @Column(name = "`leave`")
    private float leave;

    @Column(name = "joining_Date")
    private Date joining_Date;

    @Column(name = "final_Client_name")
    private String final_Client_name;

    @Column(name = "m_M")
    private float m_M;

    @Column(name = "engineering_Unit")
    private String engineering_Unit;

    @Column(name = "cluster")
    private String cluster;

    @Column(name = "eL_Mapping")
    private String eL_Mapping;

    @Column(name = "billability")
    private String billability;

    @Column(name = "type")
    private String type;

    @Column(name = "source")
    private String source;

    @Column(name = "final_Grade")
    private String final_Grade;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "diversity")
    private String diversity;

    @Column(name = "grade_Pyramid")
    private String grade_Pyramid;

    @Column(name = "cur_Date")
    private Date current_Date;

    @Column(name = "local_Time")
    private LocalTime time;

    @Column(name = "final_status")
    private String final_status;












}
