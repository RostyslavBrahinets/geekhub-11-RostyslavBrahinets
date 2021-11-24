public class Course {
    private String name;
    private Lection[] lections;
    private String formOfStudy;
    private String address;

    public Course(String name, Lection[] lections,
                  String formOfStudy, String address) {
        this.name = name;
        this.lections = lections;
        this.formOfStudy = formOfStudy;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lection[] getLectures() {
        return lections;
    }

    public void setLectures(Lection[] lections) {
        this.lections = lections;
    }

    public String getFormOfStudy() {
        return formOfStudy;
    }

    public void setFormOfStudy(String formOfStudy) {
        this.formOfStudy = formOfStudy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}