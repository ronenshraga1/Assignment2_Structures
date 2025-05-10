
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
	ArrayElement<Product> max;
	private ArrayElement<Product>[] maxByQuality;
	private int maxQualityIndex;
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
		maxByQuality = new ArrayElement[6];
		maxQualityIndex = -1;
		max = null;
		sumOfQualities = 0;
		qualityCount = new int[6];
	}
	
	public void insert(Product product) {
		Element<Product> prod = new Element<Product>(0,product);
		ArrayElement<Product> newproduct = new ArrayElement<>(prod);
		newproduct.setInsertionRaise(raisePrice[product.quality()]);
		productArray.insert(newproduct);
		sumOfQualities += product.quality();
		calculateAvg();
		qualityCount[product.quality()]++;
		if (maxByQuality[product.quality()] == null || calculatePrice(newproduct) > calculatePrice(maxByQuality[product.quality()])) {
			maxByQuality[product.quality()] = newproduct;
		}
		updateMax(newproduct.satelliteData().quality());
	}
	
	public void findAndRemove(int id) {
		ArrayElement<Product> delete = null;
		for(int i =0;i<productArray.size();i++){
			if(productArray.get(i).satelliteData().id() == id ){
				delete = productArray.get(i);
				productArray.delete(productArray.get(i));
				break;
			}
		}
		if (delete != null) {
			if(delete == maxByQuality[delete.satelliteData().quality()]){
				maxByQuality[delete.satelliteData().quality()] = null;
				for (int i = 0; i < productArray.size(); i++) {
					ArrayElement<Product> e = productArray.get(i);
					if (e.satelliteData().quality() == delete.satelliteData().quality()) {
						if (maxByQuality[delete.satelliteData().quality()] == null
								|| calculatePrice(e) > calculatePrice(maxByQuality[delete.satelliteData().quality()])) {
							maxByQuality[delete.satelliteData().quality()] = e;
						}
					}
				}

			}
			qualityCount[delete.satelliteData().quality()]--;
			sumOfQualities = sumOfQualities - delete.satelliteData().quality();
			calculateAvg();
			calculateMaxByQuality();
		}
	}
	private void calculateAvg(){
		if(productArray.size()==0){
			avg = 0;
		} else{
			avg = (double) sumOfQualities / productArray.size();
		}
	}
	public int medianQuality() {
		if (productArray.size() == 0) return -1;
		int target = (productArray.size() + 1) / 2;
		int cumulative = 0;
		for (int q = 0; q <= 5; q++) {
			cumulative += qualityCount[q];
			if (cumulative >= target) {
				return q;
			}
		}
		return -1;
	}
	
	public double avgQuality() {
		if (productArray.size()==0) return -1;
		return avg;
	}
	private int calculatePrice(ArrayElement<Product> e) {
		Product p = e.satelliteData();
		return p.price() + (raisePrice[p.quality()] - e.getInsertionRaise());
	}
	private void calculateMaxByQuality() {
		maxQualityIndex = -1;
		int best = -1;
		for (int q = 0; q < 6; q++) {
			if (maxByQuality[q] != null) {
				int price = calculatePrice(maxByQuality[q]);
				if (price > best) {
					best = price;
					maxQualityIndex = q;
				}
			}
		}
	}

	private void updateMax(int q) {
		if (maxQualityIndex < 0 || calculatePrice(maxByQuality[q]) > calculatePrice(maxByQuality[maxQualityIndex])) {
			maxQualityIndex = q;
		}
	}

	public void raisePrice(int raise, int quality) {
		raisePrice[quality] += raise;
		if (maxByQuality[quality] != null) {
			updateMax(quality);
		}
	}

	public Product mostExpensive() {
		if(maxQualityIndex < 0){
			return null;
		} else{
			return maxByQuality[maxQualityIndex].satelliteData();
		}
	}

}
