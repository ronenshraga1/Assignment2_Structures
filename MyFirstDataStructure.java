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
	MyAVLTree<T> tree;
	TreeNode<T>[] max_array;
	TreeNode<T>[] last_array;
	TreeNode<T>[] first_array;
	int maxIndex;
	int lastIndex;
	int firstIndex;
	TreeNode<T> maximum;
	TreeNode<T> first;
	TreeNode<T> last;
	int size;
	int maxSize;
	/***
     * This function is the Init function.
	 * @param N The maximum number of elements in the data structure at each time.
     */
	public MyFirstDataStructure(int N) {
		tree = new MyAVLTree<>();
		max_array = new TreeNode[N];
		last_array = new TreeNode[N];;
		first_array = new TreeNode[N];
		maximum = null;
		first = null;
		last = null;
		size = 0;
		maxIndex = 0;
		lastIndex = 0;
		firstIndex = 0;
		maxSize = N;
	}
	
	public void insert(Element<T> x) {
//		if(size == maxSize){
//			tree.delete(last);
//			size--;
//		}
		TreeNode<T> newnode = new TreeNode<>(x);
		if(first == null){
			first = newnode;
			max_array[maxIndex] = newnode;
			maxIndex++;
		}
		tree.insert(newnode);
		//last =  newnode;
		last_array[lastIndex] = newnode;
		lastIndex++;
		size++;
		if(last_array[lastIndex-1].key() > max_array[maxIndex-1].key()){
			max_array[maxIndex] = newnode;
			maxIndex++;
		}
	}
	
	public void findAndRemove(int k) {
		TreeNode<T> deleteNode = tree.search(k);
		if(deleteNode != null){
			if(deleteNode == last_array[lastIndex-1]){
				last_array[lastIndex-1] = null;
				lastIndex--;
			}
			if(deleteNode == max_array[maxIndex-1]){
				max_array[maxIndex-1] = null;
				maxIndex--;
			}
			if (deleteNode == last_array[firstIndex]) {
				last_array[firstIndex] = null;
				firstIndex++;
				first = last_array[firstIndex];
			}

			tree.delete(deleteNode);
		}

	}

	public Element<T> maximum() {
		if(maxIndex >0){
			return max_array[maxIndex-1];
		} else
			return null;
	}

	public Element<T> first() {
		return first;
	}

	public Element<T> last() {
		if(lastIndex > 0){
			return last_array[lastIndex-1];
		} else{
			return null;
		}

	}

}
