import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class View extends JFrame
{
	private JButton[] keyboard;
	private JPanel mainPanel;
	private JPanel[] panels;
	private final Color bg = new Color(204, 255, 255);
	private JLabel incorrectLetters;
	private JButton newGame;
	private JSlider slider;
	private JLabel display;

	public View()
	{
		setTitle("Hangman Game");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
		pack();
		
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(bg);
		add(mainPanel);
		JLabel label = new JLabel(new ImageIcon("title.png"));
		mainPanel.add(label, BorderLayout.NORTH);
	
		updateUI();
		
		setVisible(true);

	}

	public void updateUI()
	{
		
		createPanels();
		
		keyboard = new JButton[26];

		int i = 0;
		for (char c = 'A'; c <= 'Z'; c++)
		{
			keyboard[i] = new JButton(Character.toString(c));
			keyboard[i].setPreferredSize(new Dimension(80, 40));
			panels[0].add(keyboard[i]);
			i++;
		}
		slider = new JSlider(0, 10, 10);

		// showing the ticks and labels of slider
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		// set spacing
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.setEnabled(false);

		
		display = new JLabel();
		display.setFont(new Font("Avenir Next", Font.PLAIN, 60));
		display.setHorizontalAlignment(JLabel.CENTER);
		panels[3].add(display);
		
		incorrectLetters = new JLabel();
		incorrectLetters.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		incorrectLetters.setHorizontalAlignment(JLabel.CENTER);
		panels[3].add(incorrectLetters);

		panels[2].add(slider);
		panels[2].add(panels[3]);
		mainPanel.add(panels[2]);

		newGame = new JButton("New Game");
		newGame.setPreferredSize(new Dimension(100, 100));
		panels[1].add(newGame);


	}

	public void createPanels()
	{
		panels = new JPanel[4];

		panels[0] = new JPanel(new GridLayout(4, 7));
		panels[1] = new JPanel(new FlowLayout());

		panels[1].add(panels[0]);
		mainPanel.add(panels[1], BorderLayout.SOUTH);

		panels[2] = new JPanel(new GridLayout(1, 3));
		mainPanel.add(panels[2], BorderLayout.CENTER);

		panels[3] = new JPanel(new GridLayout(2, 1));

		for (int i = 0; i < panels.length; i++)
		{
			panels[i].setBackground(bg);
		}

	}

	public void setIncorrectLetters(String newLetter)
	{
		incorrectLetters.setText(incorrectLetters.getText() + " " + newLetter);
	}

	
	public void setDefaultWord(String word)
	{
		String str="";
		for(int i=0; i<word.length(); i++)
		{
			str+= "-";
		}
		display.setText(str);
	}
	
	public void setWord(String newWord)
	{
		display.setText(newWord);
	}
	
	public String getWord()
	{
		return display.getText();
	}
	

	public void setJSlider(int tries)
	{
		slider.setValue(tries);
	}

	public void addButtonListener(ActionListener actionListener)
	{
		for (int i = 0; i < keyboard.length; i++)
		{
			keyboard[i].addActionListener(actionListener);
		}
	}
	
	public void resetJButtons()
	{
		for (int i = 0; i < keyboard.length; i++)
		{
			keyboard[i].setEnabled(true);
		}
	}
	
	public void addNewGameListener(ActionListener actionListener)
	{
		newGame.addActionListener(actionListener);
	}
}
