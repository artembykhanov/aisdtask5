
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Node {
    private String value;
    private List<Node> children;
    private int level; // новое поле для уровня в дереве

    public Node(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        children.add(child);
        child.setLevel(level + 1); // вычисляем уровень для добавленного узла
    }

    public String getValue() {
        return value;
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public Node getChild(int index) {
        return children.get(index);
    }

    public static Node readFromFile(String filename) throws IOException {
        Node root = null;
        Node currentNode = null;
        Stack<Node> stack = new Stack<>();

        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int level = 0;
            while (line.charAt(level) == ' ') {
                level++;
            }
            String value = line.trim();//в value добавляем значение без пробелов

            Node newNode = new Node(value);
            //Присваиваем корень дерева, тот у кого нет пробелов(вложенности)
            if (level == 0) {
                root = newNode;
                stack.clear();
                stack.push(root);
            } else {
                //Если уровень вложенности последнего узла больше, чем того кого проверяем сейчас (newNode), то из стека убираем последний узел
                while (stack.peek().getLevel() > level - 1) {
                    stack.pop();
                }
                //Тут два исхода либо мы почистили стек(зашли в условие while), то того кого сейчас (newNode) проверяем добавляем к наследникам,
                //того кто остался в стеке, то есть наименьший уровень вложенности, по сравнению с (newNode).
                // (В этом примере наименьший уровень вложенности у А=0)

                // Другой исход, это если у (newNode) уровень вложенности больше, чем у последнего узла,
                // значит мы не заходим (while) и (newNode) является его наследником
                currentNode = stack.peek();
                currentNode.addChild(newNode);
                stack.push(newNode);
            }
        }

        scanner.close();
        return root;
    }


    //Рекурсией печатаем дерево в консоль
    public void printTree() {
        printSubtree(this, 0);
    }

    private void printSubtree(Node node, int indentLevel) {
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            space.append("  "); // Два пробела на каждый уровень вложенности
        }
        System.out.println(space + node.getValue());
        // цикл for-each, который перебирает всех дочерних узлов child
        // вызывается рекурсивный вызов printSubtree(child, indentLevel + 1), увеличивая уровень отступа на единицу
        for (Node child : node.getChildren()) {
            printSubtree(child, indentLevel + 1);
        }
    }

}
