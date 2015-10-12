package com.bit2015.emaillist.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.bit2015.emaillist.vo.*;

public class EmailListDao {
	
	private Connection getConnection() throws SQLException{
		Connection connection = null;
		try{
			//1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			//2.커넥션 만들기
			String dbURL  = "jdbc:oracle:thin:@localhost:1521:xe";
			connection =  DriverManager.getConnection ( dbURL, "webdb", "webdb" );
			
		}catch(ClassNotFoundException ex){
			System.out.println("드라이버 로딩 실패-"+ex);
		}
		return connection;
	}
	public void insert(EmailListVo vo){//정보 입력
		try{
				Connection connection = getConnection();
				//3.Statement 준비
				String sql = "insert into email_list values(email_list_no_seq.nextval,?,?,?)";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				
				//4.binding
				pstmt.setString(1, vo.getFirstName());
				pstmt.setString(2, vo.getLastName());
				pstmt.setString(3, vo.getEmail());
				
				//5.query 실행
				pstmt.executeUpdate();
				
				//6.자원정리 (자원정리는 역순으로 한다)
				pstmt.close();
				connection.close();

			}catch(SQLException ex){
				System.out.println("SQL 오류-"+ex);
		}
	}
		
	public List<EmailListVo> getList() {//테이블에 있는 모든 소스를 리스트로 가져온다
		
		List<EmailListVo> list = new ArrayList<EmailListVo>();
		try{
			Connection connection = getConnection();
			//3.Statement 생성
			Statement stmt = connection.createStatement();
			
			//4.SQL문 실행
			String sql = "select * from email_list ORDER BY  no desc";
			ResultSet rs = stmt.executeQuery(sql);
			
			//5.row 가져오기
			while( rs.next() ){
				Long no = rs.getLong(1); // no를 가져옴
				String firstName = rs.getString(2); // db에서 first name을 가져옴
				String lastName = rs.getString(3); // db에서 lastName을 가져옴
				String email = rs.getString(4); // db에서 email을 가져옴
				
				EmailListVo vo = new EmailListVo();	//EmailListVo list 틀 생성
				vo.setNo(no);						//vo list에 no를 세팅함
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				
				list.add(vo); //list에 넣어줌
			}
			//6.자원정리 (자원정리는 역순으로 한다)
				rs.close();
				stmt.close();
				connection.close();

		}catch(SQLException ex){
			System.out.println("SQL 오류-"+ex);
		}
		return list;
	}
	
}
