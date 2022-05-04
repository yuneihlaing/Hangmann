import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Controller
{
	private Model theModel;
	private View theView;
	private String word;
	JButton clicked;
//	private int numberOfLetters;

	public Controller(Model model, View view)
	{
		this.theModel = model;
		this.theView = view;
		this.theView.addButtonListener(new ButtonListener());
		this.theView.addNewGameListener(new NewGameListener());
		theView.setDefaultWord(theModel.getWord());
		System.out.println(theModel.getWord());
		

	}

	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof JButton)
			{
				clicked = (JButton) e.getSource();
				String letter = clicked.getText();

				
				if (theModel.search(letter))
				{
					String newWord = theModel.replace(letter,theView.getWord());
					theView.setWord(newWord);
					if(theView.getWord().equals(theModel.getWord()))
					{
						JOptionPane.showMessageDialog(null, "You won!");
					}
					
				} else
				{
					theView.setIncorrectLetters(theModel.getNotCorrect().toString());
					theModel.setTries();
					theView.setJSlider(theModel.getTries());
					if(theModel.getTries()==0)
					{
						JOptionPane.showMessageDialog(null, "You lost! The word is " + word);
					}
				}
				clicked.setEnabled(false);
			}

		}

	}
	
	private class NewGameListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.updateUI();
			theModel = new Model();
			new Controller(theModel, theView);
			theView.resetJButtons();
			
			
		}
	}
	
	

	public static void main(String[] args)
	{
		new Controller(new Model(), new View());
	}

}
