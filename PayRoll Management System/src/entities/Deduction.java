package entities;

public class Deduction {
	private int deduction_id;
	
	private String description;
	
	public Deduction() {
		
	}

	public Deduction(int deduction_id, String description) {
		super();
		this.deduction_id = deduction_id;
		this.description = description;
	}

	public int getDeduction_id() {
		return deduction_id;
	}

	public void setDeduction_id(int deduction_id) {
		this.deduction_id = deduction_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
