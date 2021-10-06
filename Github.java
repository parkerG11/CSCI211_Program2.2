import java.util.ArrayList;
import java.util.Stack;

public class Github implements GithubADT{
	private String code;
	private Stack<Undo> branch;
	private Stack<String> master;
	boolean undo;
	
	/**
	 * Github no-arg Constructor
	 */
	public Github(){
		code = "";
		branch = new Stack<>();
		master = new Stack<>();
		undo = false;

		// Push current branch, empty String "", onto stack
		// This branch is not a master until it is committed
		branch.push(new Undo("", 0, 0, 2));
	}
	
	/**
	 * Github overloaded Constructor
	 * 
	 * @param code
	 */
	public Github(String code) {
		this.code = code;
		branch = new Stack<>();
		master = new Stack<>();

		// Push current branch, code, onto stack
		// This branch is not a master until it is committed
		branch.push(new Undo(code, 0, code.length(), 2));
	}
	
	
	/**
	 * insert method that adds to a currently pulled version of code
	 */
	public void insert(int pos, String add) {
		//TODO:  Complete insert method
		
		
		code = "System.out.println";
		code = "System.out.print(“Hello World”);";
		insert(reverse.getPos(), reverse.getCode());

	}

	
	/**
	 * delete method that removes from a currently pulled version of code
	 */
	public void delete(int pos, int count) {
		//TODO:  Complete delete method
		code = "System.out.print(“Hello World”);";
		delete(reverse.getPos(), reverse.getCount());
		Undo reverse = branch.pop();
	}

	
	/**
	 * undo method that will undo an insert or delete from a currently pulled version of code
	 */
	public void undo() {
		//Use a boolean, undo, to "turn on undo mode"
		//This will need to be used in your insert and delete methods
		undo = true;
		
		
		//TODO:  Complete undo method
		Undo reverse = branch.pop();
		
		//Once undo method is complete "turn off undo mode" 
		undo = false;
	}

	
	/**
	 * pull method that retrieves a current version from the master stack
	 * NOTE: version numbers are off by 1 (i.e., version #1 is at index 0 of the master stack
	 *                                           version #2 is at index 1 of the master stack
	 *                                           etc.
	 *                                           
	 * HINT: Use a temp stack. Pop master stack, pushing onto temp stack, until you are
	 *       at the master version you want - save this version to the variable code.  
	 *       Then, pop temp stack until its empty, pushing back onto master stack - this maintains master stack
	 *       Look at printMasterVersions method.  
	 *       
	 * LAST LINE OF METHOD IS PROVIDED FOR YOU. THIS PUSHES THE MASTER VERSION YOU WANT - the variable code -
	 * ONTO THE BRANCH STACK - THIS LINE OF CODE CAN NOW BE EDITED.                                              
	 * @param version
	 */
	public void pull(int version) {
		//TODO:  Complete pull method
		
		//Current master version found and assigned to code
		//Push this version onto the stack
		branch.push(new Undo(code, 0, code.length(), 2));	
	}
	
	
	/**
	 * commit methods stores this version on the master stack
	 * Also, branch stack is cleared since no more edits can be made to the commited version
	 * Instance variable, code, also cleared
	 * 
	 * NOTE:  If a user wants to edit this version, it first must be pulled
	 */
	public void commit() {
		//Committing a branch suggests the branch stack MUST have at least 1 element - the branch being edited
		if(branch.isEmpty())
			throw new InvalidGithubException();
		while (!branch.isEmpty()) {
			branch.pop();
		}
		master.push(code);
		code = "";
	}
	
	
	/**
	 * Getter method to return the size of the Master stack (stack containing all commited versions)
	 * @return
	 */
	public int getMasterSize() {
		return master.size();
	}
	
	
	/**
	 * printMasterVersions method prints all current committed versions
	 * If no versions have been committed
	 * @return
	 */
	public String printMasterVersions() {
		Stack<String> temp = new Stack<>();
		int version = 0;
		String masterVersions = "";

		//Output stack contents
		if(master.isEmpty()) {
			masterVersions += "Project has no committed versions";
		}
		else {
			//Use temp stack to preserve both content and order of master stack
			//Pop master stack until it has just one element - use peek to examine this element
			//NOTE:  No need to pop stack until it is empty, though you could
			for (int i = master.size(); i > 1; i--) {
				temp.push(master.pop());
			}
			masterVersions += "\n\t" + ++version + ": " + master.peek();
			
			//Now pop temp stack until it is empty, pushing content back onto master stack
			//master stack will still be in its original order
			while(!temp.isEmpty()){
				masterVersions += "\n\t" + ++version + ": " + temp.peek();
				master.push(temp.pop());
			}
		}
		return masterVersions;
		
	}
	
	/**
	 * toString method that returns Github instance variable, code, the current branch
	 * being edited
	 */
	public String toString(){
		return code;
	}

}
