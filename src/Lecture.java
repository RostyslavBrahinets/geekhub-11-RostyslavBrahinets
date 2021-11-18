import java.time.LocalDate;

public class Lecture {
    private String title;
    private String describe;
    private LocalDate date;

    public Lecture(String title, String describe, LocalDate date) {
        this.title = title;
        this.describe = describe;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
