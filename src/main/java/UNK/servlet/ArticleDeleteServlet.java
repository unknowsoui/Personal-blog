package UNK.servlet;

import UNK.dao.ArticleDAO;
import UNK.exception.BusinessException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDelete")
public class ArticleDeleteServlet extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String idsString = req.getParameter("ids");
        int[] ids = parseIds(idsString);
        int num = ArticleDAO.delete(ids);
        if(num == 0){
            throw new BusinessException("005","文章删除出错");
        }
        return null;
    }

    private static int[] parseIds(String idsString) {
        String[] strings = idsString.split(",");
        int[] result = new int[strings.length];
        for(int i = 0;i < strings.length;i++){
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }
}
