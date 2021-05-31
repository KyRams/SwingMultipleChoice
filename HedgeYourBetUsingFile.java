import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;

public class HedgeYourBetUsingFile extends JFrame implements ActionListener {

    String[] questions = new String[] { "Q1 - What is the climbers knot?", "Q2 - What is the belayers knot?",
            "Q3 - What knot should the climber use to make a quad anchor?",
            "Q4 - After cleaning a route, what knot should the climber use to re attach themself?",
            "Q5 - What knot should be used at the end of a rope for rapelling?" };

    int i = 0;
    int score = 0;
    static int totalScore = 0;
    static String highScore = "";
    String outcome = "";

    JLabel label1 = new JLabel(questions[i]);
    JCheckBox choiceBox1 = new JCheckBox("A) Figure Eight", false);
    JCheckBox choiceBox2 = new JCheckBox("B) Double Fishermans", false);
    JCheckBox choiceBox3 = new JCheckBox("C) Double Barrell", false);
    JButton button = new JButton("Enter");
    JTextField field = new JTextField(10);
    JLabel gameOverLabel = new JLabel();
    JLabel highScoreLabel = new JLabel("Current high Score: " + highScore);

    public HedgeYourBetUsingFile() {
        super("Hedge Your Bet Using File");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(highScoreLabel);
        add(label1);
        add(choiceBox1);
        add(choiceBox2);
        add(choiceBox3);
        add(button);
        add(field);
        add(gameOverLabel);
        button.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int boxCount = 0;

        if (choiceBox1.isSelected()) {
            ++boxCount;
        }
        if (choiceBox2.isSelected()) {
            ++boxCount;
        }
        if (choiceBox3.isSelected()) {
            ++boxCount;
        }
        // Question1
        if (label1.getText().equals(questions[0])) {
            if (boxCount == 1 && choiceBox1.isSelected()) {
                score = 5;
            } else if (boxCount == 2 && choiceBox1.isSelected()) {
                score = 2;
            } else if (boxCount == 3) {
                score = 1;
            } else if (choiceBox1.isSelected() == false) {
                score = 0;
            }
            totalScore += score;
        }
        // Question 2
        if (label1.getText().equals(questions[1])) {
            if (boxCount == 1 && choiceBox3.isSelected()) {
                score = 5;
            } else if (boxCount == 2 && choiceBox3.isSelected()) {
                score = 2;
            } else if (boxCount == 3) {
                score = 1;
            } else if (choiceBox3.isSelected() == false) {
                score = 0;
            }
            totalScore += score;
        }
        // Question 3
        if (label1.getText().equals(questions[2])) {
            if (boxCount == 1 && choiceBox2.isSelected()) {
                score = 5;
            } else if (boxCount == 2 && choiceBox2.isSelected()) {
                score = 2;
            } else if (boxCount == 3) {
                score = 1;
            } else if (choiceBox2.isSelected() == false) {
                score = 0;
            }
            totalScore += score;
        }
        // Question 4
        if (label1.getText().equals(questions[3])) {
            if (boxCount == 1 && choiceBox1.isSelected()) {
                score = 5;
            } else if (boxCount == 2 && choiceBox1.isSelected()) {
                score = 2;
            } else if (boxCount == 3) {
                score = 1;
            } else if (choiceBox1.isSelected() == false) {
                score = 0;
            }
            totalScore += score;
        }
        // Question 5
        if (label1.getText().equals(questions[4])) {
            if (boxCount == 1 && choiceBox3.isSelected()) {
                score = 5;
            } else if (boxCount == 2 && choiceBox3.isSelected()) {
                score = 2;
            } else if (boxCount == 3) {
                score = 1;
            } else if (choiceBox3.isSelected() == false) {
                score = 0;
            }
            totalScore += score;
            if (totalScore >= 21) {
                outcome = "Fantastic!";
            } else if (totalScore < 21 && totalScore >= 15) {
                outcome = "Very Good!";
            } else {
                outcome = "OK.";
            }
            gameOverLabel.setText("Game Over. Total score is: " + totalScore + ", which is " + outcome);
            button.setEnabled(false);
            Path file = Paths
                    .get("C:\\Users\\krdra\\Desktop\\Java\\Unit 14 Swing components\\HedgeYourBetHighScores.txt");
            if (totalScore > Integer.parseInt(highScore)) {
                // System.out.println("New high score: " + totalScore);
                highScore = "\n" + totalScore;
                byte[] data = highScore.getBytes();
                OutputStream output = null;
                try {
                    output = new BufferedOutputStream(Files.newOutputStream(file, APPEND));
                    output.write(data);
                    output.flush();
                    output.close();
                } catch (Exception d) {
                    System.out.println(d);
                }
            }
        }

        if (i < 4) {
            ++i;
            label1.setText(questions[i]);

        }
        choiceBox1.setSelected(false);
        choiceBox2.setSelected(false);
        choiceBox3.setSelected(false);

        field.setText("Total score is: " + totalScore);
        // System.out.println("test" + totalScore);
    }

    public static void updateHighScore(Path file, int totalScore, String highScore) {
        if (totalScore > Integer.parseInt(highScore)) {
            System.out.println("New high score: " + totalScore);
            highScore = "\n" + totalScore;
            byte[] data = highScore.getBytes();
            OutputStream output = null;
            try {
                output = new BufferedOutputStream(Files.newOutputStream(file, APPEND));
                output.write(data);
                output.flush();
                output.close();
            } catch (Exception d) {
                System.out.println(d);
            }
        }
    }

    public static void main(String[] args) {

        Path file = Paths.get("C:\\Users\\krdra\\Desktop\\Java\\Unit 14 Swing components\\HedgeYourBetHighScores.txt");
        InputStream fileInput = null;
        try {
            fileInput = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInput));
            String s = null;
            String current = "";
            s = reader.readLine();
            while (s != null) {
                highScore = s;
                s = reader.readLine();
            }
            // System.out.println("Current High Score " + highScore);
            fileInput.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        HedgeYourBetUsingFile aFrame = new HedgeYourBetUsingFile();
        final int WIDTH = 600;
        final int HEIGHT = 600;
        aFrame.setSize(WIDTH, HEIGHT);
        aFrame.setVisible(true);

    }

}
