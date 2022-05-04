import java.awt.*;
import java.io.*;
import java.util.*;

public class Model
{
	private String chosenWord;
	private static ArrayList<String> words;
	private String notCorrect;
	private int triesRemaining;

	public Model()
	{
		chosenWord = getRandomWord().toUpperCase();
		triesRemaining = 10;
	}

	public static String getRandomWord()
	{
		Scanner sc = null;
		try
		{
			sc = new Scanner(new File("words.txt"));

		} catch (FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}

		words = new ArrayList<String>();
		while (sc.hasNextLine())
		{
			words.add(sc.nextLine());
		}

		int randomIndex = randomWithRange(0, words.size() - 1);
		return words.get(randomIndex);

	}

	public boolean search(String letter)
	{
		if (chosenWord.toUpperCase().contains(letter))
		{
			return true;
		} else
		{
			notCorrect = letter;
			return false;
		}
	}

	public String replace(String letter, String word)
	{
		char[] chars = null;
		chars = word.toCharArray();
		try
		{
		{
			for (int i = 0; i < chosenWord.length(); i++)
			{
				if (Character.toString(chosenWord.charAt(i)).equalsIgnoreCase(letter))
				{
					chars[i] = letter.charAt(0);
				}
			}
		}}
		catch(ArrayIndexOutOfBoundsException e)
		{
			chosenWord = getRandomWord();
		}
		return String.valueOf(chars);

	}

	public static int randomWithRange(int min, int max)
	{
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}

	public String getWord()
	{
		return chosenWord;
	}

	public String getNotCorrect()
	{
		return notCorrect;
	}

	public void setTries()
	{
		triesRemaining = triesRemaining - 1;
	}

	public int getTries()
	{
		return triesRemaining;
	}

	

}
