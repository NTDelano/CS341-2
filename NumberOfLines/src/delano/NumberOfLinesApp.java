package delano;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * The Class NumberOfLinesApp. The purpose is for the user to select a file and
 * the App will count the total number of lines, not including blank lines or
 * comments
 */
public class NumberOfLinesApp {

	/** The frame. */
	private JFrame frame;

	/** The text area. */
	private JTextArea textArea;

	/** The File btn. */
	private JButton FileBtn;

	/**
	 * Launch the application.
	 *
	 * @param args the main program for the app, which will display said jframe
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NumberOfLinesApp window = new NumberOfLinesApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the app, and runs fileExamination for the work needed to be done for
	 * said app.
	 */
	public NumberOfLinesApp() {
		initialize();
		fileExamination();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		FileBtn = new JButton("Choose A File");
		FileBtn.setBounds(125, 58, 200, 25);
		frame.getContentPane().add(FileBtn);

		textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textArea.setBounds(175, 117, 100, 50);
		frame.getContentPane().add(textArea);

		JLabel lblNewLabel = new JLabel("\"Number of Lines\" Counter for Files");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 21, 450, 25);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Number of Lines In File:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(44, 119, 129, 25);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("(Not Including Comments or Blank Lines)");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(100, 186, 250, 30);
		frame.getContentPane().add(lblNewLabel_2);
	}

	/**
	 * File examination, when a button is pressed, the user will be asked to select
	 * a file. When file has determined number of lines, it will display it
	 */
	private void fileExamination() {
		FileBtn.addActionListener(new ActionListener() { // Creates an action listener for the button to be pressed
			public void actionPerformed(ActionEvent e) {
				int count = fileReader(); // To prevent clutter, I put process in a seperate method
				String str1 = Integer.toString(count); // Convert the total line count to a string
				textArea.setText(str1); // Display the string for the output in our App
			}
		});
	}

	/**
	 * File reader - Reads the contents of the file, searching for blank lines or
	 * comments.
	 *
	 * @return the total amount of non-empty or non-comment lines
	 */
	public int fileReader() {
		int count = 0; // Counter for the number of lines
		JFileChooser chooseFile = new JFileChooser(); // Sets the action of the button press to a JFileChooser
		chooseFile.showOpenDialog(null); // replace null with your swing container
		File fileToExamine = chooseFile.getSelectedFile(); // The Selected file for which we are examining
		LinkedList<String> ListOfFileLines = new LinkedList<String>(); // List of every line within the selected file
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileToExamine); // FileReader reads text files in the default
																	// encoding
			BufferedReader bufferedReader = new BufferedReader(fileReader); // always wrap the FileReader in
																			// BufferedReader
			boolean comment = false; // Initialize no comments located
			int LengthOfComment = 0; // Total Number of lines for comment, set 0 as comment is false

			while ((line = bufferedReader.readLine()) != null) { // While examining each line in text file
				count++; // Once each line is read, count will increase by one, indicating a new line
				if (line.isBlank()) { // If the line is empty or only contains white spaces
					count -= 1; // Don't include that line in the counter
				}
				if (line.contains("//")) { // If a line contains a simple // for a comment
					count -= 1; // Don't include the line
				}
				if (line.contains("/*")) { // If a line contains /*
					comment = true; // Then it is the beginning of a comment
				}
				if (comment == true) { // While a comment is active
					LengthOfComment += 1; // Count the number of lines after the beginning of the comment
				}
				if (line.contains("*/")) { // If line contains */
					count -= LengthOfComment; // Then take the lines included in the comment and subtract it to the
												// total line count
					comment = false; // Set comment to inactive so counter doesn't continue
					LengthOfComment = 0; // Revert length of comment to 0 for the next comment that may appear
				}
				ListOfFileLines.add(line); // After all conditions are met, go to the next line
			}
			bufferedReader.close(); // always close the file after use
		} catch (IOException ex) { // Just in case file isn't readable
			textArea.setText("Error: Unable to read the File ");
		}
		return count;
	}
}
