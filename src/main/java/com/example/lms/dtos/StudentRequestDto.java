package com.example.lms.dtos;

import com.example.lms.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {
    private int sId;
    private String sname;
    private int sage;
    private Department department;
    private String email;
}
