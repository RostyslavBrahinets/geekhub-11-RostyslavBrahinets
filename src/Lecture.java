public class Lecture {
    private String title;
    private String describe;
    private Lecturer lecturer;
    private HomeWork homework;
    private AdditionalMaterial additionalMaterial;

    public Lecture(String title, String describe, Lecturer lecturer,
                   HomeWork homework, AdditionalMaterial additionalMaterial) {
        this.title = title;
        this.describe = describe;
        this.lecturer = lecturer;
        this.homework = homework;
        this.additionalMaterial = additionalMaterial;
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

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public HomeWork getHomework() {
        return homework;
    }

    public void setHomework(HomeWork homework) {
        this.homework = homework;
    }

    public AdditionalMaterial getAdditionalMaterial() {
        return additionalMaterial;
    }

    public void setAdditionalMaterial(AdditionalMaterial additionalMaterial) {
        this.additionalMaterial = additionalMaterial;
    }
}
