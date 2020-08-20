package UNK.servlet;

import UNK.model.Article;
import UNK.model.Result;
import UNK.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/articleList")
public class ArticleListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        //根据前端articleList.jsp和articleDetail.jsp的JSON数据格式，做好统一的格式返回
        Result result = new Result();
        List<Article> articles = null;
        try {
            //正确返回业务数据
            articles = testData();
            result.setSuccess(true);
            result.setData(articles);
        } catch (Exception e) {
            //捕获到异常，需要设置前端需要的错误信息和堆栈信息
            result.setMessage(e.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter Rpw = new PrintWriter(sw);
            e.printStackTrace(Rpw);
            result.setStackTrace(sw.toString());
        }
        PrintWriter pw = resp.getWriter();
        pw.println(JSONUtil.serialize(result));//返回JSON字符串
        pw.flush();
    }

    //测试数据
    private static List<Article> testData() {
        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setId(1);
        article.setTitle("h");
        article.setContent("dadada");
        article.setCreateTime(new Date());
        article.setUserId(1);
        Article article2 = new Article();
        article2.setId(2);
        article2.setTitle("h2");
        article2.setContent("d27272");
        article2.setCreateTime(new Date());
        article2.setUserId(2);
        articles.add(article);
        articles.add(article2);
        return articles;
    }
}
