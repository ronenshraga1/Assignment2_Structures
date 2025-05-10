/**
 * @param <T> The type of the satellite data of the elements in the data structure.
 */
public class MyFirstDataStructure<T> {
	/*
     * You may add any fields that you wish to add.
     * Remember that the use of built-in Java classes is not allowed,
     * the only variables types you can use are:
     * 	-	the given classes in the assignment
     * 	-	basic arrays
     * 	-	primitive variables
     */
	private MyAVLTree<ListLink<T>> tree;
	private TreeNode<ListLink<T>> maxNode;
	private ListLink<T> head;
	private ListLink<T> tail;
	/***
     * This function is the Init function.
	 * @param N The maximum number of elements in the data structure at each time.
     */
	public MyFirstDataStructure(int N) {
		tree    = new MyAVLTree<>();
		maxNode = null;
		head    = null;
		tail    = null;
	}

	public void insert(Element<T> x) {
		ListLink<T> link = new ListLink<>(x);
		if (head == null) {
			head = link;
			tail = link;
		} else {
			tail.setNext(link);
			link.setPrev(tail);
			tail = link;
		}
		TreeNode<ListLink<T>> node = new TreeNode<>(link.key(), link);
		tree.insert(node);
		if (maxNode == null || node.key() > maxNode.key()) {
			maxNode = node;
		}
	}

	public void findAndRemove(int k) {
		TreeNode<ListLink<T>> node = tree.search(k);
		if (node == null) return;
		ListLink<T> link = node.satelliteData();
		boolean wasMax = (node == maxNode);
		TreeNode<ListLink<T>> newMax = null;
		if (wasMax) {
			newMax = oldMax(node);
		}
		tree.delete(node);
		if (link.getPrev() != null) {
			link.getPrev().setNext(link.getNext());
		} else {
			head = link.getNext();
		}
		if (link.getNext() != null) {
			link.getNext().setPrev(link.getPrev());
		} else {
			tail = link.getPrev();
		}
		if (wasMax) {
			maxNode = newMax;
		}
	}

	public Element<T> maximum() {
		if (maxNode == null) {
			return null;
		}
		return maxNode.satelliteData();
	}
	public Element<T> first() {
		return head;
	}

	public Element<T> last() {
		return tail;
	}
	private TreeNode<ListLink<T>> oldMax(TreeNode<ListLink<T>> node) {
		if (node.getLeft() != null) {
			TreeNode<ListLink<T>> leftNode = node.getLeft();
			while (leftNode.getRight() != null) {leftNode = leftNode.getRight();}
			return leftNode;
		}
		TreeNode<ListLink<T>> parent = node.getParent();
		while (parent != null && node == parent.getLeft()) {
			node = parent;
			parent = parent.getParent();
		}
		return parent;
	}


}
