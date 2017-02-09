package library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class IssueBook extends JFrame {

	private JPanel contentPane;
	private JTextField bcallno;
	private JTextField sid;
	
	private Connection connect = null;
	  private Statement statement = null;
	  //private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet1 = null;
	  private ResultSet resultSet2 = null;


	  final private String host = "localhost:3306";
	  final private String user = "root";
	  final private String passwd = "harlieenbindra";
	  
	  boolean flag=false;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBook frame = new IssueBook();
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
	public IssueBook() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Issue Book");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 13, 789, 76);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(253, 126, 374, 405);
		contentPane.add(panel);
		
		JLabel lblBookCallno = new JLabel("Book CallNo. : ");
		lblBookCallno.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBookCallno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		bcallno = new JTextField();
		bcallno.setColumns(10);
		
		JLabel lblStudentId = new JLabel("Student ID : ");
		lblStudentId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		sid = new JTextField();
		sid.setColumns(10);
		
		JButton btnIssueBook = new JButton("Issue Book");
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      
				      // Setup the connection with the DB
				      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/library_management?"+ "user=" + user + "&password=" + passwd );

				      	String tid=sid.getText();
				      	System.out.println(tid);
				      	String tcallno = bcallno.getText();
				      	System.out.println(tcallno);
				      	
				      	statement = connect.createStatement();
				      	
				      	//String query1="select * from library_management.books where callno=\""+tcallno+"\"";
				      	//System.out.println(query1);
				      	resultSet1 = statement.executeQuery("select * from library_management.books where callno=\""+tcallno+"\"");
				      	
				      	while (resultSet1.next()) 
				      	{
					        String tquantity = resultSet1.getString("quantity");
					        int quan = Integer.parseInt(tquantity);
					        System.out.println(quan);
					        
					        String tissued = resultSet1.getString("issued");
					        int issd = Integer.parseInt(tissued);
					        System.out.println(issd);
					        
					        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
							LocalDate localDate = LocalDate.now();
							String tdateadded=dtf.format(localDate);
							
					        if(quan>0&&issd<quan)
					        {
					        	//String query2="select * from library_management.books_issued where bookcallno=\""+tcallno+"\"";
						      	resultSet2 = statement.executeQuery("select * from library_management.books_issued where bookcallno=\""+tcallno+"\"");
						      	
						      	while(resultSet2.next())
						      	{
									      	String sidbooksissued=resultSet2.getString("studentid");
									      	System.out.println(sidbooksissued);
									      	
									      	if(sidbooksissued.equals(tid))
									      	{
									      		flag=true;									      		
									      	}
						      	}
						      	
						      	if(flag)
						      	{
						      		JOptionPane.showMessageDialog(null,"Book already issued.\nERROR !");
						      		break;
						      	}
						      	else
						      	{
									   
											        issd++;
											        	
											        String updquery = "UPDATE `library_management`.`books` SET `issued`= ? WHERE `callno`= ?";
											        PreparedStatement preparedStmt = connect.prepareStatement(updquery);
											        preparedStmt.setInt(1, issd);
											        preparedStmt.setString(2, tcallno);
						
											        // execute the java preparedstatement
											        preparedStmt.executeUpdate();
											        
						
											     // the mysql insert statement
											        String query = "INSERT INTO `library_management`.`books_issued` (`bookcallno`, `studentid`, `issuedate`)"
											          + " values (?, ?, ?)";
						
											        
											        // create the mysql insert preparedstatement
											        PreparedStatement preparedStmt1 = connect.prepareStatement(query);
											        preparedStmt1.setString(1, tcallno);
											        preparedStmt1.setString(2, tid);
											        preparedStmt1.setString(3, tdateadded);
											        
											      // Result set get the result of the SQL query
											        preparedStmt1.execute();
											        
											      JOptionPane.showMessageDialog(null,"Book successfully issued.");
											      break;
						      	 	
						      	}
					        }
					        else
					        {
					        	JOptionPane.showMessageDialog(null,"No Book Left.\nERROR !");
					        	break;
					        }
 
				        }
				        
				      	
				    } catch (Exception exc) 
				    {	
				    	//JOptionPane.showMessageDialog(null,"Book not issued.\nERROR !");
				    	System.out.println(exc);
				     } 
				    finally 
				    {
					    	  try {
								        if (resultSet1 != null && resultSet2 != null) 
								        {
								          resultSet1.close();
								          resultSet2.close();
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
					    	  catch (Exception excp) 
					    	  	{

							    }
				      }

			}
				
			
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblBookCallno)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bcallno, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblStudentId, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(sid, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(130)
							.addComponent(btnIssueBook, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBookCallno)
						.addComponent(bcallno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStudentId, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(sid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(105)
					.addComponent(btnIssueBook, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(109, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btnBack.setBounds(12, 635, 141, 35);
		contentPane.add(btnBack);
	}

}
