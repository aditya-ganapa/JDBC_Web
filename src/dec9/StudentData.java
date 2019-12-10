package dec9;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.com.util.DatabaseUtil;

@WebServlet("/StudentData")
public class StudentData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		try {
	/*		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db10", "root", "password-1");
	*/		
			Connection connection = DatabaseUtil.getCon();
			
			if(connection!=null)
				System.out.println("connection successful");
			else
				System.out.println("connection problem");
			
			int id = Integer.parseInt(request.getParameter("roll"));
			String name = request.getParameter("name");
			String class1 = request.getParameter("class");
			
			
			java.sql.Statement st = connection.createStatement();
		//	Statement st = connection.createStatement();
			
		//	int res = st.executeUpdate("insert into student(roll,name,class) values("+id+",'"+name+"','"+class1+"')");
		
			PreparedStatement pst = connection.prepareStatement("insert into student(roll,name,class) values(?,?,?)");
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setString(3, class1);
			int res = pst.executeUpdate();
			
			if(res==1)
				out.print("<h2>Record Added</h2>");
			
			ResultSet rs = st.executeQuery("select * from student");
		//	rs.next();
			out.print("<font color='blue' size=12>");
			while(rs.next())
				out.println("<br>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
		//	System.out.println(rs.getInt("roll")+" "+rs.getString("name")+" "+rs.getString("class")+" "+rs.getString("location")+" "+rs.getInt("marks"));
		} catch (SQLException e) {
			System.out.println(e);
		//	out.print("<font color='red' size=15>"+e);
			out.print("<font color='red' size=15>"+e.getMessage());
		}
	}
}