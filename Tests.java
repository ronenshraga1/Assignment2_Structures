public class Tests {
    public static void testDS1(){
        MyFirstDataStructure<String> ds = new MyFirstDataStructure<>(10);

        // Insert elements
        ds.insert(new Element<>(5, "five"));
        ds.insert(new Element<>(3, "three"));
        ds.insert(new Element<>(8, "eight"));
        ds.insert(new Element<>(1, "one"));
        ds.insert(new Element<>(10, "ten"));

        // Check first
        System.out.println("First: " + ds.first()); // Should be 5

        // Check last
        System.out.println("Last: " + ds.last()); // Should be 10

        // Check maximum
        System.out.println("Maximum: " + ds.maximum()); // Should be 10

        // Remove first inserted (5)
        ds.findAndRemove(5);
        System.out.println("After removing 5:");
        System.out.println("First: " + ds.first()); // Should be 3
        System.out.println("Maximum: " + ds.maximum()); // Still 10

        // Remove maximum (10)
        ds.findAndRemove(10);
        System.out.println("After removing 10:");
        System.out.println("Maximum: " + ds.maximum()); // Should be 8
        System.out.println("Last: " + ds.last()); // Should be 1

        // Remove everything else
        ds.findAndRemove(1);
        ds.findAndRemove(3);
        ds.findAndRemove(8);
        System.out.println("After removing all:");
        System.out.println("First: " + ds.first()); // Should be null
        System.out.println("Last: " + ds.last());   // Should be null
        System.out.println("Maximum: " + ds.maximum()); // Should be null
    }
    public static void test2(){
        MySecondDataStructure ds = new MySecondDataStructure(10);

        // 1) Empty-DS behavior
        System.out.println("medianQuality on empty: " + ds.medianQuality() + " (expected -1)");
        System.out.println("avgQuality on empty: " + ds.avgQuality() + " (expected -1)");
        System.out.println("mostExpensive on empty: " + ds.mostExpensive() + " (expected null)");

        // 2) Insert products (id, quality, price, name)
        Product p1 = new Product(1, 2, 100, "p1");
        Product p2 = new Product(2, 3, 150, "p2");
        Product p3 = new Product(3, 2, 120, "p3");
        ds.insert(p1);
        ds.insert(p2);
        ds.insert(p3);

        // 3) Print avgQuality and medianQuality
        System.out.println("After inserts avgQuality: " + ds.avgQuality() + " (expected ~2.3333)");
        System.out.println("After inserts medianQuality: " + ds.medianQuality() + " (expected 2)");

        // 4) mostExpensive
        System.out.println("mostExpensive id: " + (ds.mostExpensive() != null ? ds.mostExpensive().id() : null) + " (expected 2)");

        // 5) Raise price of quality 2 by 50
        ds.raisePrice(50, 2);
        System.out.println("After raisePrice(50,2) mostExpensive id: " + (ds.mostExpensive() != null ? ds.mostExpensive().id() : null) + " (expected 3)");

        // 6) Remove product 3
        ds.findAndRemove(3);
        System.out.println("After remove(3) mostExpensive id: " + (ds.mostExpensive() != null ? ds.mostExpensive().id() : null) + " (expected 2)");

        // 7) Insert product 4 (quality 2)
        Product p4 = new Product(4, 2, 200, "p4");
        ds.insert(p4);
        System.out.println("After insert(p4) mostExpensive id: " + (ds.mostExpensive() != null ? ds.mostExpensive().id() : null) + " (expected 4)");

    }
    public static void test3(){
        MySecondDataStructure ds = new MySecondDataStructure(10);

        // 1) Empty-DS behavior
        System.out.println("medianQuality on empty: " + ds.medianQuality() + " (expected -1)");
        System.out.println("avgQuality on empty: " + ds.avgQuality() + " (expected -1)");
        System.out.println("mostExpensive on empty: " + ds.mostExpensive() + " (expected null)");

        // 2) Insert products (id, quality, price, name)
        Product p1 = new Product(1, 2, 100, "p1");
        Product p2 = new Product(2, 3, 150, "p2");
        Product p3 = new Product(3, 2, 120, "p3");
        ds.insert(p1);
        ds.insert(p2);
        ds.insert(p3);

        // 3) Print avgQuality and medianQuality
        System.out.println("After inserts avgQuality: " + ds.avgQuality() + " (expected ~2.3333)");
        System.out.println("After inserts medianQuality: " + ds.medianQuality() + " (expected 2)");

        // 4) mostExpensive
        System.out.println("mostExpensive id: " + (ds.mostExpensive() != null ? ds.mostExpensive().id() : null) + " (expected 2)");

        // 5) Raise price of quality 2 by 50
        ds.raisePrice(50, 2);
        System.out.println("After raisePrice(50,2) mostExpensive id: " + (ds.mostExpensive() != null ? ds.mostExpensive().id() : null) + " (expected 3)");
        System.out.println("After raisePrice medianQuality: " + ds.medianQuality() + " (expected 2)");

        // 6) Remove product 3
        ds.findAndRemove(3);
        System.out.println("After remove(3) mostExpensive id: " + (ds.mostExpensive() != null ? ds.mostExpensive().id() : null) + " (expected 2 or 1 on tie)");
        System.out.println("After remove(3) medianQuality: " + ds.medianQuality() + " (expected 2)");

        // 7) Insert product 4 (quality 2)
        Product p4 = new Product(4, 2, 200, "p4");
        ds.insert(p4);
        System.out.println("After insert(p4) mostExpensive id: " + (ds.mostExpensive() != null ? ds.mostExpensive().id() : null) + " (expected 4)");
        System.out.println("After insert(p4) medianQuality: " + ds.medianQuality() + " (expected 2)");

        // 8) Insert product 5 (quality 5) to test even count median
        Product p5 = new Product(5, 5, 80, "p5");
        ds.insert(p5);
        System.out.println("After insert(p5) medianQuality: " + ds.medianQuality() + " (expected 2)");

    }
    public static void testReverse(){
        // 1) Create a MyArray of capacity 8 and insert 8 elements
        MyArray<Integer> arr = new MyArray<>(8);
        arr.insert(new ArrayElement<>(1, 10000));
        arr.insert(new ArrayElement<>(2, 5));
        arr.insert(new ArrayElement<>(3, 200));
        arr.insert(new ArrayElement<>(4, 4));
        arr.insert(new ArrayElement<>(5, 1));
        arr.insert(new ArrayElement<>(6, 100));
        arr.insert(new ArrayElement<>(7, 2));
        arr.insert(new ArrayElement<>(8, 3));

        // 2) Print before reversing
        System.out.println("Before reverse: " + arr.toString());
        // Expected: [10000][5][200][4][1][100][2][3]

        // 3) Call reverse()
        arr.reverse();

        // 4) Print after reversing
        System.out.println("After reverse:  " + arr.toString());
        // Expected: [3][2][100][1][4][200][5][10000]

    }
    public static void main(String[] args) {
        testReverse();
    }
}
