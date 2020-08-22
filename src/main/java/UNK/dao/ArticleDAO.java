package UNK.dao;

import UNK.exception.SystemExecption;
import UNK.model.Article;
import UNK.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleDAO {
    //显示文章列表
    public static List<Article> list() {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select id,title,content,user_id,create_time from article";
        try {
            List<Article> articles = new ArrayList<>();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setUserId(resultSet.getInt("user_id"));
                article.setCreateTime(new Date(resultSet.getTimestamp("create_time").getTime()));
                articles.add(article);
            }
            return articles;
        } catch (SQLException e) {
            throw new SystemExecption("000","执行SQL失败，无法获取文章列表",e);
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
    }

    public static Article detail(Integer articleID) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select id,title,content,user_id,create_time from article where id = ?";
        try {
            Article article = new Article();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,articleID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setUserId(resultSet.getInt("user_id"));
                article.setCreateTime(new Date(resultSet.getTimestamp("create_time").getTime()));
            }
            return article;
        } catch (SQLException e) {
            throw new SystemExecption("001","无法获取文章信息",e);
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
    }

    public static int add(Article article) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into article(title, content, user_id ,create_time) select ?,?,id,? from user where name = ?";
        int num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2,article.getContent());
            preparedStatement.setString(4,article.getUserAccout());
            preparedStatement.setTimestamp(3,new Timestamp(new Date().getTime()));
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SystemExecption("002" + "插入文章失败" + e);
        } finally {
            DBUtil.close(connection,preparedStatement);
        }
    }

    public static int update(Article article) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update article set title = ?, content = ? where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2,article.getContent());
            preparedStatement.setInt(3,article.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SystemExecption("003","修改文章失败",e);
        } finally {
            DBUtil.close(connection,preparedStatement);
        }
    }

    public static int delete(int[] ids) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        StringBuffer sql = new StringBuffer("delete from article where id in (");
        for(int i = 0; i < ids.length;i++){
            if(i == 0){
                sql.append("?");
            }else {
                sql.append(",?");
            }
        }
        sql.append(")");
        try {
            preparedStatement = connection.prepareStatement(sql.toString());
            for(int i = 0;i < ids.length;i++){
                preparedStatement.setInt(i + 1,ids[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SystemExecption("004" + "删除文章失败" + e);
        } finally {
            DBUtil.close(connection,preparedStatement);
        }
    }
}
