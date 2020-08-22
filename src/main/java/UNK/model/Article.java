package UNK.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Article {
    private Integer id;

    private String title;

    private  String content;

    private Integer userId;

    private Date createTime;

    //新增文章所需要的字段
    private String userAccout;
}
