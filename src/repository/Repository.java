package repository;

import Domain.Question;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;


    private ArrayList<Question> questions=new ArrayList<>();
   
    public ArrayList<Question> getQuestions(){
        return questions;
    }

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void remove(Question q)
    {
        questions.remove(q);
    }

    public void readFromDB()
    {
        if(conn==null) openConnection();
        try{
            this.questions=new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Question");

            while(rs.next())
            {

                int id = rs.getInt("id");
                String text = rs.getString("text");
                String corectA = rs.getString("corectA");
                int score = rs.getInt("score");
                String userA = rs.getString("userA");


                Question newQ = new Question(id,text,corectA,score,userA);

                questions.add(newQ);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
