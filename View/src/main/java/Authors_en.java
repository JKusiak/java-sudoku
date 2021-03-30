import java.util.ListResourceBundle;

public class Authors_en extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "lan", "Authors: " },
            { "names", "Juliusz Kusiak" }
    };
}
