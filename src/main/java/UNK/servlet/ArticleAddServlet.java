package UNK.servlet;

import UNK.dao.ArticleDAO;
import UNK.exception.BusinessException;
import UNK.model.Article;
import UNK.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@WebServlet("/articleAdd")
public class ArticleAddServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //请求头包含 title content userAccont
        InputStream is = req.getInputStream();
        //http请求Content-Type 为application/json，请求体是json字符串，需要反序列化为Java对象
        //需要检查json字符串的键，必须和Java类型中的属性匹配
        Article article = JSONUtil.deserialize(is,Article.class);
        System.out.println("++++++++++++++++++++++++++++++++\n" + article);
        int num = ArticleDAO.add(article);
        if(num != 1){
            throw new BusinessException("003","插入文章失败");
        }
        return null;
    }
}
