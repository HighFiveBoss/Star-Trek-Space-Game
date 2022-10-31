import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game {
    private enigma.console.Console cn = Enigma.getConsole("Star Trek", 150, 40, 15);
    private KeyListener klis;
    private int keypr;   // key pressed?
    private int rkey;    // key   (for press/release)
    private Map map;
    private Queue<Object> q;
    private Player player;
    private Backpack backpack;
    private int sleep_counter;
    private double time_counter;

    Game () throws FileNotFoundException, InterruptedException {
        backpack = new Backpack();
        player = new Player();
        klis = new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if(keypr==0) {
                    keypr=1;
                    rkey=e.getKeyCode();
                }
            }
            public void keyReleased(KeyEvent e) {}
        };
        cn.getTextWindow().addKeyListener(klis);
        map = new Map(cn);
        map.addPlayer();
        writeInputQueue(20);
        map.writeMap();
        writeTime();
        map.writePlayerLives();
        writeInputQueueToScreen();
        backpack.writeBackpack(cn, 70, 13);
        while(player.getLives() != 0) {
            movements();
        }
    }
    /*private void menu() {
        setCursor(65, 20);
        System.out.println("Star Trek Star Wars");
        setCursor(67, 21);
        System.out.println("Press 1 to play.");

    }*/
    private void movements() throws InterruptedException {
        if (player.getEnergy()) {
            Thread.sleep(250);
            player.subtractTime(0.25);
            sleep_counter += 1;
            time_counter += 0.25;
        }
        else {
            Thread.sleep(500);
            player.subtractTime(0.5);
            sleep_counter += 2;
            time_counter += 0.5;
        }
        writeTime();
        map.writePlayerLives();
        if(sleep_counter % 2 == 0) {
            map.moveChars();
            sleep_counter = 0;
        }
        if(time_counter >= 3) {
            time_counter = 0;
            addElementToInputQueue();
        }
        if(keypr==1) {    // if keyboard button pressed
            setCursor(0, 0);
            if(rkey==KeyEvent.VK_LEFT) {
                if (map.checkMapIndexes(map.getPlayerLine(), map.getPlayerColumn() - 1) == ' ') {
                    map.movePlayerLeft();
                }
            }
            else if(rkey==KeyEvent.VK_RIGHT) {
                if (map.checkMapIndexes(map.getPlayerLine(), map.getPlayerColumn() + 1) == ' ') {
                    map.movePlayerRight();
                }
            }
            else if(rkey==KeyEvent.VK_UP) {
                if (map.checkMapIndexes(map.getPlayerLine() - 1, map.getPlayerColumn()) == ' ') {
                    map.movePlayerUp();
                }
            }
            else if(rkey==KeyEvent.VK_DOWN) {
                if (map.checkMapIndexes(map.getPlayerLine() + 1, map.getPlayerColumn()) == ' ') {
                    map.movePlayerDown();
                }
            }
            else if(rkey==KeyEvent.VK_W) {
                if (map.checkMapIndexes(map.getPlayerLine() - 1, map.getPlayerColumn()) == ' ' && !backpack.isEmpty()) {
                    if (backpack.peekElement() == '=' || backpack.peekElement() == '*') {
                        setCursor(map.getPlayerColumn(), map.getPlayerLine() - 1);
                        System.out.print(backpack.getElement());
                        map.addElement(backpack.peekElement(), map.getPlayerLine() - 1, map.getPlayerColumn());
                    }
                    else backpack.getElement();
                }
            }
            else if(rkey==KeyEvent.VK_A) {
                if (map.checkMapIndexes(map.getPlayerLine(), map.getPlayerColumn() - 1) == ' ' && !backpack.isEmpty()) {
                    if (backpack.peekElement() == '=' || backpack.peekElement() == '*') {
                        setCursor(map.getPlayerColumn() - 1, map.getPlayerLine());
                        System.out.print(backpack.getElement());
                        map.addElement(backpack.peekElement(), map.getPlayerLine(), map.getPlayerColumn() - 1);
                    }
                    else backpack.getElement();
                }
            }
            else if(rkey==KeyEvent.VK_S) {
                if (map.checkMapIndexes(map.getPlayerLine() + 1, map.getPlayerColumn()) == ' ' && !backpack.isEmpty()) {
                    if (backpack.peekElement() == '=' || backpack.peekElement() == '*') {
                        setCursor(map.getPlayerColumn(), map.getPlayerLine() + 1);
                        System.out.print(backpack.getElement());
                        map.addElement(backpack.peekElement(), map.getPlayerLine() + 1, map.getPlayerColumn());
                    }
                    else backpack.getElement();
                }
            }
            else if(rkey==KeyEvent.VK_D) {
                if (map.checkMapIndexes(map.getPlayerLine(), map.getPlayerColumn() + 1) == ' ' && !backpack.isEmpty()) {
                    if (backpack.peekElement() == '=' || backpack.peekElement() == '*') {
                        setCursor(map.getPlayerColumn() + 1, map.getPlayerLine());
                        System.out.print(backpack.getElement());
                        map.addElement(backpack.peekElement(), map.getPlayerLine(), map.getPlayerColumn() + 1);
                    }
                    else backpack.getElement();
                }
            }
            backpack.writeBackpack(cn, 70, 13);
            keypr = 0;
        }
    }
    private void setCursor(int x, int y) {
        cn.getTextWindow().setCursorPosition(x,y);
    }
    private void generateInputQueue() {
        q = new LinkedList<>();
        Random r = new Random();
        while (q.size() != 35) {
            int temp = r.nextInt(1,41);
            if (temp <= 12) {
                q.add('1');
            }
            else if (temp <= 20) {
                q.add('2');
            }
            else if (temp <= 26) {
                q.add('3');
            }
            else if (temp <= 31) {
                q.add('4');
            }
            else if (temp <= 35) {
                q.add('5');
            }
            else if (temp <= 37) {
                q.add('=');
            }
            else if (temp <= 38) {
                q.add('*');
            }
            else {
                q.add('C');
            }
        }
    }
    private void writeInputQueue(int size) {
        generateInputQueue();
        for (int i = 0; i < size; i++) {
            map.addElement((char) q.poll());
        }
    }
    private void writeInputQueueToScreen() {
        setCursor(70, 7);
        cn.getTextWindow().output("<<<<<<<<<<<<<<<", new TextAttributes(Color.BLACK, Color.WHITE));
        setCursor(70, 9);
        cn.getTextWindow().output("<<<<<<<<<<<<<<<", new TextAttributes(Color.BLACK, Color.WHITE));
        for (int i = 0; i < 15; i++) {
            setCursor(70 + i, 8);
            System.out.print(q.peek());
            q.add(q.poll());
        }
    }
    private void writeTime() {
        setCursor(70, 11);
        System.out.print("Player Energy: " + (int) player.getTime());
    }
    private void addElementToInputQueue() {
        Random r = new Random();
        int temp = r.nextInt(1,41);
        if (temp <= 12) {
            q.add('1');
        }
        else if (temp <= 20) {
            q.add('2');
            }
        else if (temp <= 26) {
            q.add('3');
        }
        else if (temp <= 31) {
            q.add('4');
        }
        else if (temp <= 35) {
            q.add('5');
        }
        else if (temp <= 37) {
            q.add('=');
        }
        else if (temp <= 38) {
            q.add('*');
        }
        else {
            q.add('C');
        }
        map.addElement((char) q.poll());
        writeInputQueueToScreen();
    }
}
