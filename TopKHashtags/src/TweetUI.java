

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;

public class TweetUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldforK;
	private JTextField textFieldforSize;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TweetUI frame = new TweetUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TweetUI() {
		setTitle("Generate Top K Hashtags");
		final GenerateTopKHashtags t= new GenerateTopKHashtags();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setEditable(false);
		textField.setBounds(315, 72, 162, 39);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 176, 234, 163);
		contentPane.add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Calibri",Font.BOLD,15));
		scrollPane.setViewportView(textArea);
		
		JLabel lblTweetCount = new JLabel("Tweet Count");
		lblTweetCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTweetCount.setBounds(315, 31, 174, 25);
		contentPane.add(lblTweetCount);
		
		JLabel lblGeneratedHashtags = new JLabel("Top K Hashtags");
		lblGeneratedHashtags.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGeneratedHashtags.setBounds(310, 122, 174, 39);
		contentPane.add(lblGeneratedHashtags);
		
		
		
		JLabel lblEnterValueOf = new JLabel("Enter Value of k");
		lblEnterValueOf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterValueOf.setBounds(35, 36, 148, 25);
		contentPane.add(lblEnterValueOf);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_1.setBounds(35, 72, 120, 39);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
	//	int k=Integer.parseInt(textField_1.getText());
	//System.out.println(textField_1.getText());
		JLabel lblEnterWindowSize = new JLabel("Enter Window size ");
		lblEnterWindowSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterWindowSize.setBounds(38, 110, 202, 31);
		contentPane.add(lblEnterWindowSize);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_2.setBounds(38, 152, 120, 39);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
	//int size=Integer.parseInt(textField_2.getText());
		//System.out.println(textField_2.getText());
		
	
		final JButton btnStop_1 = new JButton("Stop");
		btnStop_1.setEnabled(false);
		
		final JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				final int k=Integer.parseInt(textField_1.getText());
				final int size=Integer.parseInt(textField_2.getText());
				if(k<=0 || size <=0)
					JOptionPane.showMessageDialog(null, "Please enter numbers greater than 0");
				else
				{
				btnStop_1.setEnabled(true);
				btnStart.setEnabled(false);
				textField_1.setEditable(false);
				textField_2.setEditable(false);
				t.SetStream(textField,textArea,k,size);
				
				textArea.setText("Connecting....Please Wait !!");
				}
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Please enter numbers only");
				}
				
				
			}
		});
		btnStart.setBounds(40, 221, 115, 39);
		contentPane.add(btnStart);
		
		
		btnStop_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			t.StopStream(textArea,textField_2,textField_1,textField);
			btnStart.setEnabled(true);
			btnStop_1.setEnabled(false);
			textField_1.setEditable(true);
			textField_2.setEditable(true);
			}
		});
		btnStop_1.setBounds(40, 286, 115, 39);
		contentPane.add(btnStop_1);
		
		JLabel lblInput = new JLabel("INPUT");
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInput.setBounds(33, 11, 110, 25);
		contentPane.add(lblInput);
		
		JLabel lblOutput = new JLabel("OUTPUT");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOutput.setBounds(315, 6, 76, 19);
		contentPane.add(lblOutput);
		
	}
}
