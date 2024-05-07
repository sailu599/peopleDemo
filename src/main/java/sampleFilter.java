import java.io.IOException;
import java.net.http.HttpResponse;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
public class sampleFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	      System.out.println("from filter");
	      ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
			((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
			System.out.println(((HttpServletResponse) response).getHeaderNames());
	        chain.doFilter(request, response);
	        System.out.println("end of the response");
		 
	}

}
