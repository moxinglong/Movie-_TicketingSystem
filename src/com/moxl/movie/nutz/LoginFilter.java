package com.moxl.movie.nutz;

import javax.servlet.http.HttpServletRequest;

import org.nutz.lang.Strings;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.RawView;
import org.nutz.mvc.view.ServerRedirectView;

import com.moxl.movie.pojo.User;

public class LoginFilter implements ActionFilter {

      @Override
      public View match(ActionContext context) {
            HttpServletRequest request=context.getRequest();
            User user = (User) request.getSession().getAttribute("me");
            String contentType=request.getContentType();
     if(Strings.sNull(contentType).contains("application/x-www-form-urlencoded")&&user==null){
            context.getResponse().setHeader("sessionstatus","timeout");
            return new RawView("");
      }else if (user == null) {
            ServerRedirectView view = new ServerRedirectView("/error/nologin.jsp");
            return view;
     }
     request.setAttribute("me", user);

      String basePath=request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()
      + Mvcs.getServletContext().getContextPath() + "/";
      request.setAttribute("basePath",basePath );
      return null;
      }

}
