package ws.msg.InstMsg.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MessageService {
	private static final String url = "jdbc:mysql://localhost/WS";

	private static final String user = "root";


	public MessageService() {

	}

	public Message getMessage(int id, String pwd) {
		Message ret = new Message();
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pwd);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM MESSAGES WHERE id="+id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ret.setId(rs.getInt("id"));
				ret.setMessage(rs.getString("message"));
				ret.setAuthor(rs.getString("author"));
			}
			rs.close();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					conn.close();
			}catch(SQLException se){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return ret;
	}

	public Message updateMessage(int id, Message msg, String pwd) {
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pwd);
			stmt = conn.createStatement();
			String sql = "UPDATE MESSAGES " +
					"SET id=" + msg.getId() + ", message='" + msg.getMessage() + "', author='" + msg.getAuthor() + "' " +
					"WHERE id=" + id;
			stmt.executeUpdate(sql);
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					conn.close();
			}catch(SQLException se){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
    	return getMessage(msg.getId(),pwd);
    }
    
    public Message createMessage(Message msg, String pwd) {
    	Connection conn = null;
    	Statement stmt = null;
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(url, user, pwd);
    		stmt = conn.createStatement();
    		String sql = "INSERT INTO MESSAGES " +
    				"VALUES (" + msg.getId() + ", '" + msg.getMessage() + "', '" + msg.getAuthor() + "')";
    		stmt.executeUpdate(sql);
    	}catch(SQLException se){
    		se.printStackTrace();
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		try{
    			if(stmt!=null)
    				conn.close();
    		}catch(SQLException se){
    		}
    		try{
    			if(conn!=null)
    				conn.close();
    		}catch(SQLException se){
    			se.printStackTrace();
    		}
    	}
    	return getMessage(msg.getId(), pwd);
    }

    public Message deleteMessage(int id, String pwd) {
    	Message ret = getMessage(id, pwd);
    	Connection conn = null;
    	Statement stmt = null;
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(url, user, pwd);
    		stmt = conn.createStatement();
    		String sql = "DELETE FROM MESSAGES " +
    				"WHERE id=" + id;
    		stmt.executeUpdate(sql);
    	}catch(SQLException se){
    		se.printStackTrace();
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		try{
    			if(stmt!=null)
    				conn.close();
    		}catch(SQLException se){
    		}
    		try{
    			if(conn!=null)
    				conn.close();
    		}catch(SQLException se){
    			se.printStackTrace();
    		}
    	}
    	return ret;
    }
    
    public List<Message> getAllMessages(String pwd) {
    	List<Message> l = new ArrayList<Message>();
    	Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pwd);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM MESSAGES";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Message ret = new Message();
				ret.setId(rs.getInt("id"));
				ret.setMessage(rs.getString("message"));
				ret.setAuthor(rs.getString("author"));
				l.add(ret);
			}
			rs.close();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					conn.close();
			}catch(SQLException se){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
    	return l;
    }

}
