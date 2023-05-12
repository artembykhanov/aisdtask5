public class Main {
    public static void main(String[] args) throws Exception {
        Node trees = Node.readFromFile("test.txt");
        trees.printTree();
    }
}
