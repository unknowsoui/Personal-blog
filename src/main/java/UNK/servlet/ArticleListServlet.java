package UNK.servlet;

import UNK.dao.ArticleDAO;
import UNK.model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/articleList")
public class ArticleListServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Article> articles = ArticleDAO.list();
        return articles;
    }

//    //测试数据
//    private static List<Article> testData() {
//        List<Article> articles = new ArrayList<>();
//        Article article = new Article();
//        article.setId(1);
//        article.setTitle("h");
//        article.setContent("dadada");
//        article.setCreateTime(new Date());
//        article.setUserId(1);
//        Article article2 = new Article();
//        article2.setId(2);
//        article2.setTitle("h2");
//        article2.setContent("d27272");
//        article2.setCreateTime(new Date());
//        article2.setUserId(2);
//        articles.add(article);
//        articles.add(article2);
//        return articles;
//    }
}
