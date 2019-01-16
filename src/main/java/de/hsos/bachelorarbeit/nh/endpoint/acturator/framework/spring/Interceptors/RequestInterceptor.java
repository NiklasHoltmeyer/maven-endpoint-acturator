package de.hsos.bachelorarbeit.nh.endpoint.acturator.framework.spring.Interceptors;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndPointExecutionInfo.*;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.Unit;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.framework.spring.Acturators.ExecuteInfoActurator;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.SystemInfo;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor extends HandlerInterceptorAdapter{
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        addStartTime(request);
        return true;
    }

    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {
        Unit<Long> executeTime = getExecuteTime(request);

        String url = request.getServletPath();
        String method = request.getMethod();

        String cl = response.getHeader("Content-Length");
        Unit<String> responseSize = new Unit<>(cl, "byte(s)");

        EndpointExecutionInfo endpointExecutionInfo = EndpointExecutionInfoBuilder.anEndpointExecutionInfo()
                .withUrl(url)
                .withMethod(method)
                .withExecutionTime(executeTime)
                .withResponseSize(responseSize)
                .build();
        ExecuteInfoActurator.list.add(endpointExecutionInfo);
   }

   private void addStartTime(HttpServletRequest request){
       long startTime = System.currentTimeMillis();
       request.setAttribute("startTime", startTime);
   }


   private Unit<Long> getExecuteTime(HttpServletRequest request){
       long startTime = (Long)request.getAttribute("startTime");
       long executeTime = System.currentTimeMillis() - startTime;
       return new Unit<>(executeTime,"ms");
   }
}
