package borad.anonymousboard;

public class AnonyMousBoard {
    private String userId;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "제목: " + title + "\n-------------------------\n" + content + "\n-------------------------";
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
