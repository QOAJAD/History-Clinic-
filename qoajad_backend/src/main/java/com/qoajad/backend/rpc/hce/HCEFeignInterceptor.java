package com.qoajad.backend.rpc.hce;

import com.qoajad.backend.model.external.hce.authentication.AuthenticationResponse;
import com.qoajad.backend.model.external.hce.authentication.HealthPromotingEntityAuthentication;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class HCEFeignInterceptor implements RequestInterceptor {

    private static final HealthPromotingEntityAuthentication USERS_HEALTH_AUTHENTICATION = new HealthPromotingEntityAuthentication(2, "UsersModulePassword");

    private final AuthenticationRPC authenticationRPC;

    @Autowired
    public HCEFeignInterceptor(@Qualifier("defaultHCEAuthenticationServiceRPC") final AuthenticationRPC authenticationRPC) {
        this.authenticationRPC = authenticationRPC;
    }

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        if (request == null) {
            return;
        }
        final ResponseEntity<AuthenticationResponse> response = authenticationRPC.attemptToAuthenticateAsHealthPromotingEntity(USERS_HEALTH_AUTHENTICATION);
        final boolean responseIsOkAndHasABody = response.getStatusCode() == HttpStatus.OK && response.getBody() != null;
        if (responseIsOkAndHasABody) {
            final String jwt = response.getBody().getToken();
            template.header("Authorization", jwt);
        }
    }
}
