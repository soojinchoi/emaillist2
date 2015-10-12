package com.bit2015.emaillist.dao.test;

import java.util.List;
import com.bit2015.emaillist.dao.EmailListDao;
import com.bit2015.emaillist.vo.EmailListVo;

public class EmailListDaoTest {

	public static void main(String[] args) {
		
		InsertTest();//insert test
		getListTest();//getList test

	}

	public static void getListTest(){
		EmailListDao dao = new EmailListDao();
		List<EmailListVo> list = dao.getList();
		for(EmailListVo vo : list){
			//list에서 하나씩 뽑아서 EmailListVo클래스에 세팅해줌
			System.out.println(vo);
		}
	}
	
	public static void InsertTest(){
		EmailListDao dao = new EmailListDao();
		EmailListVo vo = new EmailListVo();
		vo.setFirstName("강");
		vo.setLastName("호동");
		vo.setEmail("gegea@nagge.com");
		
		dao.insert(vo);
	}
}
