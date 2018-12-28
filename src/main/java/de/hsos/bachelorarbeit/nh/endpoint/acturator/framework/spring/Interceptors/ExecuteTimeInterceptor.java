package de.hsos.bachelorarbeit.nh.endpoint.acturator.framework.spring.Interceptors;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndpointExecutionInfo;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.framework.spring.Acturators.ExecutetimeActurator;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExecuteTimeInterceptor extends HandlerInterceptorAdapter{
    public ExecuteTimeInterceptor(){
        System.out.println("\nwuhu\n");
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        response.addHeader("startTime", startTime+"");
        return true;
    }

    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {
        long startTime = (Long)request.getAttribute("startTime");
        long executeTime = System.currentTimeMillis() - startTime;

        String url = request.getRequestURL().toString();
        String method = request.getMethod();

        EndpointExecutionInfo endpointExecutionInfo = new EndpointExecutionInfo(url, method, executeTime);
        ExecutetimeActurator.list.add(endpointExecutionInfo);
   }
}