package org.example.easymart.aop.aspects;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.easymart.dto.response.UserDtoResponse;
import org.example.easymart.exception.UserNotAllowed;
import org.example.easymart.exception.UserNotAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.example.easymart.aop.annotations.Secured;


@Aspect
@Component
public class AuthenticationAspect {

    @Autowired
    HttpSession httpSession;


    @Around("@annotation(secured)")
    public Object checkAccess(ProceedingJoinPoint proceedingJoinPoint,Secured secured) throws Throwable {
        UserDtoResponse users = (UserDtoResponse) httpSession.getAttribute("CURRENT_USER");

        if (users == null) {
            throw new UserNotAuthenticated("Please Login Before Any Action!");
        }

        for(String role : secured.role())
        {
            if(role.equals(users.getRole().name())) {
                return proceedingJoinPoint.proceed();            }
        }

        throw new UserNotAllowed("Your don't have role to do this action");


    }







    }
