package library;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainLogin1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField1,stu_name;
	private JPasswordField passwordField1,stu_password;
	private JTextField lib_name;
	private JPasswordField lib_password;

	private Connection connect = null;
	  private Statement statement = null;
	  //private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	  final private String host = "localhost:3306";
	  final private String user = "root";
	  final private String passwd = "harlieenbindra";
	  
	  JTable table;
	  boolean flag=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainLogin1 frame = new MainLogin1();
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
	public MainLogin1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setResizable(false);
		
		setTitle("Main Login");
        JTabbedPane jtp = new JTabbedPane();
        jtp.setFont(new Font("Tahoma", Font.PLAIN, 20));
        getContentPane().add(jtp);
        
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        
        //Tab Admin
        
        JLabel lblNewLabel1 = new JLabel("Admin Login");
        lblNewLabel1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnForgotPassword1 = new JButton("Forgot Password ?");
		
		//Librarian Tab
		
		JLabel lblNewLabel2 = new JLabel("Librarian Login");
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		 
        JLabel lblStudentId2 = new JLabel("Librarian Name : ");
        lblStudentId2.setHorizontalAlignment(SwingConstants.TRAILING);
        lblStudentId2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        JLabel lblPassword2 = new JLabel("Password : ");
        lblPassword2.setHorizontalAlignment(SwingConstants.TRAILING);
        lblPassword2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        lib_name = new JTextField();
        lib_name.setColumns(10);
        
        lib_password = new JPasswordField();
        
		
		JButton btnLogin2 = new JButton("Login");
		btnLogin2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
			{
			try {
			      // This will load the MySQL driver, each DB has its own driver
			      Class.forName("com.mysql.jdbc.Driver");
			      
			      // Setup the connection with the DB
			      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/library_management?"+ "user=" + user + "&password=" + passwd );

			      	
			        String tname = lib_name.getText();
			        char[] pass = lib_password.getPassword();
			        String tpassword=new String(pass);
			       
			     // Statements allow to issue SQL queries to the database
				      statement = connect.createStatement();
				      // Result set get the result of the SQL query
				      resultSet = statement.executeQuery("select * from library_management.librarian");
				      
				      while (resultSet.next()) 
				      {
				    	  String lp=resultSet.getString("password");
				    	  String ln=resultSet.getString("name");
				    	  if (tpassword.equals(lp) && tname.equals(ln)) 
				                //if(true)
				        		{
				    		  		flag=true;
				                	//JOptionPane.showMessageDialog(null,"Password is correct");
				                	EventQueue.invokeLater(new Runnable() {
				            			public void run() {
				            				try {
				            					LibrarianSection frame = new LibrarianSection();
				            					frame.setVisible(true);
				            				} catch (Exception e) {
				            					e.printStackTrace();
				            				}
				            			}
				            		});
				                	break;
				                } 
  
				      }
				      if(flag==false)
				      {
				    	  JOptionPane.showMessageDialog(null,"Username or Password is incorrect","Error message", JOptionPane.ERROR_MESSAGE);
				      }
				      lib_name.setText("");
				      lib_password.setText("");
				      
			    } catch (Exception e) 
			    {	
			    	JOptionPane.showMessageDialog(null,"Cannot connect to Database.\nERROR !");
			    	System.out.println(e);
			     } 
			    finally 
			    {
				    	  try {
							        if (resultSet != null) 
							        {
							          resultSet.close();
							        }

							        if (statement != null) 
							        {
							          statement.close();
							        }

							        if (connect != null) 
							        {
							          connect.close();
							        }
						      } 
				    	  catch (Exception e) 
				    	  	{

						    }
			      }
	 
			}
		});
		
		JButton btnForgotPassword2 = new JButton("Forgot Password ?");
		
		//Student Tab
		
		JLabel lblNewLabel3 = new JLabel("Student Login");
		lblNewLabel3.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnLogin3 = new JButton("Login");
		btnLogin3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      
				      // Setup the connection with the DB
				      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/library_management?"+ "user=" + user + "&password=" + passwd );

				      	
				        String tname = stu_name.getText();
				        char[] pass = stu_password.getPassword();
				        String tpassword=new String(pass);
				       
				     // Statements allow to issue SQL queries to the database
					      statement = connect.createStatement();
					      // Result set get the result of the SQL query
					      resultSet = statement.executeQuery("select * from library_management.student_details where stu_id="+tpassword);
					      System.out.println(" student query executed");
					      
					      while (resultSet.next()) 
					      {
					    	  String ln=resultSet.getString("stu_name");
					    	  System.out.println(ln);
					    	  String lp=resultSet.getString("stu_id");
					    	  
					    	  if (tpassword.equals(lp) && tname.equals(ln)) 
					                //if(true)
					        		{
					    		  		flag=true;
					                	//JOptionPane.showMessageDialog(null,"Password is correct");
					                	EventQueue.invokeLater(new Runnable() {
					            			public void run() {
					            				try {
					            					StudentSection frame = new StudentSection(lp);
					            					frame.readDataBase();
					            					frame.setVisible(true);
					            				} catch (Exception e) {
					            					e.printStackTrace();
					            				}
					            			}
					            		});
					                	break;
					                } 
	  
					      }
					      if(flag==false)
					      {
					    	  JOptionPane.showMessageDialog(null,"Username or Password is incorrect","Error message", JOptionPane.ERROR_MESSAGE);
					      }
					      stu_name.setText("");
					      stu_password.setText("");
					      
				    } catch (Exception s) 
				    {	
				    	JOptionPane.showMessageDialog(null,"Cannot connect to Database.\nERROR !");
				    	System.out.println(s);
				     } 
				    finally 
				    {
					    	  try {
								        if (resultSet != null) 
								        {
								          resultSet.close();
								        }

								        if (statement != null) 
								        {
								          statement.close();
								        }

								        if (connect != null) 
								        {
								          connect.close();
								        }
							      } 
					    	  catch (Exception s) 
					    	  	{

							    }
				      }
		 
				    
			}		
		});
		
		JButton btnForgotPassword3 = new JButton("Forgot Password ?");

        jtp.addTab("Admin", jp1);
        
        JPanel panel = new JPanel();
        
        JLabel lblStudentId1 = new JLabel("Admin ID : ");
        lblStudentId1.setHorizontalAlignment(SwingConstants.CENTER);
        lblStudentId1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        textField1 = new JTextField();
        textField1.setColumns(10);
        String txt2="admin";
        
        JLabel lblPassword1 = new JLabel("Password : ");
        lblPassword1.setHorizontalAlignment(SwingConstants.TRAILING);
        lblPassword1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        passwordField1 = new JPasswordField();
        String match="admin";
        /*
        char[] password = passwordField1.getPassword();
        char[] correctPass = new char[] {'a', 'd', 'm', 'i', 'n'};
         */
        
        JButton btnLogin1 = new JButton("Login");
        btnLogin1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		if (passwordField1.getText().equals(match) && textField1.getText().equals(txt2)) 
                //if(true)
        		{
                	//JOptionPane.showMessageDialog(null,"Password is correct");
                	EventQueue.invokeLater(new Runnable() {
            			public void run() {
            				try {
            					AdminSection frame = new AdminSection();
            					frame.setVisible(true);
            				} catch (Exception e) {
            					e.printStackTrace();
            				}
            			}
            		});
                	
                } else {
                	JOptionPane.showMessageDialog(null,"Password is incorrect","Error message", JOptionPane.ERROR_MESSAGE);
                }
        		
        		passwordField1.setText("");
        		textField1.setText("");
        	}
        });
        
        GroupLayout gl_jp1 = new GroupLayout(jp1);
        
        gl_jp1.setHorizontalGroup(
        	gl_jp1.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_jp1.createSequentialGroup()
        			.addGap(53)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 695, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(50, Short.MAX_VALUE))
        		.addGroup(gl_jp1.createSequentialGroup()
        			.addGap(324)
        			.addGroup(gl_jp1.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(btnLogin1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnForgotPassword1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
        			.addGap(286))
        		.addGroup(gl_jp1.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel1, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_jp1.setVerticalGroup(
        	gl_jp1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jp1.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
        			.addGap(52)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(42)
        			.addComponent(btnLogin1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
        			.addGap(30)
        			.addComponent(btnForgotPassword1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(145, Short.MAX_VALUE))
        );
        
       
        
        GroupLayout gl_panel = new GroupLayout(panel);
        
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(8)
        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lblStudentId1)
        				.addComponent(lblPassword1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
        			.addGap(32)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(passwordField1)
        				.addComponent(textField1, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(27)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblStudentId1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblPassword1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
        				.addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        panel.setLayout(gl_panel);
        jp1.setLayout(gl_jp1);
        jtp.addTab("Librarian", jp2);
        
        JPanel panel_1 = new JPanel();
       
        
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        
        gl_panel_1.setHorizontalGroup(
        	gl_panel_1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_1.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(lblStudentId2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(lblPassword2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lib_name, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        				.addComponent(lib_password, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
        			.addGap(32))
        );
        gl_panel_1.setVerticalGroup(
        	gl_panel_1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_1.createSequentialGroup()
        			.addGap(26)
        			.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblStudentId2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lib_name, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblPassword2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lib_password, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(44, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);
        GroupLayout gl_jp2 = new GroupLayout(jp2);
        gl_jp2.setHorizontalGroup(
        	gl_jp2.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jp2.createSequentialGroup()
        			.addGroup(gl_jp2.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jp2.createSequentialGroup()
        					.addGap(12)
        					.addComponent(lblNewLabel2, GroupLayout.PREFERRED_SIZE, 784, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_jp2.createSequentialGroup()
        					.addGap(54)
        					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_jp2.createSequentialGroup()
        					.addGap(310)
        					.addComponent(btnLogin2, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_jp2.createSequentialGroup()
        					.addGap(310)
        					.addComponent(btnForgotPassword2, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(2, Short.MAX_VALUE))
        );
        gl_jp2.setVerticalGroup(
        	gl_jp2.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jp2.createSequentialGroup()
        			.addGap(9)
        			.addComponent(lblNewLabel2, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
        			.addGap(45)
        			.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
        			.addGap(26)
        			.addComponent(btnLogin2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
        			.addGap(39)
        			.addComponent(btnForgotPassword2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
        			.addGap(161))
        );
        
        jp2.setLayout(gl_jp2);
        jtp.addTab("Student" , jp3);
        
        JPanel panel_2 = new JPanel();
        GroupLayout gl_jp3 = new GroupLayout(jp3);
        
        gl_jp3.setHorizontalGroup(
        	gl_jp3.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_jp3.createSequentialGroup()
        			.addGap(41)
        			.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 705, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(52, Short.MAX_VALUE))
        		.addGroup(gl_jp3.createSequentialGroup()
        			.addContainerGap(322, Short.MAX_VALUE)
        			.addComponent(btnLogin3, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
        			.addGap(318))
        		.addGroup(gl_jp3.createSequentialGroup()
        			.addContainerGap(292, Short.MAX_VALUE)
        			.addComponent(btnForgotPassword3, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
        			.addGap(268))
        		.addGroup(gl_jp3.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel3, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_jp3.setVerticalGroup(
        	gl_jp3.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jp3.createSequentialGroup()
        			.addGap(9)
        			.addComponent(lblNewLabel3, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
        			.addGap(32)
        			.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
        			.addGap(37)
        			.addComponent(btnLogin3, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
        			.addGap(44)
        			.addComponent(btnForgotPassword3, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(162, Short.MAX_VALUE))
        );
        
        JLabel lblStudentId3 = new JLabel("Student Name : ");
        lblStudentId3.setHorizontalAlignment(SwingConstants.TRAILING);
        lblStudentId3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        JLabel lblPassword3 = new JLabel("Password : ");
        lblPassword3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword3.setHorizontalAlignment(SwingConstants.TRAILING);
        
        stu_name = new JTextField();
        stu_name.setColumns(10);
        
        stu_password = new JPasswordField();
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
        	gl_panel_2.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_2.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lblPassword3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(lblStudentId3, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
        				.addComponent(stu_name, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE)
        				.addComponent(stu_password, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE))
        			.addGap(44))
        );
        gl_panel_2.setVerticalGroup(
        	gl_panel_2.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_2.createSequentialGroup()
        			.addGap(19)
        			.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblStudentId3, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
        				.addComponent(stu_name, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblPassword3, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        				.addComponent(stu_password, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
        			.addGap(25))
        );
        panel_2.setLayout(gl_panel_2);
        jp3.setLayout(gl_jp3);
	}
}
