package UNK.servlet;

import UNK.exception.BaseException;
import UNK.model.Result;
import UNK.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public abstract class AbstractBaseServlet extends HttpServlet {
    private static final ConcurrentHashMap<String, AtomicInteger> MAP = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        //根据前端articleList.jsp和articleDetail.jsp的JSON数据格式，做好统一的格式返回
        Result result = new Result();
        try {
            //正确返回业务数据
            Object data = process(req,resp);
            result.setSuccess(true);
            result.setData(data);
        } catch (Exception e) {
            //捕获到异常，需要设置前端需要的错误信息和堆栈信息
            if(e instanceof BaseException){
                BaseException BE = (BaseException)e;
                result.setMessage("错误码："+BE.getCode() + "错误信息：" + BE.getMessage());
            }else {
                result.setMessage("服务器异常：未知的错误");
            }
            StringWriter sw = new StringWriter();
            PrintWriter Rpw = new PrintWriter(sw);
            e.printStackTrace(Rpw);
            result.setStackTrace(sw.toString());
        }
        PrintWriter pw = resp.getWriter();
        pw.println(JSONUtil.serialize(result));//返回JSON字符串
        pw.flush();

//        req.getScheme();//协议号
//        req.getServletPath();//请求servlet的路径
//        req.getContextPath();//应用部署路径（上下文路径）
//        req.getRequestURL();//请求的全路径
//        req.getRequestURI();// ContextPath + ServletPath
//        req.getPathInfo();//null
            String servletPath = req.getServletPath();
            AtomicInteger count = MAP.putIfAbsent(servletPath,new AtomicInteger());
            if(count != null) {
                count.incrementAndGet();
            }
    }

    public static ConcurrentHashMap<String, AtomicInteger> getMAP() {
        return MAP;
    }

    public abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
