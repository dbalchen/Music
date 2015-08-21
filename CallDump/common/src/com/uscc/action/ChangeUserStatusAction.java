package com.uscc.action;

import com.uscc.dao.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import javax.servlet.http.HttpSession;

public class ChangeUserStatusAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ServletContext application = servlet.getServletContext();

		UserDAO myUserDAO = (UserDAO) application.getAttribute("userdaokey");
		myUserDAO.updateStatus(request.getParameter("id"), request
				.getParameter("status"));

		// Dispatch/forward the request to the view
		return mapping.findForward("success");
	}
}
