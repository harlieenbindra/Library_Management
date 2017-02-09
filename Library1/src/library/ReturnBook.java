package library;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReturnBook extends JFrame {

	private JPanel contentPane;
	
	  private JTextField bcallno;
	  private JTextField studentid;

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
					ReturnBook frame = new ReturnBook();
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
	public ReturnBook() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblReturnBook = new JLabel("Return Book");
		lblReturnBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblReturnBook.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblReturnBook.setBounds(12, 13, 789, 81);
		contentPane.add(lblReturnBook);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(198, 144, 448, 380);
		contentPane.add(panel);
		
		JLabel lblBookCallno = new JLabel("Book CallNo : ");
		lblBookCallno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBookCallno.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblStudentId = new JLabel("Student ID : ");
		lblStudentId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		bcallno = new JTextField();
		bcallno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bcallno.setColumns(10);
		
		studentid = new JTextField();
		studentid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		studentid.setColumns(10);
		
		JButton rtnbook = new JButton("Return Book");
		rtnbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      
				      // Setup the connection with the DB
				      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/library_management?"+ "user=" + user + "&password=" + passwd );

				      	String tid=studentid.getText();
				      	System.out.println(tid);
				      	String tcallno = bcallno.getText();
				      	System.out.println(tcallno);
				      	
				      	statement = connect.createStatement();
				      	
				   
				      	resultSet1 = statement.executeQuery("select * from library_management.books where callno=\""+tcallno+"\"");
				      	
				      	while (resultSet1.next()) 
				      	{
					        String tquantity = resultSet1.getString("quantity");
					        int quan = Integer.parseInt(tquantity);
					        System.out.println(quan);
					        
					        String tissued = resultSet1.getString("issued");
					        int issd = Integer.parseInt(tissued);
					        System.out.println(issd);
					        /*
					        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
							LocalDate localDate = LocalDate.now();
							String tdateadded=dtf.format(localDate);
							*/
					        if(quan>0&&issd<quan)
					        {
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
						      		issd--;
						        	
							        String updquery = "UPDATE `library_management`.`books` SET `issued`= ? WHERE `callno`= ?";
							        PreparedStatement preparedStmt = connect.prepareStatement(updquery);
							        preparedStmt.setInt(1, issd);
							        preparedStmt.setString(2, tcallno);
		
							        // execute the java preparedstatement
							        preparedStmt.executeUpdate();
							        
		
							     // the mysql insert statement
							        String query = "DELETE FROM `library_management`.`books_issued`"
							          + " WHERE `bookcallno`=? AND `studentid`=?";
		
							        
							        // create the mysql insert preparedstatement
							        PreparedStatement preparedStmt1 = connect.prepareStatement(query);
							        preparedStmt1.setString(1, tcallno);
							        preparedStmt1.setString(2, tid);
							        
							      // Result set get the result of the SQL query
							        preparedStmt1.execute();
							        
							      JOptionPane.showMessageDialog(null,"Book successfully returned.");
							      break;
						      		
						      	}
						      	else
						      	{
						      		JOptionPane.showMessageDialog(null,"Wrong Student ID.\nERROR!");	 	
						      	}
					        }
					        else
					        {
					        	JOptionPane.showMessageDialog(null,"Wrong BookCallNo.\nERROR !");
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
		rtnbook.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblStudentId, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentid, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblBookCallno)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bcallno, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(181, Short.MAX_VALUE)
					.addComponent(rtnbook)
					.addGap(170))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBookCallno)
						.addComponent(bcallno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(75)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblStudentId, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(studentid, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
					.addComponent(rtnbook)
					.addGap(69))
		);
		panel.setLayout(gl_panel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(12, 637, 141, 33);
		contentPane.add(btnBack);
		
		
	}
}
