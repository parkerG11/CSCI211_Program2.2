import java.util.InputMismatchException;
import java.util.Scanner;

public class GithubDriver {

	public static void main(String[] args) {
		//Declare variables and objects
		Scanner scan = new Scanner(System.in);
		int action = 1, start = 0, count = 0;
		String menu = "\n(1) insert\n(2) delete\n(3) Undo\n(4) Pull from Master version\n(5) Commit to Master\n(6) Quit";
		Github code;
		String editCode = "";


		System.out.println("Enter your Java code");
		code = new Github(scan.nextLine());
		try{
		  do{
			  	//Prompt for an action - Insert, Delete, Undo, Quit
				System.out.println("Your current branch: \n\t" + code + " \n\t(indexing from 0 to " + (code.toString().length()-1) + " - valid indexes are from 0 to " + code.toString().length() + ")");
				System.out.println(menu);
				System.out.println("Enter an action");
				
				action = scan.nextInt();
				scan.nextLine();
				
				//Determine which action and prompt for necessary input
				switch (action) {
				case 1:
					System.out.println("Enter starting INSERT index\n\t" + code + "\n\tIndex range is 0 to " + code.toString().length());
					start = scan.nextInt();
					scan.nextLine();
					
					System.out.println("Enter the code to insert");
					editCode = scan.nextLine();

					//Invoke Github insert method
					code.insert(start, editCode);
					break;
				case 2:
					System.out.println("Enter starting DELETE index\n\t" + code + "\n\tIndex range is 0 to " + (code.toString().length()-1));
					start = scan.nextInt();				
					
					System.out.println("Enter the number of characters to delete (count)");
					count = scan.nextInt();
					
					//Invoke Github delete method
					code.delete(start, count);
					break;
					
				case 3:
					//Invoke Github undo method
					code.undo();				
					break;
					
				case 4:
					//Invoke Github pull method
					if(code.getMasterSize() == 0){
						System.out.println(code.printMasterVersions());
					}	
					else {
						System.out.println("Enter the version number to pull from the master list" + code.printMasterVersions());
						
						int version = scan.nextInt();
						code.pull(version);
						}
					break;
					
				case 5:
					//Invoke Github commit method
					code.commit();
				}
				

		  }while (action != 6);
		}
		catch(InputMismatchException e) {
			System.out.println("Entry must be numeric!");
		}
		System.out.println("\n Your Final Commited Versions " + code.printMasterVersions());

	}

}
