
public interface GithubADT {
	public void insert(int pos, String add);
	public void delete(int pos, int count);
	public void undo();
	public void pull(int version);
	public void commit();
	public int getMasterSize();
	public String printMasterVersions();
	public String toString();
}
