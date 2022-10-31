import java.util.Stack;

public class Backpack {
    private static Stack<Object> s;
    private Player player;

    Backpack() {
        s = new Stack<Object>();
        player = new Player();
    }
    public void addElement(Object element) {
        s.push(element);
    }
    public char getElement() {
        return (char) s.pop();
    }
    public boolean isEmpty() {
        if (s.empty()) {
            return true;
        }
        else return false;
    }
    public char peekElement() {
        return (char) s.peek();
    }
    public void checkLastTwoElements() {
        if(s.size() >= 2) {
            Stack<Object> temp = new Stack<Object>();
            temp.push(s.peek());
            char last_item =(char) s.pop();
            if (last_item == (char) s.peek()) {
                s.pop();
                switch (last_item) {
                    case '2':
                        player.addTime(30);
                        break;
                    case '3':
                        s.push('=');
                        break;
                    case '4':
                        player.addTime(240);
                        break;
                    case '5':
                        s.push('*');
                        break;
                
                    default:
                        break;
                }
            }
            else {
                s.push(last_item);
            }   
        }
    }
    public void writeBackpack(enigma.console.Console cn, int x, int y) {
        Stack<Object> temp = new Stack<Object>();
        for (int i = 0; i < 8; i++) {
            cn.getTextWindow().setCursorPosition(x,y + i);
            System.out.print("|    |");
        }
        cn.getTextWindow().setCursorPosition(x,y + 8);
        System.out.print("+----+");
        for (int i = 0; i < 8; i++) {
            cn.getTextWindow().setCursorPosition(x + 3 ,y + 7 - i);
            if (!s.empty()) {
                System.out.print((char) s.peek());
                temp.push(s.pop());
            }
        }
        int size = temp.size();
        for (int i = 0; i < size; i++) {
            s.push(temp.pop());
        }
    }
}
