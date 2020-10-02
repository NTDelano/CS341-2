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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class MeanAndDeviation {

	private JFrame frame;
	private JButton FindFileBtn;
	private JTextArea MeanArea;
	private JTextArea SDeviationArea;

	/**
	 * Launch the application.
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
		MeanArea.setBounds(99, 217, 316, 54);
		frame.getContentPane().add(MeanArea);

		SDeviationArea = new JTextArea();
		SDeviationArea.setBounds(99, 349, 316, 54);
		frame.getContentPane().add(SDeviationArea);
	}

	private void fileExamination() {

		FindFileBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFileChooser chooseFile = new JFileChooser();
				chooseFile.showOpenDialog(null); // replace null with your swing container
				File fileToExamine = chooseFile.getSelectedFile();
				// if(returnVal == JFileChooser.APPROVE_OPTION) {
				// fileToExamine = chooseFile.getSelectedFile();
				// }

				LinkedList<String> ListOfFileLines = new LinkedList<String>();
				LinkedList<Double> ListOfValues = new LinkedList<Double>();
				String fname = fileToExamine.getAbsolutePath();
				Scanner scan = new Scanner(System.in);

				/* enter filename with extension to open and read its content */

				// System.out.print("Enter File Name to Open (with extension like file.txt) :
				// ");
				// String line = fileContents.readLine();

				/* this will reference only one line at a time */

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

				if (ListOfFileLines.isEmpty()) {
					MeanArea.setText("Error");
				} else {
					/*
					 * ListOfValues = [double(sub.split('.')[1]) for sub in ListOfFileLines];
					 * 
					 * for (int i=0; i< ListOfFileLines.size(); i++) { for item in
					 * ListOfFileLines.get(i): for subitem in item.split(): if(subitem.isdigit()):
					 * numbers.append(subitem) }
					 */

					for (int i = 0; i < ListOfFileLines.size(); i++) {

						Boolean LetterExistence = false;
						String[] list = ListOfFileLines.get(i).split(" ");
						

						for (int k = 0; k < list.length; k++) {
							if (list[k].matches("[a-zA-Z]+")) {
								LetterExistence = true;
								break;
							} else if (list[k].contains(" ") || list[k].isBlank()) {
								list[k] = null;
							}
							else {

								ListOfValues.add(Double.parseDouble(list[k]));
							}
						}
						if (LetterExistence == true) {
							MeanArea.setText("Error: Only numbers allowed in file");
							break;
						}
						
					}
					System.out.println(ListOfValues);
					
					String meanOfFile = String.valueOf(findMean(ListOfValues));
					String DeviationOfFile = String.valueOf(findDeviation(ListOfValues));
					
					MeanArea.setText(meanOfFile);
					SDeviationArea.setText(DeviationOfFile);
					
				}

				/*
				 * 
				 * JFileChooser chooseFile = new JFileChooser(); int returnVal =
				 * chooseFile.showOpenDialog(null); //replace null with your swing container
				 * File fileToExamine; if(returnVal == JFileChooser.APPROVE_OPTION) {
				 * fileToExamine = chooseFile.getSelectedFile(); }
				 * 
				 * BufferedReader fileContents = new BufferedReader(new
				 * FileReader(fileToExamine)); LinkedList<String> ListOfFileLines = new
				 * LinkedList<String>(); String line = fileContents.readLine(); String AllText =
				 * ""; while(line != null){ ListOfFileLines.add(line); line =
				 * fileContents.readLine(); }
				 */
			}

		});

	}
	
	public static double findMean(LinkedList<Double> listOfValues)
    {
        double sum = 0;

        for (int i = 0; i < listOfValues.size(); i++)
        {
            sum += listOfValues.get(i);

        }

        return sum / listOfValues.size();

    }
	
	public static double findDeviation(LinkedList<Double> nums)
    {

        double mean = findMean(nums);

        double squareSum = 0;

        for (int i = 0; i < nums.size(); i++)
        {

            squareSum += Math.pow(nums.get(i) - mean, 2);

        }

        return Math.sqrt((squareSum) / (nums.size() - 1));

    }
}
