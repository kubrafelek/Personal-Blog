package devrabbit.personalblog.util.security;

import devrabbit.personalblog.model.Role;
import devrabbit.personalblog.util.AccessToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface ITokenProvider {

    AccessToken createToken(String username, Set<Role> roles);
    boolean validateToken(AccessToken accessToken);
    AccessToken getTokenFromHeader(HttpServletRequest httpServletRequest);
    Authentication getAuthentication(AccessToken accessToken);
}
