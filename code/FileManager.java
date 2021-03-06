package mazerunner;

import java.awt.Image;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;
//import sun.audio.AudioData;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
//import sun.audio.ContinuousAudioDataStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FileManager
{
	
	private static ArrayList<String> namelist;
	public static ArrayList<Image> list;
	
	// Methods
	public static ArrayList<Image> getImages()
	{
		  list = new ArrayList<Image>();
	      namelist = new ArrayList<String>();
	      namelist.add("bomb");
	      namelist.add("bonus");
	      namelist.add("ai");
	      namelist.add("ai2");
	      namelist.add("right");
	      namelist.add("left");
	      namelist.add("up");
	      namelist.add("down");
	      namelist.add("bonus");
	      namelist.add("bonus");
	      namelist.add("bonus");
	      namelist.add("bonus");
	    
	      for(String s : namelist)
	          list.add((new ImageIcon("src\\img\\" + s + ".png")).getImage());
	      return list;
	}
	
	public static ArrayList<Image> getTutorialImages()
	{
		  ArrayList<Image> tutList = new ArrayList<Image>();
	      ArrayList<String> namelist = new ArrayList<String>();
	      namelist.add("bombTut");
	      namelist.add("bonusTut");
	      namelist.add("finishTut");
	      namelist.add("patrolsTut");
	      namelist.add("scoreTut");
	      
	      for(String s : namelist)
	    	  tutList.add((new ImageIcon("src\\tutorialimg\\" + s + ".png")).getImage());
	      return tutList;
	}
	
	public static int[][] getMaze(int numP, int difficulty)
	{
	    //2 for 1 start
	    //3 for 1 end
	    //-2 for 2 start
	    //-3 for 2 end
	    //1 for walls
            String s = numP + "" + difficulty;
            String path = "src\\text\\" + s + ".txt";
            Scanner scan = null;
            try 
            {
            	scan = new Scanner(new File(path)); 
            } catch(Exception e){e.printStackTrace();}
            String line = scan.nextLine();
            ArrayList<Integer[]> list = new ArrayList();
            while(!line.equals(""))
            {
                String[] lineList = line.split(",");
                Integer[] intList = new Integer[lineList.length];
                for(int i = 0; i < intList.length; i++){
                    intList[i] = Integer.parseInt(lineList[i]);
                }
                list.add(intList);
                try{
                    line = scan.nextLine();
                }catch (java.util.NoSuchElementException e){
                	
                    break;
                }
            }
            int [][] ret = new int[list.size()][list.get(0).length];
            for(int i = 0; i < ret.length; i++)
            {
                for(int j = 0; j < ret[0].length; j++){
                    
                    ret[i][j] = list.get(i)[j];
                    
                }
            }
            return ret;
	 }
	
	public static ArrayList<String> getNames()
	{
		ArrayList<String> namelist = new ArrayList<String>(); // initialize namelist
		try(Scanner fileIn = new Scanner(new File("src\\text\\names.txt"));)
		{
			for(int i=0;i<5;i++)
			{
				String s = fileIn.next();
				namelist.add(s);
			} 
		}catch(Exception e){e.printStackTrace();}
		return namelist;
	}
	
	public static ArrayList<Integer> getScores()
	{
		ArrayList<Integer> scorelist = new ArrayList<>(); // initialize score list
		try(Scanner fileIn = new Scanner(new File("src\\text\\scores.txt"));){
		for(int i=0;i<5;i++)
		{
			String s = fileIn.next();
			int x = Integer.parseInt(s);
			scorelist.add(x);
		} }catch(Exception e){e.printStackTrace();}
		return scorelist;
	}
	
	public static void changeNames(ArrayList<String> listX)
	{   
            try{
		Scanner fileIn = new Scanner(new File("src\\text\\names.txt"));
		for(int i=0;i<5;i++)
		{
		String oldName = fileIn.next();
		String s = listX.get(i);
		Path path = Paths.get("src\\text\\names.txt");
		Charset charset = StandardCharsets.UTF_8;
		String content = new String(Files.readAllBytes(path), charset);
		content = content.replaceAll( oldName ,"");
		Files.write(path, content.getBytes(charset));
		}
		for(int i=0;i<5;i++)
		{
			try 
			{
				String b = listX.get(i);
				String a = "" + b;
	            FileWriter writer = new FileWriter("src\\text\\names.txt", true);
	            writer.write(a +"\t");
	            writer.close();
	        } catch (IOException e) 
			{
	            e.printStackTrace();
	        }
		}
             }catch(Exception e){e.printStackTrace();}
	}
	
	public static void changeScores(ArrayList<Integer> listX) 
	{
            try{
                		
		Scanner fileIn = new Scanner(new File("src\\text\\scores.txt"));
		for(int i=0;i<5;i++)
		{
		String oldName = fileIn.next();
		Path path = Paths.get("src\\text\\scores.txt");
		Charset charset = StandardCharsets.UTF_8;
		String content = new String(Files.readAllBytes(path), charset);
		content = content.replaceAll( oldName , "");
		Files.write(path, content.getBytes(charset));
		}
		for(int i=0;i<5;i++)
		{
			try 
			{
				int a = listX.get(i);
				String s = "" + a;
	            FileWriter writer = new FileWriter("src\\text\\scores.txt", true);
	            writer.write(s +"\t");
	            writer.close();
	        } catch (IOException e) 
			{
	            e.printStackTrace();
	        }
		}
		
	
		
            }catch(Exception e){e.printStackTrace();}
	}
    
	public static AudioInputStream[] getMusic(){
            
            String file1 = "src\\sound\\1.mp3";
            String file2 = "src\\sound\\2.mp3";
            AudioInputStream[] in = new AudioInputStream[2];
            try {
                for(int i = 0; i < 2; i++){
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src\\sound\\" + (i + 1) + ".mp3"));
                    in[i] = audioStream;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return in;
        }
	
}
