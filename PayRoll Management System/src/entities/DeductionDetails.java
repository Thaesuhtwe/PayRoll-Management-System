package entities;

import java.util.Objects;

public class DeductionDetails {
	private int deduction_details_id;
	
	private String tax;
	
	private String SSC;
	
	private String description;
	
	private double deduction_amount;
	
	private Employee employee;
	
	public DeductionDetails() {
		super();
	}

	public DeductionDetails(int deduction_details_id, String tax, String sSC, String description,
			double deduction_amount, Employee employee) {
		super();
		this.deduction_details_id = deduction_details_id;
		this.tax = tax;
		SSC = sSC;
		this.description = description;
		this.deduction_amount = deduction_amount;
		this.employee = employee;
	}

	public int getDeduction_details_id() {
		return deduction_details_id;
	}

	public void setDeduction_details_id(int deduction_details_id) {
		this.deduction_details_id = deduction_details_id;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getSSC() {
		return SSC;
	}

	public void setSSC(String sSC) {
		SSC = sSC;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDeduction_amount() {
		return deduction_amount;
	}

	public void setDeduction_amount(double deduction_amount) {
		this.deduction_amount = deduction_amount;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		return Objects.hash(SSC, deduction_amount, deduction_details_id, description, employee, tax);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeductionDetails other = (DeductionDetails) obj;
		return Objects.equals(SSC, other.SSC)
				&& Double.doubleToLongBits(deduction_amount) == Double.doubleToLongBits(other.deduction_amount)
				&& deduction_details_id == other.deduction_details_id && Objects.equals(description, other.description)
				&& Objects.equals(employee, other.employee) && Objects.equals(tax, other.tax);
	}

	
}
