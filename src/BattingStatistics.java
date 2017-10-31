import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class BattingStatistics {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String choice = "y";

		System.out.println("Welcome to Batting Average Calculator!");
		System.out.println();

		//Loop until user terminates the program
		while (choice.equalsIgnoreCase("y")) {

			int batterNum = Validator.getInt(scnr, "How many batters would you like to enter? ", 0, Integer.MAX_VALUE);
			System.out.println();

			//2D array to store team players and at bats
			int[][] team = new int[batterNum][];

			ArrayList<String> battingAverages = new ArrayList<String>();
			ArrayList<String> sluggingPercentage = new ArrayList<String>();

			getBatterAtBats(scnr, team, battingAverages, sluggingPercentage);

			//Table output of team batting stats
			System.out.println("Team Stats      AVG      SLG");
			System.out.println("==========      ===      ===");
			for (int i = 0; i < team.length; i++) {
				System.out.println("Batter " + (i+1) + "      " + battingAverages.get(i) + "    " + sluggingPercentage.get(i));
			}
			//Prompt user to start again or exit the app
			System.out.println();
			System.out.println("Would you like to start again? (y/n)");
			choice = String.valueOf(scnr.nextLine().charAt(0));
		}

		System.out.println();
		System.out.println("Program terminated.");
		scnr.close();
	}

	private static void getBatterAtBats(Scanner scnr, int[][] team, ArrayList<String> battingAverages,
			ArrayList<String> sluggingPercentage) {
		
		//Loop through each player in team array
		for (int i = 0; i < team.length; i++) {
			
			//Prompt user to enter total at bats for each player
			int atBats = Validator.getInt(scnr, "Enter number of at bats for player " + (i + 1) + ": ", 0,
					Integer.MAX_VALUE);
			int[] player = new int[atBats];

			System.out.println();
			System.out.println("0=out, 1=single, 2=double, 3=triple, 4=homerun");

			int hits = 0;
			int bases = 0;

			//For each at bat, enter the value for hits
			for (int j = 0; j < player.length; j++) {
				// System.out.print("Result for at-bat "+j+": " );
				int hitValue = Validator.getInt(scnr, "Result for at-bat " + j + ": ", 0, 4);
				bases += hitValue; //add hit value to total number of bases
				player[j] = hitValue; 
				//If player did not strikeout, add one to hit counter
				if (hitValue > 0) {
					hits++;
				}
			}
			team[i] = player; //save player array with each at-bat into team array

			//calculate at bats, used twice because formula same for hits and bases
			battingAverages.add(calcBatAvg(hits, atBats));
			sluggingPercentage.add(calcBatAvg(bases, atBats));

			System.out.println();
			System.out.println("Batting Average: " + battingAverages.get(i));
			System.out.println("Slugging Percentage: " + sluggingPercentage.get(i));
			System.out.println();

		}
	}

	public static String calcBatAvg(int hits, int atBats) {
		return formatStat((double) hits / atBats); //return formatted calculated avg
	}

	public static String formatStat(double stat) {
		DecimalFormat format = new DecimalFormat("0.000");
		return format.format(stat);
	}

}
