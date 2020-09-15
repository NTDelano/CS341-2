package delano;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;

// TODO: Auto-generated Javadoc
/**
 * The Class SalesListApp.
 */
public class SalesListApp {

	/** The frame. */
	private JFrame frame;
	
	/** The Item field. */
	private JTextField ItemField;
	
	/** The Cost field. */
	private JTextField CostField;
	
	/** The Quantity field. */
	private JTextField QuantityField;
	
	/** The Final cost. */
	private JTextField FinalCost;
	
	/** The Total area. */
	private JTextArea TotalArea;
	
	/** The calc button. */
	private JButton calcButton;

	/** The Total. */
	public double Total = 0;

	/** The Item list. */
	ArrayList<String> ItemList = new ArrayList<String>();
	
	/** The Price list. */
	ArrayList<Double> PriceList = new ArrayList<Double>();
	
	/** The Format price list. */
	ArrayList<String> FormatPriceList = new ArrayList<String>();
	
	/** The Total list. */
	ArrayList<String> TotalList = new ArrayList<String>();
	
	/** The Quantity list. */
	ArrayList<Integer> QuantityList = new ArrayList<Integer>();

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesListApp window = new SalesListApp();
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
	public SalesListApp() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 633, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Sales List");
		lblNewLabel.setBounds(151, 10, 110, 26);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Item");
		lblNewLabel_1.setBounds(67, 99, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Cost");
		lblNewLabel_2.setBounds(67, 132, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setBounds(67, 167, 45, 13);
		frame.getContentPane().add(lblNewLabel_3);

		ItemField = new JTextField();
		ItemField.setBounds(197, 96, 306, 19);
		frame.getContentPane().add(ItemField);
		ItemField.setColumns(10);

		CostField = new JTextField();
		CostField.setBounds(197, 129, 306, 19);
		frame.getContentPane().add(CostField);
		CostField.setColumns(10);

		QuantityField = new JTextField();
		QuantityField.setBounds(197, 164, 306, 19);
		frame.getContentPane().add(QuantityField);
		QuantityField.setColumns(10);

		calcButton = new JButton("Add Item to Sales List");
		calcButton.setBounds(67, 224, 436, 34);
		frame.getContentPane().add(calcButton);

		TotalArea = new JTextArea();
		TotalArea.setBounds(67, 290, 436, 110);
		frame.getContentPane().add(TotalArea);

		JScrollPane scrollPane = new JScrollPane(TotalArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setSize(436, 131);
		scrollPane.setLocation(67, 275);
		frame.getContentPane().add(scrollPane);
		
		FinalCost = new JTextField();
		FinalCost.setBounds(197, 431, 306, 19);
		frame.getContentPane().add(FinalCost);
		FinalCost.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Total Cost:");
		lblNewLabel_4.setBounds(67, 434, 74, 13);
		frame.getContentPane().add(lblNewLabel_4);
	}

	/**
	 * Creates the events.
	 */
	private void createEvents() {
		calcButton.addActionListener(new ActionListener() {

			/**
			 * Action performed.
			 *
			 * @param e the pressing of the button
			 */
			public void actionPerformed(ActionEvent e) {
				
				TotalArea.setText("");

				String Item = ItemField.getText();
				Item = Item.replaceAll(" ", "");
				ItemList.add(Item);

				double Price = 0;
				String Cost = CostField.getText();
				Cost = Cost.replaceAll(" ", "");

				if (isNumber(Cost) == true) {
					Price = Double.parseDouble(Cost);
					PriceList.add(Price);
				} else {
					TotalArea.setText("Cost Error: Only Real Numbers");
				}

				int Quantity = 0;
				String Quan = QuantityField.getText();
				Quan = Quan.replaceAll(" ", "");

				if (isNumber(Quan) == true) {
					if (Quan.contains(".")) {
						TotalArea.setText("Quantity Error: Only Integers");
					} else {
						Quantity = Integer.parseInt(Quan);
						QuantityList.add(Quantity);
					}
				} else {
					TotalArea.setText("Quantity Error: Only Integers");
				}

				Total += (Price * Quantity);

				FinalCost.setText("$" + Total);

				FormatPriceList.add(String.format("%3.3f", Price));

				TotalList.add(String.format("%15s $%7s %2s%n", Item, Price, Quantity));

				for (int i = 0; i < ItemList.size(); i++) {

					TotalArea.append(TotalList.get(i));
				}

			}
		});

	}

	/**
	 * Checks if is numeric.
	 *
	 * @param strNum the str num
	 * @return true, if is numeric
	 */
	public static boolean isNumber(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
