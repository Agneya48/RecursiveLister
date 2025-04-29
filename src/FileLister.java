import javax.swing.JTextArea;
import java.io.File;

public class FileLister {

    private final String indentUnit;

    public FileLister(String indentUnit) {
        this.indentUnit = indentUnit;
    }

    /**
     * Recursively calls dir.listFiles to traverse all files and subdirectories
     * in a given directory.
     * @param dir File directory that will be fully displayed
     * @param depth int used for keeping track of recursion depth for tab spacing
     * @param textArea JTextArea that where the directory breakdown will be displayed
     */
    public void listAllFiles(File dir, int depth, JTextArea textArea) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                String indent = indentUnit.repeat(depth);

                if (file.isDirectory()) {
                    textArea.append(indent + "[DIR] " + file.getName() + "\n");
                    listAllFiles(file, depth + 1, textArea);
                } else {
                    textArea.append(indent + "- " + file.getName() + "\n");
                }
            }
        }
    }
}
