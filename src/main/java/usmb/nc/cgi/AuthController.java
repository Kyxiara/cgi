package usmb.nc.cgi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import usmb.nc.cgi.security.JwtAuthenticationRequest;
import usmb.nc.cgi.security.JwtTokenUtil;
import usmb.nc.cgi.security.repository.UserRepository;
import usmb.nc.cgi.security.service.JwtAuthenticationResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    UserRepository users;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        try {
            User newUser = new User();
            String username = authenticationRequest.getUsername();
            newUser.setUsername(username);
            newUser.setPassword(authenticationRequest.getPassword());
            users.save(newUser);
            System.out.println(users.findAll());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}