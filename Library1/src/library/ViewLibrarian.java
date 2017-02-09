package library;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewLibrarian extends JFrame {

	private JPanel contentPane;
	
	  private Connection connect = null;
	  private Statement statement = null;
	  private ResultSet resultSet = null;

	  final private String host = "localhost:3306";
	  final private String user = "root";
	  final private String passwd = "harlieenbindra";
	  
	  JTable table;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLibrarian frame = new ViewLibrarian();
					frame.setVisible(true);
					frame.readDataBase();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewLibrarian() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	  public void readDataBase() throws Exception {
		    try {
		      // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      
		      // Setup the connection with the DB
		      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/library_management?"+ "user=" + user + "&password=" + passwd );

		      // Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement.executeQuery("select * from library_management.librarian");
		      writeResultSet(resultSet);
		      
		    } catch (Exception e) {
		        throw e;
		      } finally {
		        close();
		      }

		    }
/*
		    private void writeMetaData(ResultSet resultSet) throws SQLException {
		      //   Now get some metadata from the database
		      // Result set get the result of the SQL query
		      
		      System.out.println("The columns in the table are: ");
		      
		      System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		      for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
		        System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
		      }
		    }
*/
		    private void writeResultSet(ResultSet resultSet) throws SQLException {
		      // ResultSet is initially before the first data set
		     
		    	   // get columns info
		        ResultSetMetaData rsmd = resultSet.getMetaData();
		        int columnCount = rsmd.getColumnCount();

		        // for changing column and row model
		        DefaultTableModel tm = new DefaultTableModel();
		        table = new JTable();
		        table.setModel(tm);
		        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		        table.setFillsViewportHeight(true);
		        JScrollPane scroll = new JScrollPane(table);
		        scroll.setHorizontalScrollBarPolicy(
		                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		        scroll.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		        

		        // clear existing columns 
		        tm.setColumnCount(0);

		        // add specified columns to table
		        for (int i = 1; i <= columnCount; i++ ) {
		            tm.addColumn(rsmd.getColumnName(i));
		        }   

		        // clear existing rows
		        tm.setRowCount(0);

		        // add rows to table
		        while (resultSet.next()) {

			        String id=resultSet.getString("id");
			        String name = resultSet.getString("name");
			        String password = resultSet.getString("password");
			        String email = resultSet.getString("email");
			        String address = resultSet.getString("address");
			        //String comment = resultSet.getString("comments");
			        String city = resultSet.getString("city");
			        String contactno = resultSet.getString("contactno");
			        System.out.println("ID: " + id);
			        System.out.println("Name: " + name);
			        System.out.println("Password: " + password);
			        System.out.println("E-MAil: " + email);
			        System.out.println("Address: " + address);
			        System.out.println("City:"+city);
			        System.out.println("Contact Number:"+contactno);
			        tm.addRow(new Object[]{id, name, password, email, address, city, contactno});
		        }
		        add(scroll);
		        //tm.addRow(a);
		    
		        //tm.fireTableDataChanged();
		    	/*
		  	  String[] columnNames = {"id", "name", "pasword","email" ,"address" ,"city" ,"contactno"};
		        DefaultTableModel model = new DefaultTableModel();
		        model.setColumnIdentifiers(columnNames);
		        table = new JTable();
		        table.setModel(model);
		        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		        table.setFillsViewportHeight(true);
		  	  
		  	  while (resultSet.next()) {
		        // It is possible to get the columns via name
		        // also possible to get the columns via the column number
		        // which starts at 1
		        // e.g. resultSet.getSTring(2);
		      	
		        String id=resultSet.getString("id");
		        String name = resultSet.getString("name");
		        String password = resultSet.getString("password");
		        String email = resultSet.getString("email");
		        String address = resultSet.getString("address");
		        //String comment = resultSet.getString("comments");
		        String city = resultSet.getString("city");
		        String contactno = resultSet.getString("contactno");
		        System.out.println("ID: " + id);
		        System.out.println("Name: " + name);
		        System.out.println("Password: " + password);
		        System.out.println("E-MAil: " + email);
		        System.out.println("Address: " + address);
		        System.out.println("City:"+city);
		        System.out.println("Contact Number:"+contactno);
		        model.addRow(new Object[]{id, name, password, email, address, city, contactno});
		      }
		      */
}
	
		    // You need to close the resultSet
		    private void close() {
		      try {
		        if (resultSet != null) {
		          resultSet.close();
		        }

		        if (statement != null) {
		          statement.close();
		        }

		        if (connect != null) {
		          connect.close();
		        }
		      } catch (Exception e) {

		      }
		    }

		  
}
