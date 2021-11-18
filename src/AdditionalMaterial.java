import java.util.Arrays;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdditionalMaterial that = (AdditionalMaterial) o;
        return Arrays.equals(listOfBooks, that.listOfBooks)
            && Arrays.equals(listOfLinks, that.listOfLinks);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(listOfBooks);
        result = 31 * result + Arrays.hashCode(listOfLinks);
        return result;
    }

    @Override
    public String toString() {
        return "AdditionalMaterial{"
            + "listOfBooks=" + Arrays.toString(listOfBooks)
            + ", listOfLinks=" + Arrays.toString(listOfLinks)
            + '}';
    }
}
