package de.hsos.bachelorarbeit.nh.endpoint.acturator.framework.spring.Interceptors;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndPointExecutionInfo.*;
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
        addStartCPUInfo(request);
        addStartMemoryInfo(request);
        return true;
    }

    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {
        ExecutionTime executeTime = getExecuteTime(request);
        Usage cpuUsage = getCPUUsage(request);
        Usage cpuSystemUsage = getCPUSystemUsage(request);
        Usage memUsage = getMemoryUsage(request);
        String url = request.getServletPath();
        String method = request.getMethod();

        String cl = response.getHeader("Content-Length");
        Size responseSize = new Size(cl, "byte(s)");

        EndpointExecutionInfo endpointExecutionInfo = EndpointExecutionInfoBuilder.anEndpointExecutionInfo()
                .withUrl(url)
                .withMethod(method)
                .withExecutionTime(executeTime)
                .withCpuUsage(cpuUsage)
                .withCpuUsageSystem(cpuSystemUsage)
                .withMemoryUsage(memUsage)
                .withResponseSize(responseSize)
                .build();
        ExecuteInfoActurator.list.add(endpointExecutionInfo);
   }

    private void addStartMemoryInfo(HttpServletRequest request) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        request.setAttribute("startMemory", memory);
    }

   private void addStartTime(HttpServletRequest request){
       long startTime = System.currentTimeMillis();
       request.setAttribute("startTime", startTime);
   }
   private void addStartCPUInfo(HttpServletRequest request){
       double processCpuLoad = SystemInfo.getProcessCpuLoad();
       double systemCpuLoad = SystemInfo.getSystemCpuLoad();
       request.setAttribute("processCpuLoad", processCpuLoad);
       request.setAttribute("systemCpuLoad", systemCpuLoad);
   }

   private ExecutionTime getExecuteTime(HttpServletRequest request){
       long startTime = (Long)request.getAttribute("startTime");
       long executeTime = System.currentTimeMillis() - startTime;
       return new ExecutionTime(executeTime);
   }

   private Usage getCPUUsage(HttpServletRequest request){
       double processCpuLoadAfter = SystemInfo.getProcessCpuLoad();
       double processCpuLoadPre = (double)request.getAttribute("processCpuLoad");

       double dif = processCpuLoadAfter-processCpuLoadPre;

       return new Usage(dif);
   }

   private Usage getCPUSystemUsage(HttpServletRequest request){
       double processCpuLoadAfter = SystemInfo.getSystemCpuLoad();
       double processCpuLoadPre = (double)request.getAttribute("systemCpuLoad");

       double dif = processCpuLoadAfter-processCpuLoadPre;

       return new Usage(dif);
   }

   private Usage getMemoryUsage(HttpServletRequest request){
       long preMem = (long)request.getAttribute("startMemory");
       Runtime runtime = Runtime.getRuntime();
       long mem = runtime.totalMemory() - runtime.freeMemory();
       double difMB = (mem - preMem) / 1e+6;
       return new Usage(difMB, "MB");
   }


}
