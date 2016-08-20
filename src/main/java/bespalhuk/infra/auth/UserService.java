package bespalhuk.infra.auth;

import bespalhuk.domain.auth.user.User;
import com.google.common.base.Strings;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Service
public class UserService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		if (!Strings.isNullOrEmpty(s)) {
			return User.of();
		}
		throw new UsernameNotFoundException("User not found!");
	}

	public void login() throws IOException, ServletException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		ServletRequest request = (ServletRequest) context.getRequest();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, (ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().getResponseComplete();
	}

}
