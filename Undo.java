
public class Undo {
	private String code;
	private int start;
	private int count;
	private int action;
	
	/**
	 * Undo constructor
	 * @param code - branch code that is being edited
	 * @param start - index of edited code's starting position
	 * @param count - number of characters edited
	 * @param action - action to apply should UNDO be selected
	 *                 1 is for insert (code was deleted so to undo it is to "insert" it back in)
	 *                 2 is for delete (code was inserted so to undo it is to "delete" it)
	 */
	public Undo(String code, int start, int count, int action) {
		this.code = new String(code);
		this.start = start;
		this.count = count;
		this.action = action;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String str) {
		this.code = str;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}
	
	
}
