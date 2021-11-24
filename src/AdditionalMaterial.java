public class AdditionalMaterial {
    private String[] listOfBooks;
    private String[] listOfLinks;

    public AdditionalMaterial(String[] listOfBooks, String[] listOfLinks) {
        this.listOfBooks = listOfBooks;
        this.listOfLinks = listOfLinks;
    }

    public String[] getListOfBooks() {
        return listOfBooks;
    }

    public void setListOfBooks(String[] listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    public String[] getListOfLinks() {
        return listOfLinks;
    }

    public void setListOfLinks(String[] listOfLinks) {
        this.listOfLinks = listOfLinks;
    }
}
