package library;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class ViewBooks extends JFrame {

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
					ViewBooks frame = new ViewBooks();
					frame.readDataBase();
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
	public ViewBooks() {
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
	      resultSet = statement.executeQuery("select * from library_management.books");
	      writeResultSet(resultSet);
	      
	    } catch (Exception e) {
	        throw e;
	      } finally {
	        close();
	      }

	    }
	
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

	        	String tid = resultSet.getString("id");
		      	String tcallno = resultSet.getString("callno");
		        String tname = resultSet.getString("name");				        
		        String tauthor = resultSet.getString("author");
		        String tpublisher = resultSet.getString("publisher");
		        String tquantity = resultSet.getString("quantity");
		        String tissued = resultSet.getString("issued");
		        String tdateadded = resultSet.getString("dateadded");
		        //System.out.println(tid);
	        	
		        tm.addRow(new Object[]{tid,tcallno,tname,tauthor,tpublisher,tquantity,tissued,tdateadded});
	        }
	        add(scroll);
	     
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
