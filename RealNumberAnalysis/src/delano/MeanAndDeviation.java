package delano;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class MeanAndDeviation.
 */
public class MeanAndDeviation {

	/** The frame. */
	private JFrame frame;

	/** The Find file btn. */
	private JButton FindFileBtn;

	/** The Mean area. */
	private JTextArea MeanArea;

	/** The Standard deviation area. */
	private JTextArea SDeviationArea;

	/** The lbl new label 1. */
	private JLabel lblNewLabel_1;

	/** The lbl new label 2. */
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeanAndDeviation window = new MeanAndDeviation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MeanAndDeviation() {
		initialize();
		fileExamination();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("File: Mean And Standard Deviation 500x650");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 10, 466, 25);
		frame.getContentPane().add(lblNewLabel);

		FindFileBtn = new JButton("Select a File");
		FindFileBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		FindFileBtn.setBounds(175, 100, 150, 25);
		frame.getContentPane().add(FindFileBtn);

		MeanArea = new JTextArea();
		MeanArea.setBounds(150, 250, 200, 50);
		frame.getContentPane().add(MeanArea);

		SDeviationArea = new JTextArea();
		SDeviationArea.setBounds(150, 400, 200, 50);
		frame.getContentPane().add(SDeviationArea);

		lblNewLabel_1 = new JLabel("The Mean of Selected File");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(150, 200, 200, 40);
		frame.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("The Standard Deviation of Selected File");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 350, 466, 40);
		frame.getContentPane().add(lblNewLabel_2);
	}

	/**
	 * Main Method, which will ask the user to select a file using JFileChooser,
	 * then grab all the values within file, and display the mean and standard
	 * deviation of all the values.
	 */
	private void fileExamination() {

		// Creates an action listener for the button to be pressed
		FindFileBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Sets the action of the button press to a JFileChooser
				JFileChooser chooseFile = new JFileChooser();
				chooseFile.showOpenDialog(null); // replace null with your swing container
				// The Selected file for which we are examining
				File fileToExamine = chooseFile.getSelectedFile();
				// List of every line within the selected file
				LinkedList<String> ListOfFileLines = new LinkedList<String>();
				// List of values from ListOfFileLines
				LinkedList<Double> ListOfValues = new LinkedList<Double>();
				String fname = fileToExamine.getAbsolutePath();

				String line = null;
				try {
					/* FileReader reads text files in the default encoding */
					FileReader fileReader = new FileReader(fileToExamine);

					/* always wrap the FileReader in BufferedReader */
					BufferedReader bufferedReader = new BufferedReader(fileReader);

					while ((line = bufferedReader.readLine()) != null) {
						ListOfFileLines.add(line);
					}

					/* always close the file after use */
					bufferedReader.close();
				} catch (IOException ex) {
					MeanArea.setText("Error: Unable to read the File " + fname);
				}

				// If the file is empty
				if (ListOfFileLines.isEmpty()) {
					MeanArea.setText("Error: Nothing is in the File Selected");
				} else {
					for (int i = 0; i < ListOfFileLines.size(); i++) {

						// Boolean is to help determine whether there are letters in the file, which we
						// don't want
						Boolean LetterExistence = false;
						// Every existence of a space is added as a element of list
						String[] list = ListOfFileLines.get(i).split(" ");

						for (int k = 0; k < list.length; k++) {
							// if a letter exists, break the for loop and display an error
							if (list[k].matches("[a-zA-Z]+")) {
								LetterExistence = true;
								break;
							}
							// If there is an empty element, set it to null
							else if (list[k].contains(" ") || list[k].isBlank()) {
								list[k] = null;
							} else {
								// If element isn't empty or have letters, transform it into a double and add to
								// list of values
								ListOfValues.add(Double.parseDouble(list[k]));
							}
						}
						// If a letter exists, display an error
						if (LetterExistence == true) {
							MeanArea.setText("Error: Only numbers allowed in file");
							break;
						}

					}
					// Find mean and standard deviation
					String meanOfFile = String.valueOf(findMean(ListOfValues));
					String DeviationOfFile = String.valueOf(findDeviation(ListOfValues));

					MeanArea.setText(meanOfFile);
					SDeviationArea.setText(DeviationOfFile);

				}
			}

		});

	}

	/**
	 * Find the mean of the selected linked list
	 *
	 * @param nums The list of numbers for which we are trying to find the mean of
	 * @return the mean of the selected list as a double
	 */
	public static double findMean(LinkedList<Double> nums) {
		double sum = 0;

		for (int i = 0; i < nums.size(); i++) {
			sum += nums.get(i);

		}

		return sum / nums.size();

	}

	/**
	 * Find deviation of the selected linked list
	 *
	 * @param nums The list of numbers for which we are trying to find the deviation of
	 * @return the deviation of the selected list as a double
	 */
	public static double findDeviation(LinkedList<Double> nums) {

		double mean = findMean(nums);

		double squareSum = 0;

		for (int i = 0; i < nums.size(); i++) {

			squareSum += Math.pow(nums.get(i) - mean, 2);

		}

		return Math.sqrt((squareSum) / (nums.size() - 1));

	}
}
