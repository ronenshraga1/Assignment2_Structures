
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
	private ArrayElement<Product>[] championByQuality;
	private int globalQuality;
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
		championByQuality  = new ArrayElement[6];
		globalQuality      = -1;
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
		int addedprice = 0;
//		if(max != null){
//			int maxQuality = max.quality();
//			addedprice = raisePrice[maxQuality] != -1 ? raisePrice[maxQuality]:0;
//		}
		if (championByQuality[product.quality()] == null
				|| effectivePrice(newproduct) > effectivePrice(championByQuality[product.quality()])) {
			championByQuality[product.quality()] = newproduct;
		}
		updateGlobalChampion(newproduct.satelliteData().quality());
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
			if(delete.satelliteData().equals(delete.satelliteData())){
				for (int i = 0; i < productArray.size(); i++) {
					ArrayElement<Product> e = productArray.get(i);
					if (e.satelliteData().quality() == delete.satelliteData().quality()) {
						if (championByQuality[delete.satelliteData().quality()] == null
								|| effectivePrice(e) > effectivePrice(championByQuality[delete.satelliteData().quality()])) {
							championByQuality[delete.satelliteData().quality()] = e;
						}
					}
				}

			}
			qualityCount[delete.satelliteData().quality()]--;
			sumOfQualities = sumOfQualities - delete.satelliteData().quality();
			calculateAvg();
			recomputeGlobalChampion();
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
	private int effectivePrice(ArrayElement<Product> e) {
		Product p = e.satelliteData();
		return p.price() + (raisePrice[p.quality()] - e.getInsertionRaise());
	}
	private void recomputeGlobalChampion() {
		globalQuality = -1;
		int best = Integer.MIN_VALUE;
		for (int q = 0; q < 6; q++) {
			if (championByQuality[q] != null) {
				int eff = effectivePrice(championByQuality[q]);
				if (eff > best) {
					best = eff;
					globalQuality = q;
				}
			}
		}
	}


	private void updateGlobalChampion(int q) {
		if (globalQuality < 0
				|| effectivePrice(championByQuality[q])
				> effectivePrice(championByQuality[globalQuality])) {
			globalQuality = q;
		}
	}

	public void raisePrice(int raise, int quality) {
		raisePrice[quality] += raise;
		if (championByQuality[quality] != null) {
			updateGlobalChampion(quality);
		}
	}

	public Product mostExpensive() {
		if(globalQuality < 0){
			return null;
		} else{
			return championByQuality[globalQuality].satelliteData();
		}
	}

}
