import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class GithubTest {

	@Test 
	public void testInsert1() {
		Github evaluator = new Github();
		evaluator.insert(0, "System.out.println");
		assertEquals("System.out.println", evaluator.toString());
	}
	
	
	@Test
	public void testInsert3() {
		Github evaluator = new Github("System.out.println");
		//Must use escape for " - that is \" to print a double quote
		evaluator.insert(18, "(\"Testing insert...\");");
		assertEquals("System.out.println(\"Testing insert...\");", evaluator.toString());
	}

	
	@Test
	public void testInsert5() {
		Github evaluator = new Github("System.out.println();");
		evaluator.insert(19, "\"More testing...\"");
		assertEquals("System.out.println(\"More testing...\");", evaluator.toString());
	}
	

	@Test
	public void testInsert7() {
	  assertThrows(InvalidGithubException.class, () -> { 
		Github evaluator = new Github("System.out.println();");
		evaluator.commit();
		evaluator.insert(-1, "\"More testing...\"");
	  });
	}
	


	
	@Test
	public void testDelete2() {
		Github evaluator = new Github("System.out.println();");
		evaluator.delete(0, 23);
		assertEquals("", evaluator.toString());
	}
	

	@Test
	public void testDelete4() {
		Github evaluator = new Github("System.out.println();");
		evaluator.delete(10, 11);
		assertEquals("System.out", evaluator.toString());
	}
	
	
	@Test
	public void testDelete6() {
		Github evaluator = new Github("System.out.println();");
		evaluator.delete(20, 1);
		assertEquals("System.out.println()", evaluator.toString());
	}
	

	@Test
	public void testDelete9() {
	  assertThrows(InvalidGithubException.class, () -> { 
		Github evaluator = new Github("System.out.println();");
		evaluator.commit();
		evaluator.delete(19, 2);
	  });
	}
	

	
	@Test
	public void testDelete11() {
	  assertThrows(InvalidGithubException.class, () -> { 
		Github evaluator = new Github("System.out.println();");
		evaluator.delete(21, 2);
	  });

	}
	
	@Test
	public void testDelete12() {
	  assertThrows(InvalidGithubException.class, () -> { 
		Github evaluator = new Github("System.out.println();");
		evaluator.delete(5, -1);
	  });

	}
	

	
	@Test
	public void testUndo3() {
		Github evaluator = new Github("System.out.println();");
		evaluator.insert(19, "\"Testing undo...\"");
		evaluator.delete(27, 5);
		evaluator.undo();
		assertEquals("System.out.println(\"Testing undo...\");", evaluator.toString());
	}
	
	@Test
	public void testUndo4() {
		Github evaluator = new Github("System.out.println();");
		evaluator.delete(16, 2);
		evaluator.insert(17, "\"Testing undo...\"");
		evaluator.undo();
		assertEquals("System.out.print();", evaluator.toString());
	}
	
	
	@Test
	public void testUndo6() {
		Github evaluator = new Github("System.out.print();");
		evaluator.insert(17, "\"\"");
		evaluator.insert(18, "Testing new code");
		evaluator.delete(26, 4);
		evaluator.delete(22, 3);
		evaluator.insert(23, "Java ");
		evaluator.insert(16, "ln");
		evaluator.delete(29, 5);
		evaluator.undo();
		evaluator.undo();
		evaluator.insert(32, "!");
		evaluator.delete(18, 5);
		evaluator.insert(22, " 10");
		evaluator.insert(30, " is great");
		evaluator.undo();
		evaluator.undo();
		evaluator.insert(27, " Rocks");
		evaluator.delete(22, 5);
		assertEquals("System.out.print(\"Java Rocks!\");", evaluator.toString());
	}
	
	
	@Test 
	public void testUndo8() {
	  assertThrows(InvalidGithubException.class, () -> { 
		Github evaluator = new Github("System.out.println();");
		evaluator.insert(19, "\"Testing undo...\"");
		evaluator.delete(27, 5);
		evaluator.commit();
		evaluator.undo();
	  });
	}
	
	
	@Test
	public void testPull2() {
		Github evaluator = new Github("System.out.println();");
		evaluator.commit();
		evaluator.pull(1);
		evaluator.delete(16,2);
		evaluator.insert(17, "\"\"");
		assertEquals("System.out.print(\"\");", evaluator.toString());
	}
	
	
	@Test
	public void testPull4() {
		Github evaluator = new Github("System.out.println(\"Happy Days\");");
		evaluator.commit();
		evaluator.pull(1);
		evaluator.delete(16,2);
		evaluator.commit();
		evaluator.pull(1);
		evaluator.delete(20, 10);
		evaluator.commit();
		evaluator.pull(3);
		evaluator.delete(16, 2);
		evaluator.commit();
		evaluator.pull(4);
		assertEquals("System.out.print(\"\");", evaluator.toString());
	}
	
	@Test
	public void testPull5() {
	  assertThrows(InvalidGithubException.class, () -> { 
		Github evaluator = new Github("System.out.println(\"Happy Days\");");
		evaluator.commit();
		evaluator.pull(1);
		evaluator.delete(16,2);
		evaluator.commit();
		evaluator.pull(1);
		evaluator.pull(2);
	  });
	}
	
}
