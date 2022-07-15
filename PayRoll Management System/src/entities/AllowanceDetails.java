package entities;

import java.util.Objects;

public class AllowanceDetails {

	private int adId;
	
	private Employee employee;
	
	private String skills;
	
	private double allowance_Amount;
	
	private String longevity;
	
	private String description;
	
	public AllowanceDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AllowanceDetails(int adId, Employee employee, String skills, double allowance_Amount, String longevity,
			String description) {
		super();
		this.adId = adId;
		this.employee = employee;
		this.skills = skills;
		this.allowance_Amount = allowance_Amount;
		this.longevity = longevity;
		this.description = description;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public double getAllowance_Amount() {
		return allowance_Amount;
	}

	public void setAllowance_Amount(double allowance_Amount) {
		this.allowance_Amount = allowance_Amount;
	}

	public String getLongevity() {
		return longevity;
	}

	public void setLongevity(String longevity) {
		this.longevity = longevity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adId, allowance_Amount, description, employee, longevity, skills);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllowanceDetails other = (AllowanceDetails) obj;
		return adId == other.adId
				&& Double.doubleToLongBits(allowance_Amount) == Double.doubleToLongBits(other.allowance_Amount)
				&& Objects.equals(description, other.description) && Objects.equals(employee, other.employee)
				&& Objects.equals(longevity, other.longevity) && Objects.equals(skills, other.skills);
	}
	
	
	
}
