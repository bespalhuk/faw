package bespalhuk.infra.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnauthenticatedSessionEntryPoint extends LoginUrlAuthenticationEntryPoint {

	public UnauthenticatedSessionEntryPoint() {
		super("/login.faces");
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
	                     AuthenticationException authException) throws IOException, ServletException {
		if (authException != null && isAjaxHeader(request)) {
			String redirectXml = redirectXML(request.getRequestURL().toString());
			response.setContentType("text/xml");
			response.getWriter().write(redirectXml);
		} else {
			super.commence(request, response, authException);
		}
	}

	private boolean isAjaxHeader(HttpServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
	}

	private String redirectXML(String redirectURL) {
		return String.format(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><partial-response><redirect url=\"%s\"></redirect></partial-response>",
				redirectURL);
	}

}
