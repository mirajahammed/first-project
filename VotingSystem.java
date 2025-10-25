import java.util.Scanner;

public class VotingSystem {
   
    private static int[] votes = new int[3];  
   
    public static void castVote(int candidate) {
        if (candidate >= 1 && candidate <= votes.length) {
            votes[candidate - 1]++;  
            System.out.println("Vote cast for Candidate " + candidate);
        } else {
            System.out.println("Invalid candidate number. Try again.");
        }
    }

    
    public static void displayResults() {
        for (int i = 0; i < votes.length; i++) {
            System.out.println("Candidate " + (i + 1) + ": " + votes[i] + " votes");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean voting = true;

        while (voting) {
            System.out.println("Enter the candidate number to vote for (1-3), or 0 to end voting:");
            int input = scanner.nextInt();

            if (input == 0) {
                voting = false;  
            } else {
                castVote(input); 
            }
        }

        System.out.println("Voting has ended. Final results:");
        displayResults();  
        scanner.close();
    }
}