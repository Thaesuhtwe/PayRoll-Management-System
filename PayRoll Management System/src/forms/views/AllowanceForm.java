package forms.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import entities.AllowanceDetails;
import entities.DeductionDetails;
import entities.Employee;
import services.AllowanceService;
import services.DeductionService;
import services.EmployeeService;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AllowanceForm extends JInternalFrame {
	private JTextField txtEmpID;
	private JTextField txtSkills;
	private JTextField txtLong;
	private JTextField txtDescription;
	private JTextField txtaAmount;
	private AllowanceDetails allowanceDetails;
	private EmployeeService employeeService;
	private AllowanceService allowanceService;
	private Employee employee;
	private JTable tblAllowance;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<AllowanceDetails> originalAllowanceList = new ArrayList<>();
	private List<AllowanceDetails> aDetailslist=new ArrayList();
	private JTextField txtEmp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllowanceForm frame = new AllowanceForm();
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
	public AllowanceForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllowanceDetails(Optional.empty());
    	this.allowanceDetails = new AllowanceDetails();
	}
	
	public AllowanceForm(AllowanceDetails allowanceDetails) {
		this.allowanceDetails = allowanceDetails;
    	initialize();
        this.initializeDependency();
        this.setTableDesign();
        this.loadAllowanceDetails(Optional.empty());     
		
    }
	
	private void initializeDependency() {
        this.allowanceService = new AllowanceService();
        this.employeeService = new EmployeeService();
    }
	
	private void setTableDesign() {
       	dtm.addColumn("ID");
        dtm.addColumn("Skills");
        dtm.addColumn("Longevity");
        dtm.addColumn("Amount");
        this.tblAllowance.setModel(dtm);
  }
	
	private void resetFormData() {	
        txtSkills.setText("");
        txtLong.setText("");
        txtDescription.setText("");
        txtaAmount.setText("");
  }
 
 private void setAllowanceDetails(AllowanceDetails allowanceDetails) {
	
	 allowanceDetails.setSkills(txtSkills.getText());;
	 allowanceDetails.setLongevity(txtLong.getText());
	 allowanceDetails.setAllowance_Amount(Double.parseDouble(txtaAmount.getText()));
	 allowanceDetails.setDescription(txtDescription.getText());
	 allowanceDetails.setEmployee(employee);
 }
 

 
 private void loadAllowanceDetails(Optional<List<AllowanceDetails>> optionalAllowanceDetails) {
    	this.dtm = (DefaultTableModel) this.tblAllowance.getModel();
    	this.dtm.getDataVector().removeAllElements();
    	this.dtm.fireTableDataChanged();
    	this.originalAllowanceList = this.allowanceService.findAllADetails();
    		List<AllowanceDetails> aDetailsList = optionalAllowanceDetails.orElseGet(() ->  originalAllowanceList);
    		aDetailsList.forEach(a -> {
    			Object[] row = new Object[7];
    				row[0] = a.getAdId();
    				row[1] = a.getSkills();
    				row[2] = a.getLongevity();
    				row[3] = a.getAllowance_Amount();
    				row[4] = a.getDescription();
    				dtm.addRow(row);
    			});
    		this.tblAllowance.setModel(dtm);
    }
 
	
	public void initialize() {
		getContentPane().setBackground(Color.WHITE);
		
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		
		setBounds(0, 0, 976, 591);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Allowance", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(64, 54, 405, 445);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emp ID");
		lblNewLabel.setBounds(37, 37, 46, 14);
		panel.add(lblNewLabel);
		
		txtEmpID = new JTextField();
		txtEmpID.setText("");
		txtEmpID.setBounds(173, 34, 86, 20);
		panel.add(txtEmpID);
		txtEmpID.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<AllowanceDetails> aList = new ArrayList<>();
				aList= allowanceService.findAllADetails();
				String id = txtEmpID.getText();
				employee = new Employee();
				employee = employeeService.findEmployeeById(id);
				txtEmp.setText(employee.getName());
			}
		});
		btnSearch.setBounds(269, 33, 89, 23);
		panel.add(btnSearch);
		
		JLabel lblNewLabel_1 = new JLabel("Skills");
		lblNewLabel_1.setBounds(37, 145, 46, 14);
		panel.add(lblNewLabel_1);
		
		txtSkills = new JTextField();
		txtSkills.setBounds(173, 142, 185, 20);
		panel.add(txtSkills);
		txtSkills.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Longetivity");
		lblNewLabel_2.setBounds(37, 198, 60, 14);
		panel.add(lblNewLabel_2);
		
		txtLong = new JTextField();
		txtLong.setBounds(173, 195, 185, 20);
		panel.add(txtLong);
		txtLong.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Emp name");
		lblNewLabel_3.setBounds(37, 95, 60, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Description");
		lblNewLabel_4.setBounds(37, 244, 60, 20);
		panel.add(lblNewLabel_4);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(173, 244, 185, 20);
		panel.add(txtDescription);
		txtDescription.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllowanceDetails allowanceDetails = new AllowanceDetails();
				setAllowanceDetails(allowanceDetails);
				
				if(!txtSkills.getText().isEmpty()&&!txtLong.getText().isEmpty()&&!txtaAmount.getText().isEmpty()) {
					allowanceService.createAllowanceDetails(allowanceDetails);
					loadAllowanceDetails(Optional.empty());
					resetFormData();
				}
			}
		});
		btnRegister.setBounds(37, 358, 89, 39);
		panel.add(btnRegister);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setBounds(269, 358, 89, 39);
		panel.add(btnClear);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCalculate.setBounds(153, 358, 89, 39);
		panel.add(btnCalculate);
		
		JLabel lblNewLabel_5 = new JLabel("Amount");
		lblNewLabel_5.setBounds(37, 308, 46, 14);
		panel.add(lblNewLabel_5);
		
		txtaAmount = new JTextField();
		txtaAmount.setBounds(173, 305, 185, 20);
		panel.add(txtaAmount);
		txtaAmount.setColumns(10);
		
		txtEmp = new JTextField();
		txtEmp.setEditable(false);
		txtEmp.setBounds(173, 92, 185, 20);
		panel.add(txtEmp);
		txtEmp.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(510, 101, 412, 413);
        this.getContentPane().add(scrollPane);

        tblAllowance = new JTable();
        tblAllowance.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scrollPane.setViewportView(tblAllowance);
	}
}
