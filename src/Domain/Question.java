package Domain;

public class Question {
    private int id;
    private String text;
    private String corectA;
    private int score;
    private String userA;


    public Question(int id, String text, String corectA, int score, String userA) {
        this.id = id;
        this.text = text;
        this.corectA = corectA;
        this.score = score;
        this.userA = userA;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", corectA='" + corectA + '\'' +
                ", score=" + score +
                ", userA='" + userA + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorectA() {
        return corectA;
    }

    public void setCorectA(String corectA) {
        this.corectA = corectA;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserA() {
        return userA;
    }

    public void setUserA(String userA) {
        this.userA = userA;
    }
}
