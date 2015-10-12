package com.bit2015.emaillist.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.emaillist.dao.EmailListDao;
import com.bit2015.emaillist.vo.EmailListVo;
import com.bit2015.emaillist.web.action.EmailListActionFactory;
import com.bit2015.web.WebUtil;
import com.bit2015.web.action.Action;
import com.bit2015.web.action.ActionFactory;

@WebServlet("/el")
public class EmailListSetvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		
		ActionFactory actionFactory = new EmailListActionFactory();
		Action action = actionFactory.getAction(actionName);
		action.execute(request, response);
		
	}

}
