import java.util.LinkedList;
import java.util.List;

/**
 * An data structure that resembles an n-ary tree. It represents blocks of code
 * and its nested blocks (and statements).
 * 
 * @author Andrew Liu
 * 
 * @param <T>
 *            The type of statement each block holds. Usually a String.
 */
public class CodeBlock<T> {

	private String prototype;
	private List<T> statements;
	private List<CodeBlock<T>> children;

	/**
	 * Default constructor.
	 */
	private CodeBlock() {
		this.statements = new LinkedList<T>();
		this.children = new LinkedList<CodeBlock<T>>();
	}

	/**
	 * Constructor.
	 * 
	 * @param prototype
	 *            The "prototype" for the block. It can be a function prototype,
	 *            or a class declaration, etc...
	 */
	public CodeBlock(String prototype) {
		this();
		this.prototype = prototype;
	}

	/**
	 * Copy constructor.
	 * 
	 * @param copy
	 *            CodeBlock to copy.
	 */
	public CodeBlock(CodeBlock<T> copy) {
		this.prototype = copy.prototype;
		this.addStatements(copy.statements);
		for (CodeBlock<T> child : copy.getChildren()) {
			this.addChild(new CodeBlock<T>(child));
		}
	}

	/**
	 * Gets the prototype for this block.
	 * 
	 * @return The block's prototype.
	 */
	public String getPrototype() {
		return this.prototype;
	}

	/**
	 * Does a depth-first search to get the prototypes of this code block and
	 * all child code blocks.
	 * 
	 * @return All prototypes.
	 */
	public List<String> getPrototypesRecursively() {
		List<String> prototypes = new LinkedList<String>();
		prototypes.add(this.prototype);
		for (CodeBlock<T> child : this.children) {
			prototypes.addAll(child.getPrototypesRecursively());
		}
		return prototypes;
	}

	/**
	 * Changes the block's prototype.
	 * 
	 * @param prototype
	 *            The new prototype.
	 */
	public void setPrototype(String prototype) {
		this.prototype = prototype;
	}

	/**
	 * Adds the statement to the list of statements for the block.
	 * 
	 * @param statement
	 *            The statement to add.
	 */
	public void addStatement(T statement) {
		this.statements.add(statement);
	}

	/**
	 * Adds multiple statements to the list of statements.
	 * 
	 * @param statements
	 *            The list of statements to add.
	 */
	public void addStatements(List<T> statements) {
		for (T statement : statements) {
			this.statements.add(statement);
		}
	}

	/**
	 * Gets the list of statements for the current block.
	 * 
	 * @return The list of statements for the current block.
	 */
	public List<T> getStatements() {
		return new LinkedList<T>(this.statements);
	}

	/**
	 * Gets a list of all statements for the block and its children depth-first.
	 * 
	 * @return List of all statements.
	 */
	public List<T> getStatementsRecursively() {
		List<T> allStatements = this.getStatements();
		for (CodeBlock<T> child : this.children) {
			allStatements.addAll(child.getStatementsRecursively());
		}
		return allStatements;
	}

	/**
	 * Adds a new child to the code block.
	 * 
	 * @param child
	 *            The new child.
	 */
	public void addChild(CodeBlock<T> child) {
		this.children.add(child);
	}

	/**
	 * Gets the list of children for the code block.
	 * 
	 * @return The list of code block children.
	 */
	public List<CodeBlock<T>> getChildren() {
		List<CodeBlock<T>> children = new LinkedList<CodeBlock<T>>();
		for (CodeBlock<T> block : this.children) {
			children.add(new CodeBlock<T>(block));
		}
		return children;
	}

	/**
	 * Calculates the height of this tree structure.
	 * 
	 * @return The height of the tree.
	 */
	public int getHeight() {
		int height = 1;
		int max = 0;
		for (CodeBlock<T> child : this.children) {
			int subHeight = child.getHeight();
			if (subHeight > max) {
				max = subHeight;
			}
		}
		return height + max;
	}

	/**
	 * Gets the total number of code block children including the current block.
	 * 
	 * @return The total number of nodes in the tree.
	 */
	public int getTotalNumBlocks() {
		int total = 1;
		for (CodeBlock<T> child : this.children) {
			total += child.getTotalNumBlocks();
		}
		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "I'll do this later...";
	}
}