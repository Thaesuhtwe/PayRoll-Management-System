package shared.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Allowance;
import entities.Deduction;
import entities.DeductionDetails;
import entities.Employee;

public class DeductionMapper {
    public Deduction mapToDeduction(Deduction deduction, ResultSet rs) {
        try {
            deduction.setDeduction_id(rs.getInt("deduction_id"));
            deduction.setDescription(rs.getString("description"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deduction;

    }
    public DeductionDetails mapToDeductionDetails(DeductionDetails deductionDetails, ResultSet rs) {
        try {
             deductionDetails.setDeduction_details_id(rs.getInt("deduction_details_id"));
             deductionDetails.setTax(rs.getString("tax"));
             deductionDetails.setSSC(rs.getString("SSC"));
             deductionDetails.setDeduction_amount(rs.getDouble("deduction_amount"));
             
             Employee employee = new Employee();
             employee.setId(rs.getInt("emp_id"));
             employee.setName(rs.getString("emp_name"));
             employee.setGender(rs.getString("emp_gender"));
             employee.setDateOfBirth(String.valueOf(rs.getDate("emp_dob")));
             employee.setPhone(rs.getString("emp_phone"));
             employee.setEmail(rs.getString("emp_email"));
             employee.setAddress(rs.getString("emp_address"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deductionDetails;

    }
}

