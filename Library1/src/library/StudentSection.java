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

public class StudentSection extends JFrame {

	private JPanel contentPane;
	
	private Connection connect = null;
	  private Statement statement = null;
	  private ResultSet resultSet = null;

	  final private String host = "localhost:3306";
	  final private String user = "root";
	  final private String passwd = "harlieenbindra";
	  
	  JTable table;
	  String sid;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentSection frame = new StudentSection();
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
	public StudentSection(String l) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		sid=l;
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
		      String Query="SELECT b.callno,b.name,a.issuedate,c.stu_id,c.stu_name,c.stu_branch";
		      		Query+=" FROM library_management.books_issued a";
		      		Query+=" INNER JOIN library_management.books b";
		      		Query+=" ON a.bookcallno = b.callno";
		      		Query+=" INNER JOIN library_management.student_details c";
		      		Query+=" ON a.studentid = c.stu_id";
		      		Query+=" WHERE a.studentid="+sid;
		      	
		     resultSet=statement.executeQuery(Query);
		     System.out.println("Query executed");
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

			        String bcallno=resultSet.getString("callno");
			        String bname = resultSet.getString("name");
			        String idate = resultSet.getString("issuedate");
			        String stuid = resultSet.getString("stu_id");
			        String stuname = resultSet.getString("stu_name");
			        String stubr = resultSet.getString("stu_branch");

			        tm.addRow(new Object[]{bcallno, bname, idate, stuid, stuname, stubr});
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
