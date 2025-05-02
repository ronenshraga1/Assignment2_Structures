
public class MySecondDataStructure {
	/*
     * You may add any fields that you wish to add.
     * Remember that the use of built-in Java classes is not allowed,
     * the only variables types you can use are: 
     * 	-	the given classes in the assignment
     * 	-	basic arrays
     * 	-	primitive variables
     */
	MyArray<Product> productArray;
	double avg;
	int[] raisePrice;
	Product max;
	int sumOfQualities;
	int[] qualityCount;
	/***
     * This function is the Init function.
	 * @param N The maximum number of elements in the data structure at each time.
     */
	public MySecondDataStructure(int N) {
		productArray = new MyArray<>(N);
		avg = 0;
		raisePrice = new int[N];
		max = null;
		sumOfQualities = 0;
		qualityCount = new int[6];
	}
	
	public void insert(Product product) {
		Element<Product> prod = new Element<Product>(0,product);
		ArrayElement<Product> newproduct = new ArrayElement<>(prod);
		productArray.insert(newproduct);
		sumOfQualities += product.quality();
		calculateAvg();
		qualityCount[product.quality()]++;
		int addedprice = 0;
		if(max != null){
			int maxQuality = max.quality();
			addedprice = raisePrice[maxQuality] != -1 ? raisePrice[maxQuality]:0;
		}
		if(max == null || (product.price() > max.price() + addedprice)){
			max = product;
		}
	}
	
	public void findAndRemove(int id) {
		Product delete = null;
		for(int i =0;i<productArray.size();i++){
			if(productArray.get(i).satelliteData().id() == id ){
				delete = productArray.get(i).satelliteData();
				productArray.delete(productArray.get(i));
				break;
			}
		}
		if (delete != null) {
			if(delete.equals(max)){
				calculateNewMax();
			}
			qualityCount[delete.quality()]--;
			sumOfQualities = sumOfQualities - delete.quality();
			calculateAvg();
		}

	}
	private void calculateAvg(){
		if(productArray.size()==0){
			avg = 0;
		} else{
			avg = (double) sumOfQualities / productArray.size();
		}
	}
	private void calculateNewMax(){
		Product maximum =productArray.get(0).satelliteData();
		for(int j =0;j<productArray.size();j++){
			int quality = productArray.get(j).satelliteData().quality();
			int maxquality= maximum.quality();
			int addedmax = raisePrice[maxquality] != -1 ? raisePrice[maxquality]:0;
			int addedprice = raisePrice[quality] != -1 ? raisePrice[quality]:0;
			if(productArray.get(j).satelliteData().price()+addedprice>maximum.price()+addedmax){
				maximum = productArray.get(j).satelliteData();
			}
		}
		max = maximum;

	}
	public int medianQuality() {
		if (productArray.size() == 0) return -1;

		int target = (productArray.size() + 1) / 2; // ⌈n/2⌉
		int cumulative = 0;

		for (int q = 0; q <= 5; q++) {
			cumulative += qualityCount[q];
			if (cumulative >= target) {
				return q;
			}
		}

		return -1; // shouldn't happen
	}
	
	public double avgQuality() {
		if (productArray.size()==0) return -1;
		return avg;
	}

	public void raisePrice(int raise, int quality) {
		raisePrice[quality] += raise;
	}

	public Product mostExpensive() {
		return max;
	}

}
