package UNK.servlet;

import UNK.dao.ArticleDAO;
import UNK.exception.BusinessException;
import UNK.exception.ClientException;
import UNK.model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDetail")
public class ArticleDetailServlet extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id  = req.getParameter("id");
        Integer articleID;
        try {
            articleID = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ClientException("001" + "请求参数错误，id：" + id);
        }
        Article article = ArticleDAO.detail(articleID);
        if(article == null){
            throw new BusinessException("002","查询不到文章详情id=" + articleID);
        }
        return article;
    }

//    //测试数据
//    private static Article testData() {
//        Article article = new Article();
//        article.setId(1);
//        article.setTitle("h");
//        article.setContent("dadada");
//        article.setCreateTime(new Date());
//        article.setUserId(1);
//        return article;
//    }
}
