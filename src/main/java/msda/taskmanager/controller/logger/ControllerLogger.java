package msda.taskmanager.controller.logger;

import msda.taskmanager.Service.UserService;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.utils.RequestService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

@Aspect
@Order(0)
@Component
public class ControllerLogger {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final RequestService requestService;
    private final UserService userService;

    public ControllerLogger(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @Around(value = "execution(* msda.taskmanager.controller.*.*(..))")
    public Object restAOP(ProceedingJoinPoint joinPoint) throws Throwable {
        Object output;

        long elapsedTime = System.currentTimeMillis();

        StringBuilder args = new StringBuilder();
        String result = null;
        String userInfo = null;

        output = joinPoint.proceed(joinPoint.getArgs());
        if (output != null)
            result = output.toString();

        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (Object o : joinPoint.getArgs()) {
                if (o instanceof HttpServletRequest) {
                    userInfo =  " User IP: " + requestService.getClientIp((HttpServletRequest) o) + " ";
                } else {
                    args.append(o.toString()).append(" ");
                }
            }
        }

        User user = userService.getAuthenticatedUser();
        if (user == null) {
            userInfo += " USER INFO: User not authorized";
        } else {
            userInfo += " USER INFO: " + user.toString();
        }

        elapsedTime = System.currentTimeMillis() - elapsedTime;

        log.info("[REST(" + elapsedTime + "ms)][" + joinPoint.getTarget().getClass().getSimpleName() + "] - "
                + joinPoint.getSignature().getName() + " - " + args + " , " + result + userInfo);

        return output;
    }
}