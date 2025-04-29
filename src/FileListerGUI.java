import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileListerGUI extends JFrame {

    private JTextArea textArea;
    private JButton startButton;
    private JButton clearButton;
    private JButton quitButton;

    private static final String INDENT = "    "; //4 spaces, tabs don't display well in JTextArea

    public FileListerGUI() {
        setTitle("Recursive File Lister");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));

        //=====Top Panel======//
        //Contains Heading and Buttons//
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Recursive File Lister", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(25, 10, 5, 10));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        // Button SubPanel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startButton = new JButton("Start");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");
        buttonPanel.add(quitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(startButton);

        // Button actions
        startButton.addActionListener(e -> openDirectoryChooser());
        clearButton.addActionListener(e -> resetFileDisplay());
        quitButton.addActionListener(e -> System.exit(0));

        //Add subPanel to main panel
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(topPanel, BorderLayout.NORTH);


        //=====Center Panel ======//
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setMargin(new Insets(10, 20, 10, 10));
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(centerPanel, BorderLayout.CENTER);

        setVisible(true);

    }

    private void openDirectoryChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDir = chooser.getSelectedFile();
            textArea.setText(""); // Clear previous results

            FileLister lister = new FileLister(INDENT);
            lister.listAllFiles(selectedDir, 0, textArea);
        }
    }

    private void resetFileDisplay() {
        textArea.setText("");
    }
}
