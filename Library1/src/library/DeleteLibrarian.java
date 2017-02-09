package library;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteLibrarian extends JFrame {

	private JPanel contentPane;
	
	private Connection connect = null;
	  private Statement statement = null;
	  //private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	  final private String host = "localhost:3306";
	  final private String user = "root";
	  final private String passwd = "harlieenbindra";

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteLibrarian frame = new DeleteLibrarian();
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
	public DeleteLibrarian() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setResizable(false);
		
		JLabel lblDeleteLibrarian = new JLabel("Delete Librarian");
		lblDeleteLibrarian.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDeleteLibrarian.setBounds(12, 13, 789, 99);
		contentPane.add(lblDeleteLibrarian);
		
		JPanel panel = new JPanel();
		panel.setBounds(211, 157, 390, 274);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID : ");
		lblNewLabel.setBounds(45, 39, 40, 32);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(110, 47, 268, 22);
		comboBox.setEditable(false);
		panel.add(comboBox);
		comboBox.setVisible(true);
		
			    try 
			    {
			      // This will load the MySQL driver, each DB has its own driver
			      Class.forName("com.mysql.jdbc.Driver");
			      
			      // Setup the connection with the DB
			      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/library_management?"+ "user=" + user + "&password=" + passwd );

			      // Statements allow to issue SQL queries to the database
			      statement = connect.createStatement();
			      // Result set get the result of the SQL query
			      resultSet = statement.executeQuery("select * from library_management.librarian where id IS NOT NULL");
			      System.out.println("Query Executed");
			      while (resultSet.next()) 
			      {
						String id_ = resultSet.getString("id");
						if (id_.equals("")) {
							comboBox.addItem("");
						} 
						else 
						{
							comboBox.addItem(resultSet.getString("id"));
							System.out.println(resultSet.getString("id"));
						}
			      }
			    }
						catch (Exception e) 
			    {	
			    	JOptionPane.showMessageDialog(null,"Cannot Retrieve ids.ERROR!");
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

			    
		
		 
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
			    {
			      // This will load the MySQL driver, each DB has its own driver
			      Class.forName("com.mysql.jdbc.Driver");
			      
			      // Setup the connection with the DB
			      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/library_management?"+ "user=" + user + "&password=" + passwd );

			      // Statements allow to issue SQL queries to the database
			      statement = connect.createStatement();
			      
			      String id=(String)comboBox.getSelectedItem();
			      
			      String query="delete from library_management.librarian where id=?";
			      PreparedStatement preparedStmt = connect.prepareStatement(query);
			      preparedStmt.setString(1,id);

			      // execute the preparedstatement
			      preparedStmt.execute();
			      
			      JOptionPane.showMessageDialog(null,"Librarian deleted.\nSUCCESS !");
			      System.out.println("Query Executed");
			   
			    }
						catch (Exception ex) 
			    {	
			    	JOptionPane.showMessageDialog(null,"Librarian not deleted.\nERROR !");
			    	System.out.println(ex);
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
				    	  catch (Exception ex) 
				    	  	{

						    }
			      }
				
			}
		});
		btnDelete.setBounds(138, 146, 119, 39);
		panel.add(btnDelete);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btnBack.setBounds(12, 633, 124, 37);
		contentPane.add(btnBack);
		
		
	}
}
