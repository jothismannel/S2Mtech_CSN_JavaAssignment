/* Java program to implement a game
 * input : characters 
 * 
 *  
 */


package jothis.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Class_game 
{

	
		public static void main(String[] args) {
			// TODO Auto-generated method stub

			int  score = 100,len=0,count=1,match=0,index;
			
			BufferedReader b_reader = new BufferedReader(new InputStreamReader(System.in));
			String letter;
			String word ="apple";							//secret word
			char[] word_array;
			char[] temp;								//array for storing the input character
			
			len=word.length();
			ArrayList<String> stars=new ArrayList<String>();
			
			
			for(index = 0;index<len;index++)
			{
				stars.add("*");
			}
			
			word_array=word.toCharArray();
			do 
			{
				System.out.println("                                                    Guess The Word          ");
				
				for(index = 0;index < len;index++)
				{
					System.out.print("   "+stars.get(index));
				}
				
				System.out.println();
				System.out.println("My Score = " + score);

				try 
				{
					letter =b_reader.readLine();				//read character from user
					temp=letter.toCharArray();
					System.out.println(temp.length);
								
						for(index = 0;index < len;index++)
						{
							if(temp[0]==word_array[index])
							{
								stars.set(index,letter);	
								word_array[index]='*';
								count=0;
								match++;
								break;
							}//end of if 
						}//end of for loop 
						
					if (count == 1)
					{
						score=score-10;
					}
					count=1;
				} //end of try
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
				if(match==len)
				{
					break;
				}
			
			} while (score != 0 );

			if (score != 0) 
			{
				System.out.println("                                                    Guess The Word          ");
				System.out.println();
				System.out.println();
				for(index = 0;index < len;index++)
				{
					System.out.print("                                      "+stars.get(index));
				}
				System.out.println();
				System.out.println("My Score = " + score);
				System.out.print("                                                         You Win ");
			} //end of if 
			else 
			{
				System.out.print("                                                         You Lose");
			}

		}

	}//end of class
