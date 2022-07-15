package shared.mapper;



import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Allowance;
import entities.AllowanceDetails;
import entities.Employee;

public class AllowanceMapper {
    public Allowance mapToAllowance(Allowance allowance, ResultSet rs) {
        try {
             allowance.setAllowanceID(rs.getInt("allowance_id"));
             allowance.setDescription(rs.getString("description"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allowance;

    }
    public AllowanceDetails mapToAllowanceDetails(AllowanceDetails allowanceDetails, ResultSet rs) {
        try {
             allowanceDetails.setAdId(rs.getInt("ad_id"));
             allowanceDetails.setSkills(rs.getString("skills"));
             allowanceDetails.setLongevity(rs.getString("Longevity"));
             allowanceDetails.setAllowance_Amount(rs.getDouble("allowance_amount"));
             allowanceDetails.setDescription(rs.getString("description"));
             Employee employee = new Employee();
             employee.setId(rs.getInt("ad_employee_id"));
             employee.setName(rs.getString("emp_name"));
             employee.setGender(rs.getString("emp_gender"));
             employee.setDateOfBirth(String.valueOf(rs.getDate("emp_dob")));
             employee.setPhone(rs.getString("emp_phone"));
             employee.setEmail(rs.getString("emp_email"));
             employee.setAddress(rs.getString("emp_address"));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allowanceDetails;

    }
}
