//package com.example.lms.dtos;
//
//import com.example.lms.enums.Department;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class StudentResponseDto {
//    private int sId;
//    private String sname;
//    private Department department;
//    private String email;
//    private int cardId;
//}

package com.example.lms.dtos;

import com.example.lms.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {
    private UUID sId;
    private String sname;
    private Department department;
    private String email;
    private UUID cardId;  // Change the type of cardId to UUID
}
