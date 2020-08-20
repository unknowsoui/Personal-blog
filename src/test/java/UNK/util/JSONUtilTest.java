package UNK.util;

import UNK.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Date;

public class JSONUtilTest {
    @Test
    public void t1(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Article article = new Article();
            article.setId(1);
            article.setTitle("h");
            article.setContent("dadada");
            article.setCreateTime(new Date());
            article.setUserId(1);
            String s = mapper.writeValueAsString(article);
            System.out.println(s);
            Article des = mapper.readValue(s,Article.class);
            System.out.println(des);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
