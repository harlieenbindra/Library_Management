package library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddLibrarian extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField name;
	private JTextField password;
	private JTextField email;
	private JTextField address;
	private JTextField city;
	private JTextField contactno;
	
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
					AddLibrarian frame = new AddLibrarian();
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
	public AddLibrarian() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		
		JLabel lblAddLibrarian = new JLabel("Add Librarian");
		lblAddLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAddLibrarian.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		
		JButton btnAddLibrarian = new JButton("Add Librarian");
		btnAddLibrarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
				  
					    try {
					      // This will load the MySQL driver, each DB has its own driver
					      Class.forName("com.mysql.jdbc.Driver");
					      
					      // Setup the connection with the DB
					      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/library_management?"+ "user=" + user + "&password=" + passwd );

					      	String tid=id.getText();
					        String tname = name.getText();
					        String tpassword = password.getText();
					        String temail = email.getText();
					        String taddress = address.getText();
					        String tcity = city.getText();
					        String tcontactno = contactno.getText();
					        
					     // the mysql insert statement
					        String query = " INSERT INTO library_management.librarian(`id`, `name`, `password`, `email`, `address`, `city`, `contactno`)"
					          + " values (?, ?, ?, ?, ?, ?, ?)";

					        // create the mysql insert preparedstatement
					        PreparedStatement preparedStmt = connect.prepareStatement(query);
					        preparedStmt.setString(1, tid);
					        preparedStmt.setString(2, tname);
					        preparedStmt.setString(3, tpassword);
					        preparedStmt.setString(4, temail);
					        preparedStmt.setString(5, taddress);
					        preparedStmt.setString(6, tcity);
					        preparedStmt.setString(7, tcontactno);
					        
					      // Result set get the result of the SQL query
					        preparedStmt.execute();
					      JOptionPane.showMessageDialog(null,"Librarian successfully added.");
					    } catch (Exception e) 
					    {	
					    	JOptionPane.showMessageDialog(null,"Librarian not added.\nERROR !");
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
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblAddLibrarian, GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(218)
							.addComponent(btnAddLibrarian, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(90, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 631, GroupLayout.PREFERRED_SIZE)
					.addGap(82))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAddLibrarian, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(73)
							.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addComponent(btnAddLibrarian, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lnlID = new JLabel("ID : ");
		lnlID.setHorizontalAlignment(SwingConstants.TRAILING);
		lnlID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lnlID, "4, 4");
		
		id = new JTextField();
		panel.add(id, "5, 4, 40, 2");
		id.setColumns(10);
		
		JLabel lblName = new JLabel("Name : ");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblName, "4, 8");
		
		name = new JTextField();
		name.setColumns(10);
		panel.add(name, "5, 8, 40, 2");
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblPassword, "4, 12");
		
		password = new JTextField();
		password.setColumns(10);
		panel.add(password,"5, 12, 40, 2");
		
		JLabel lblEmail = new JLabel("E-Mail : ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblEmail, "4, 16");
		
		email = new JTextField();
		email.setColumns(10);
		panel.add(email, "5, 16, 40, 2, fill, default");
		
		JLabel lblAddress = new JLabel("Address : ");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblAddress, "4, 20");
		
		address = new JTextField();
		address.setColumns(10);
		panel.add(address, "5, 20, 40, 2, fill, default");
		
		JLabel lblCity = new JLabel("City : ");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCity.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblCity, "4, 24");
		
		city = new JTextField();
		city.setColumns(10);
		panel.add(city, "5, 24, 40, 2");
		
		JLabel lblContactNo = new JLabel("Contact No. : ");
		lblContactNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContactNo.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblContactNo, "4, 28");
		
		contactno = new JTextField();
		contactno.setColumns(10);
		panel.add(contactno,"5, 28, 40, 2");
		contentPane.setLayout(gl_contentPane);
	}
}
