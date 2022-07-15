package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import entities.Deduction;
import entities.DeductionDetails;
import shared.mapper.DeductionMapper;


public class DeductionService {
	private DBConfig dbConfig;
	private DeductionMapper deductionMapper;
	
	public DeductionService() {
		  this.dbConfig = new DBConfig();
		  this.deductionMapper=new DeductionMapper();
	}
	

	public void createDeductionDetails(DeductionDetails deductionDetails) {
		
        try {

            PreparedStatement ps = this.dbConfig.getConnection()
                    .prepareStatement("INSERT INTO deduction_details(tax, SSC, deduction_amount,description, emp_id) VALUES (?,?,?,?,?);");

            ps.setString(1, deductionDetails.getTax());
            ps.setString(2, deductionDetails.getSSC());
            ps.setDouble(3, deductionDetails.getDeduction_amount());
            ps.setString(4, deductionDetails.getDescription());
            ps.setInt(5, deductionDetails.getEmployee().getId());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
		
    }
	
//	public List<Deduction> findAllDeduction() {
//		List<Deduction> deductionList=new ArrayList<>();
//		try (Statement st = this.dbConfig.getConnection().createStatement()) {
//
//			String query = "SELECT * FROM deduction";
//
//			ResultSet rs = st.executeQuery(query);
//
//			while(rs.next()) {
//				Deduction deduction=new Deduction();
//				deductionList.add(this.deductionMapper.mapToDeduction(deduction, rs));
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return deductionList;
//	}
	
	
	public List<DeductionDetails> findAllDDetails() {

        List<DeductionDetails> dDetailsList = new ArrayList<>();

        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM deduction_details\n" +
                    "INNER JOIN employee\n" +
                    "ON employee.emp_id = deduction_details.emp_id;";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                DeductionDetails deductionDetails = new DeductionDetails();
                dDetailsList.add(this.deductionMapper.mapToDeductionDetails(deductionDetails, rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dDetailsList;
    }
	
}

	
