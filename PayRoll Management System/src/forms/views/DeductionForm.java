package forms.views;
import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import entities.Deduction;
import entities.Employee;
import services.EmployeeService;
import entities.DeductionDetails;
import services.DeductionService;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DeductionForm extends JInternalFrame {
	
	private JTextField txtTax;
	private JTextField txtSearch;
	private JTextField txtSSC;
	private JTextField txtdAmount;
	private DeductionService deductionService;
	private EmployeeService employeeService;
	private JTable tblDeduction;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<DeductionDetails> originalDeductionList = new ArrayList<>();
	private List<DeductionDetails> dDetailslist=new ArrayList();
	private JTextField txtdSearch;
	private DeductionDetails deductionDetails;
    private List<Deduction> deductionList;
    private List<Employee> employeeList;
    private JTextField txtDescription;
    private Deduction deduction;
    private Employee employee;
    private JTextField txtEmpName;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeductionForm frame = new DeductionForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public DeductionForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllDeductionDetails(Optional.empty());
    	this.deductionDetails = new DeductionDetails();

	}
	
	public DeductionForm(DeductionDetails deductionDetails) {
    	this.deductionDetails = deductionDetails;
    	initialize();
        this.initializeDependency();
        this.setTableDesign();
        this.loadAllDeductionDetails(Optional.empty());     
    }
	
	private void initializeDependency() {
        this.deductionService = new DeductionService();
        this.employeeService = new EmployeeService();

    }
	
	 private void setTableDesign() {
	       	dtm.addColumn("ID");
	        dtm.addColumn("Tax");
	        dtm.addColumn("SSC");
	        dtm.addColumn("Amount");
	        this.tblDeduction.setModel(dtm);
	  }
	 
	 private void resetFormData() {	
	        txtTax.setText("");
	        txtSSC.setText("");
	        txtdAmount.setText("");
	        txtDescription.setText("");
	  }
	 
	 private void setDeductionDetails(DeductionDetails deductionDetails) {
		
		 deductionDetails.setTax(txtTax.getText());
		 deductionDetails.setSSC(txtSSC.getText());
		 deductionDetails.setDeduction_amount(Double.parseDouble(txtdAmount.getText()));
		 deductionDetails.setDescription(txtDescription.getText());
		 deductionDetails.setEmployee(employee);
	 }
	 

	 
	 private void loadAllDeductionDetails(Optional<List<DeductionDetails>> optionalDeductionDetails) {
	    	this.dtm = (DefaultTableModel) this.tblDeduction.getModel();
	    	this.dtm.getDataVector().removeAllElements();
	    	this.dtm.fireTableDataChanged();
	    	this.originalDeductionList = this.deductionService.findAllDDetails();
	    		List<DeductionDetails> dDetailsList = optionalDeductionDetails.orElseGet(() ->  originalDeductionList);
	    		dDetailsList.forEach(d -> {
	    			Object[] row = new Object[7];
	    				row[0] = d.getDeduction_details_id();
	    				row[1] = d.getTax();
	    				row[2] = d.getSSC();
	    				row[3] = d.getDeduction_amount();
	    				row[4] = d.getDescription();
	    				dtm.addRow(row);
	    			});
	    		this.tblDeduction.setModel(dtm);
	    }
	 
	public void initialize() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Deduction", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(38, 47, 421, 467);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tax");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(45, 170, 46, 14);
		panel.add(lblNewLabel);
		
		txtTax = new JTextField();
		txtTax.setBounds(193, 167, 169, 20);
		panel.add(txtTax);
		txtTax.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Emp ID");
		lblNewLabel_1.setBounds(45, 57, 46, 14);
		panel.add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(193, 54, 75, 20);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearchEmpID = new JButton("Search");
		btnSearchEmpID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<DeductionDetails> dList = new ArrayList<>();
				dList= deductionService.findAllDDetails();
				
				String id = txtSearch.getText();
				employee = new Employee();
				employee = employeeService.findEmployeeById(id);
				txtEmpName.setText(employee.getName());
				
			}
		});
		btnSearchEmpID.setBounds(288, 53, 75, 23);
		panel.add(btnSearchEmpID);
		
		JLabel lblNewLabel_2 = new JLabel("SSC");
		lblNewLabel_2.setBounds(45, 222, 58, 14);
		panel.add(lblNewLabel_2);
		
		txtSSC = new JTextField();
		txtSSC.setColumns(10);
		txtSSC.setBounds(193, 219, 169, 20);
		panel.add(txtSSC);
		
		JLabel lblNewLabel_3 = new JLabel("Amount");
		lblNewLabel_3.setBounds(45, 345, 100, 14);
		panel.add(lblNewLabel_3);
		
		txtdAmount = new JTextField();
		txtdAmount.setColumns(10);
		txtdAmount.setBounds(193, 342, 169, 20);
		panel.add(txtdAmount);
		
		JButton btnSave = new JButton("Register");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeductionDetails deductionDetails = new DeductionDetails();
				setDeductionDetails(deductionDetails);
				
				if(!txtTax.getText().isEmpty() && !txtSSC.getText().isEmpty() && !txtdAmount.getText().isEmpty()) {
					deductionService.createDeductionDetails(deductionDetails);
					loadAllDeductionDetails(Optional.empty());
					resetFormData();
				}
			}
		});
		btnSave.setBounds(45, 399, 82, 42);
		panel.add(btnSave);
		
		JButton btnUpdate = new JButton("Calculate");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(169, 399, 82, 42);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Clear");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(288, 399, 74, 42);
		panel.add(btnDelete);
		
		JLabel lblNewLabel_4 = new JLabel("Emp Name");
		lblNewLabel_4.setBounds(44, 110, 59, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Description");
		lblNewLabel_5.setBounds(45, 282, 75, 14);
		panel.add(lblNewLabel_5);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(193, 279, 169, 20);
		panel.add(txtDescription);
		txtDescription.setColumns(10);
		
		txtEmpName = new JTextField();
		txtEmpName.setEditable(false);
		txtEmpName.setColumns(10);
		txtEmpName.setBounds(193, 107, 169, 20);
		panel.add(txtEmpName);
		
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		
		setBounds(0, 0, 976, 591);
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(510, 101, 412, 413);
        this.getContentPane().add(scrollPane);

        tblDeduction = new JTable();
        tblDeduction.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scrollPane.setViewportView(tblDeduction);
        
        
        
        txtdSearch = new JTextField();
        txtdSearch.setBounds(680, 59, 136, 20);
        getContentPane().add(txtdSearch);
        txtdSearch.setColumns(10);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(833, 58, 89, 23);
        getContentPane().add(btnSearch);

	}
}
