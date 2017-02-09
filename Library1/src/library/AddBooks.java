package library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class AddBooks extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField callno;
	private JTextField name;
	private JTextField author;
	private JTextField publisher;
	private JTextField quantity;
	private JTextField issued;
	private JTextField dateadded;

	private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
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
					AddBooks frame = new AddBooks();
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
	public AddBooks() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblAddBooks = new JLabel("Add Book(s)");
		lblAddBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddBooks.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAddBooks.setBounds(12, 13, 789, 71);
		contentPane.add(lblAddBooks);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btnBack.setBounds(12, 625, 126, 45);
		contentPane.add(btnBack);
		
		JButton btnAddBooks = new JButton("Add Book(s)");
		btnAddBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
				      // This will load the MySQL driver, each DB has its own driver
				      Class.forName("com.mysql.jdbc.Driver");
				      
				      // Setup the connection with the DB
				      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/library_management?"+ "user=" + user + "&password=" + passwd );

				      	String tid=id.getText();
				      	String tcallno = callno.getText();
				        String tname = name.getText();				        
				        String tauthor = author.getText();
				        String tpublisher = publisher.getText();
				        String tquantity = quantity.getText();
				        String tissued = issued.getText();
				        String tdateadded = dateadded.getText();
				        
				     // the mysql insert statement
				        String query = " INSERT INTO library_management.books(`id`,`callno`, `name`, `author`, `publisher`, `quantity`, `issued`, `dateadded`)"
				          + " values (?, ?, ?, ?, ?, ?, ?, ?)";

				        // create the mysql insert preparedstatement
				        PreparedStatement preparedStmt = connect.prepareStatement(query);
				        preparedStmt.setString(1, tid);
				        preparedStmt.setString(2, tcallno);
				        preparedStmt.setString(3, tname);
				        preparedStmt.setString(4, tauthor);
				        preparedStmt.setString(5, tpublisher);
				        preparedStmt.setString(6, tquantity);
				        preparedStmt.setString(7, tissued);
				        preparedStmt.setString(8, tdateadded);
				        
				      // Result set get the result of the SQL query
				        preparedStmt.execute();
				      JOptionPane.showMessageDialog(null,"Book successfully added.");
				    } catch (Exception exc) 
				    {	
				    	JOptionPane.showMessageDialog(null,"Book not added.\nERROR !");
				    	System.out.println(exc);
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
					    	  catch (Exception exc) 
					    	  	{

							    }
				      }

			}
		});
		btnAddBooks.setBounds(331, 625, 146, 45);
		contentPane.add(btnAddBooks);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 83, 789, 527);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblBookId = new JLabel("Book ID : ");
		lblBookId.setBounds(12, 28, 193, 38);
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBookId.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblBookId);
		
		id = new JTextField();
		id.setBounds(217, 31, 572, 38);
		panel.add(id);
		id.setColumns(10);
		
		JLabel lblBookCallno = new JLabel("Book CallNo : ");
		lblBookCallno.setBounds(8, 93, 197, 38);
		lblBookCallno.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBookCallno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblBookCallno);
		
		callno = new JTextField();
		callno.setBounds(217, 96, 572, 38);
		panel.add(callno);
		callno.setColumns(10);
		
		JLabel lblBookName = new JLabel("Book Name : ");
		lblBookName.setBounds(8, 155, 197, 38);
		lblBookName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblBookName);
		
		name = new JTextField();
		name.setBounds(217, 158, 572, 38);
		panel.add(name);
		name.setColumns(10);
		
		JLabel lblBookAuthor = new JLabel("Book Author : ");
		lblBookAuthor.setBounds(8, 217, 197, 38);
		lblBookAuthor.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBookAuthor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblBookAuthor);
		
		author = new JTextField();
		author.setBounds(217, 220, 572, 38);
		panel.add(author);
		author.setColumns(10);
		
		JLabel lblBookPublisher = new JLabel("Book Publisher : ");
		lblBookPublisher.setBounds(8, 279, 197, 38);
		lblBookPublisher.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBookPublisher.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblBookPublisher);
		
		publisher = new JTextField();
		publisher.setBounds(217, 282, 572, 38);
		panel.add(publisher);
		publisher.setColumns(10);
		
		JLabel lblBookQuantity = new JLabel("Book Quantity : ");
		lblBookQuantity.setBounds(8, 341, 197, 38);
		lblBookQuantity.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBookQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblBookQuantity);
		
		quantity = new JTextField();
		quantity.setBounds(217, 344, 572, 38);
		panel.add(quantity);
		quantity.setColumns(10);
		
		JLabel lblBookIssued = new JLabel("Books Issued : ");
		lblBookIssued.setBounds(8, 403, 197, 38);
		lblBookIssued.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBookIssued.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblBookIssued);
		
		issued = new JTextField();
		issued.setBounds(217, 406, 572, 38);
		issued.setText("0");
		panel.add(issued);
		issued.setColumns(10);
		
		JLabel lblBookAddeddate = new JLabel("Book Added-Date : ");
		lblBookAddeddate.setBounds(12, 465, 193, 38);
		lblBookAddeddate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBookAddeddate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblBookAddeddate);
		
		dateadded = new JTextField();
		dateadded.setColumns(10);
		dateadded.setBounds(217, 468, 572, 38);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		dateadded.setText(dtf.format(localDate));
		panel.add(dateadded);
	}
}
