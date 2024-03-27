package borad.freeboard;

public class FreeBoard {
    private String userId;
    private String writer;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "글쓴이: " + writer + ", 제목: " + title + "\n-------------------------\n" + content + "\n-------------------------";
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
