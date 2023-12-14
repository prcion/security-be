package com.findork.preclinical.security.handler;

import com.findork.preclinical.exceptions.AppException;
import com.findork.preclinical.security.constants.AuthConstants;
import com.findork.preclinical.security.constants.ErrorCodes;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * A handler class responsible to manipulate the request
 */
@Component
public class RequestHandler {

    /**
     * Get jwt from request
     *
     * @return JWT
     */
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AuthConstants.AUTH_KEY);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw new AppException("Jwt is empty or Bearer missing", ErrorCodes.UNNAUTHORIZED.toString());
    }

    /**
     * Get jwt from string request
     *
     * @return JWT
     */
    public String getJwtFromStringRequest(String request) {
        if (StringUtils.hasText(request) && request.startsWith("Bearer ")) {
            return request.substring(7);
        }
        throw new AppException("Jwt is empty or Bearer missing", ErrorCodes.UNNAUTHORIZED.toString());
    }
}
