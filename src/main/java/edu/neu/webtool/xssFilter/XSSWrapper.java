package edu.neu.webtool.xssFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSWrapper extends HttpServletRequestWrapper{
	     HttpServletRequest orgRequest = null;
	    public XSSWrapper(HttpServletRequest request) {
	        super(request);
	        orgRequest = request;
	    }
	    
	    @Override  
	    public String getParameter(String name) {  
	        String value = super.getParameter(xssEncode(name));  
	        if (value != null) {  
	            value = xssEncode(value);  
	        }  
	        return value;  
	    }  
	    private static String xssEncode(String s) {  
	        if (s == null || "".equals(s)) {  
	            return s;  
	        }
			s = s.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
			s = s.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
			s = s.replaceAll("'", "& #39;");
			s = s.replaceAll("eval\\((.*)\\)", "");
			s = s.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
			s = s.replaceAll("script", "");
			return s;
	    }

	    public HttpServletRequest getOrgRequest() {
	        return orgRequest;
	    }
	        public static HttpServletRequest getOrgRequest(HttpServletRequest req) {  
	        if (req instanceof XSSWrapper) {  
	            return ((XSSWrapper) req).getOrgRequest();  
	        }    
	        return req;  
	    }  
}