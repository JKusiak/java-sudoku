import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "lan", "Autorzy: " },
            { "names", "Juliusz Kusiak" }
    };
}