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
	// — map key → ListLink via an AVL keyed by key, storing each ListLink<T> as “satelliteData”
	// AVL tree storing ListLink<T> nodes, keyed by element.key()
	private MyAVLTree<ListLink<T>> tree;
	private TreeNode<ListLink<T>> maxNode;          // pointer to current maximum in the tree

	// Our own doubly-linked list to track insertion order
	private ListLink<T> head;  // oldest inserted still present
	private ListLink<T> tail;  // newest inserted still present
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
		// 1) wrap into a ListLink and append to tail of our list
		ListLink<T> link = new ListLink<>(x);
		if (head == null) {
			head = tail = link;
		} else {
			tail.setNext(link);
			link.setPrev(tail);
			tail = link;
		}

		// 2) insert into AVL tree keyed by x.key()
		TreeNode<ListLink<T>> node = new TreeNode<>(link.key(), link);
		tree.insert(node);

		// 3) update maximum pointer in O(1)
		if (maxNode == null || node.key() > maxNode.key()) {
			maxNode = node;
		}
	}

	public void findAndRemove(int k) {
		TreeNode<ListLink<T>> node = tree.search(k);
		if (node == null) return;

		// record link before any tree mutations
		ListLink<T> link = node.satelliteData();
		boolean wasMax = (node == maxNode);
		TreeNode<ListLink<T>> newMax = null;
		if (wasMax) {
			newMax = predecessor(node);
		}

		// delete from AVL tree (may restructure nodes internally)
		tree.delete(node);

		// unlink from our insertion-order list in O(1)
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

		// update max pointer
		if (wasMax) {
			maxNode = newMax;
		}
	}

	public Element<T> maximum() {
		return (maxNode == null ? null : maxNode.satelliteData());
	}
	public Element<T> first() {
		return head;
	}

	public Element<T> last() {
		return tail;
	}
	// — BST predecessor in O(height)=O(log n)
	private TreeNode<ListLink<T>> predecessor(TreeNode<ListLink<T>> x) {
		if (x.getLeft() != null) {
			TreeNode<ListLink<T>> p = x.getLeft();
			while (p.getRight() != null) p = p.getRight();
			return p;
		}
		TreeNode<ListLink<T>> p = x.getParent();
		while (p != null && x == p.getLeft()) {
			x = p;
			p = p.getParent();
		}
		return p;
	}


}
