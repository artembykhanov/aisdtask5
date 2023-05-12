public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder test = new StringBuilder();
        int k = 1;
        int countTest=5;
        test.append("test");
        for (int i = 0; i < countTest; i++) {
            test.append(k);
            test.append(".txt");
            System.out.println(test + "---------------------------------------------------");
            Node trees = Node.readFromFile(String.valueOf(test));
            trees.printTree();
            test.delete(4,9);
            k++;
        }
    }
}
