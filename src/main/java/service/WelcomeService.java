package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WelcomeService {
	private final String driver = "org.mariadb.jdbc.Driver";
	private final String DB_IP = "54.252.20.123";
	private final String DB_PORT = "3306";
	private final String DB_NAME = "clue";
	private final String id = "qoko";
	private final String pw = "qoko";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public List<Map<String,String>> test() {
		String dbUrl = "jdbc:mariadb://"+DB_IP+":"+DB_PORT+"/"+DB_NAME;
		List<Map<String, String>> rsltList = new ArrayList<>();
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(dbUrl, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.print("드라이버 로드 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.print("DB 접속 실패");
			e.printStackTrace();
		}
		
		if(conn != null) {
			System.out.println("DB 접속 성공");
			
			StringBuilder sb = new StringBuilder();
			sb.append("select");
			sb.append(" * ");
			sb.append("from CLASS");
			
			String sql = sb.toString();
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Map<String,String> rsltMap = new HashMap<>();
					rsltMap.put("CLASS_ID", rs.getString(1));
					rsltMap.put("DESC", rs.getString(2));
					rsltMap.put("TEACHER", rs.getString(3));
					
					rsltList.add(rsltMap);
				}
				System.out.println(rsltList);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) {
							rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return rsltList;
	}
}
