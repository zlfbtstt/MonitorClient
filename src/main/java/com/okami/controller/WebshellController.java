package com.okami.controller;

import com.okami.MonitorClientApplication;
import com.okami.bean.GlobalBean;
import com.okami.plugin.ScannerApplication;
import com.okami.plugin.scanner.bean.BaseTask;
import com.okami.plugin.scanner.bean.WebshellFeatures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wh1t3P1g
 * @since 2017/4/21
 */

@RestController
public class WebshellController {

    @Autowired
    private GlobalBean globalBean;

    private ScannerApplication scannerApplication;

    public WebshellController(){
    }

    @RequestMapping(value="/webshell/task/new",method=RequestMethod.POST)
    public String newTask(HttpServletRequest request){
        BaseTask task= MonitorClientApplication.ctx.getBean(BaseTask.class);
        scannerApplication=MonitorClientApplication.ctx.getBean(ScannerApplication.class);

        //设置参数
        task.setTaskName(request.getParameter("task_name"));
        task.setTaskId(request.getParameter("task_id"));
        task.setFilePath(request.getParameter("file_path"));
        task.setExceptPath(request.getParameter("except_path"));
        task.setExceptExtension(request.getParameter("except_extension"));
        task.setScriptExtension(request.getParameter("script_extension"));
        task.setFilter(request.getParameter("filter").equals("true"));
        task.setType(Integer.parseInt(request.getParameter("type")));
        task.setMode(Integer.parseInt(request.getParameter("mode")));
        if(scannerApplication.isRunning()){
            return "Task "+globalBean.getTaskName()+" is running";
        }else{
            scannerApplication.setTask(task);
            scannerApplication.start();

            return "Task "+task.getTaskName()+" ok";
        }
    }
    @RequestMapping(value="/webshell/task/stop",method=RequestMethod.GET)
    public String stopTask(){
        scannerApplication=MonitorClientApplication.ctx.getBean(ScannerApplication.class);
        if(scannerApplication.isRunning()){
            scannerApplication.stop();
            return "Stop success";
        }else{
            return "Nothing to Stop";
        }
    }
}
