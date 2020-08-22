package UNK.servlet;

import UNK.dao.ArticleDAO;
import UNK.exception.BusinessException;
import UNK.model.Article;
import UNK.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //请求头包含 title content id
        InputStream is = req.getInputStream();
        Article article = JSONUtil.deserialize(is,Article.class);
        int num = ArticleDAO.update(article);
        if(num != 1){
            throw new BusinessException("004","文章修改出错");
        }
        return null;
    }
}
