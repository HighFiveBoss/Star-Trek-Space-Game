import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.awt.Color;
import enigma.console.TextAttributes;

public class Map extends Backpack {
    private Player player = new Player();
    private InfoForChars[][] info;
    private char[][] map;
    private int line, column;
    private int playerline, playercolumn;
    private enigma.console.Console cn;
    public Map(enigma.console.Console cn) throws FileNotFoundException {
        this.cn = cn;
        line = 0;
        column = 0;
        Scanner sc = new Scanner(new FileReader("Maze.txt"));
        while (sc.hasNextLine()) {
            line++;
            sc.nextLine();
        }
        sc.close();
        sc = new Scanner(new FileReader("Maze.txt"));
        column = sc.nextLine().length();
        map = new char[line][column];
        sc.close();
        sc = new Scanner(new FileReader("Maze.txt"));
        while (sc.hasNextLine()) {
            for (int i = 0; i < map.length; i++) {
                map[i] = sc.nextLine().toCharArray();
            }
        }
        sc.close();
        info = new InfoForChars[line][column];
    }
    public char[][] getMap() {
        return map;
    }
    public void setMap(char[][] map) {
        this.map = map;
    }
    public int getColumnCount() {
        return column;
    }
    public int getLineCount() {
        return line;
    }
    public void addElement(char element, int line, int column) {
        map[line][column] = element;
    }
    public void addPlayer() {
        Random r = new Random();
        int randLine = r.nextInt(line);
        int randColumn = r.nextInt(column);
        while(map[randLine][randColumn] == '#'){
            randLine = r.nextInt(line);
            randColumn = r.nextInt(column);
        }
        map[randLine][randColumn] = 'P';
        playerline = randLine;
        playercolumn = randColumn;
    }
    public int getPlayerLine() {
        return playerline;
    }
    public int getPlayerColumn() {
        return playercolumn;
    }
    public char checkMapIndexes(int line, int column) {
        return map[line][column];
    }
    public void movePlayerLeft() {
        map[playerline][playercolumn] = ' ';
        map[playerline][playercolumn - 1] = 'P';
        playercolumn--;
        checkPlayerSquare();
        super.checkLastTwoElements();
        writeMap();
    }
    public void movePlayerRight() {
        map[playerline][playercolumn] = ' ';
        map[playerline][playercolumn + 1] = 'P';
        playercolumn++;
        checkPlayerSquare();
        super.checkLastTwoElements();
        writeMap();
    }
    public void movePlayerUp() {
        map[playerline][playercolumn] = ' ';
        map[playerline - 1][playercolumn] = 'P';
        playerline--;
        checkPlayerSquare();
        super.checkLastTwoElements();
        writeMap();
    }
    public void movePlayerDown() {
        map[playerline][playercolumn] = ' ';
        map[playerline + 1][playercolumn] = 'P';
        playerline++;
        checkPlayerSquare();
        super.checkLastTwoElements();
        writeMap();
    }
    public void writeMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case '#':
                        cn.getTextWindow().output('#', new TextAttributes(Color.BLACK, Color.WHITE));
                        break;
                    case 'P':
                        cn.getTextWindow().output('P', new TextAttributes(Color.CYAN));
                        break;
                    case 'C':
                        cn.getTextWindow().output('C', new TextAttributes(Color.RED));
                        break;
                    case '*':
                        cn.getTextWindow().output('*', new TextAttributes(Color.RED));
                        break;
                    case '=':
                        cn.getTextWindow().output('=', new TextAttributes(Color.RED));
                        break;
                    case '1':
                        System.out.print('1');
                        break;
                    case '2':
                        System.out.print('2');
                        break;
                    case '3':
                        System.out.print('3');
                        break;
                    case '4':
                        cn.getTextWindow().output('4', new TextAttributes(Color.GREEN));
                        break;
                    case '5':
                        cn.getTextWindow().output('5', new TextAttributes(Color.GREEN));
                        break;
                    case ' ':
                        System.out.print(' ');
                        break;
                    default:
                        break;
                }
            }
        System.out.println();
        }
    }
    public void clearScreen() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(" ");
            }
        System.out.println();
        }
    }
    public void addElement(char c) {
        Random r = new Random();
        int randLine = r.nextInt(line);
        int randColumn = r.nextInt(column);
        while(map[randLine][randColumn] == '#' || map[randLine][randColumn] == 'P') {
            randLine = r.nextInt(line);
            randColumn = r.nextInt(column);
        }
        map[randLine][randColumn] = c;
    }
    public void checkPlayerSquare() {
        if(map[playerline - 1][playercolumn] != '#' && map[playerline - 1][playercolumn] != ' ' && 
        map[playerline - 1][playercolumn] != '=' && map[playerline - 1][playercolumn] != '*' && map[playerline - 1][playercolumn] != 'C') {
            super.addElement(map[playerline - 1][playercolumn]);
            map[playerline - 1][playercolumn] = ' ';
        }
        if(map[playerline - 1][playercolumn + 1] != '#' && map[playerline - 1][playercolumn + 1] != ' ' && 
        map[playerline - 1][playercolumn + 1] != '=' && map[playerline - 1][playercolumn + 1] != '*' && map[playerline - 1][playercolumn + 1] != 'C') {
            super.addElement(map[playerline - 1][playercolumn + 1]);
            map[playerline - 1][playercolumn + 1] = ' ';
        }
        if(map[playerline][playercolumn + 1] != '#' && map[playerline][playercolumn + 1] != ' ' && 
        map[playerline][playercolumn + 1] != '=' && map[playerline][playercolumn + 1] != '*' && map[playerline][playercolumn + 1] != 'C') {
            super.addElement(map[playerline][playercolumn + 1]);
            map[playerline][playercolumn + 1] = ' ';
        }
        if(map[playerline + 1][playercolumn + 1] != '#' && map[playerline + 1][playercolumn + 1] != ' ' && 
        map[playerline + 1][playercolumn + 1] != '=' && map[playerline + 1][playercolumn + 1] != '*' && map[playerline + 1][playercolumn + 1] != 'C') {
            super.addElement(map[playerline + 1][playercolumn + 1]);
            map[playerline + 1][playercolumn + 1] = ' ';
        }
        if(map[playerline + 1][playercolumn] != '#' && map[playerline + 1][playercolumn] != ' ' && 
        map[playerline + 1][playercolumn] != '=' && map[playerline + 1][playercolumn] != '*' && map[playerline + 1][playercolumn] != 'C') {
            super.addElement(map[playerline + 1][playercolumn]);
            map[playerline + 1][playercolumn] = ' ';
        }
        if(map[playerline + 1][playercolumn - 1] != '#' && map[playerline + 1][playercolumn - 1] != ' ' && 
        map[playerline + 1][playercolumn - 1] != '=' && map[playerline + 1][playercolumn - 1] != '*' && map[playerline + 1][playercolumn - 1] != 'C') {
            super.addElement(map[playerline + 1][playercolumn - 1]);
            map[playerline + 1][playercolumn - 1] = ' ';
        }
        if(map[playerline][playercolumn - 1] != '#' && map[playerline][playercolumn - 1] != ' ' && 
        map[playerline][playercolumn - 1] != '=' && map[playerline][playercolumn - 1] != '*' && map[playerline][playercolumn - 1] != 'C') {
            super.addElement(map[playerline][playercolumn - 1]);
            map[playerline][playercolumn - 1] = ' ';
        }
        if(map[playerline - 1][playercolumn - 1] != '#' && map[playerline - 1][playercolumn - 1] != ' ' && 
        map[playerline - 1][playercolumn - 1] != '=' && map[playerline - 1][playercolumn - 1] != '*' && map[playerline - 1][playercolumn - 1] != 'C') {
            super.addElement(map[playerline - 1][playercolumn - 1]);
            map[playerline - 1][playercolumn - 1] = ' ';
        }
    }
    public void moveChar(char ch, int way, int line, int column) {
        Random r = new Random();
        map[line][column] = ' ';
        switch (way) {
            case 1:
                if (map[line][column - 1] == ' ') {
                    map[line][column - 1] = ch;
                    info[line][column].setLength(info[line][column].getLength() - 1);
                    info[line][column - 1] = info[line][column];
                    info[line][column] = null;
                    info[line][column - 1].setUsed(true);
                }
                else {
                    info[line][column].setWay(r.nextInt(1, 5));
                    info[line][column].setLength(r.nextInt(1, 10));
                    moveChar(ch, info[line][column].getWay(), line, column);
                }
                break;
            case 2:
                if (map[line - 1][column] == ' ') {
                    map[line - 1][column] = ch;
                    info[line][column].setLength(info[line][column].getLength() - 1);
                    info[line - 1][column] = info[line][column];
                    info[line][column] = null;
                    info[line - 1][column].setUsed(true);
                }
                else {
                    info[line][column].setWay(r.nextInt(1, 5));
                    info[line][column].setLength(r.nextInt(1, 10));
                    moveChar(ch, info[line][column].getWay(), line, column);
                }
                break;
            case 3:
                if (map[line][column + 1] == ' ') {
                    map[line][column + 1] = ch;
                    info[line][column].setLength(info[line][column].getLength() - 1);
                    info[line][column + 1] = info[line][column];
                    info[line][column] = null;
                    info[line][column + 1].setUsed(true);
                }
                else {
                    info[line][column].setWay(r.nextInt(1, 5));
                    info[line][column].setLength(r.nextInt(1, 10));
                    moveChar(ch, info[line][column].getWay(), line, column);
                }
                break;
            case 4:
                if (map[line + 1][column] == ' ') {
                    map[line + 1][column] = ch;
                    info[line][column].setLength(info[line][column].getLength() - 1);
                    info[line + 1][column] = info[line][column];
                    info[line][column] = null;
                    info[line + 1][column].setUsed(true);
                }
                else {
                    info[line][column].setWay(r.nextInt(1, 5));
                    info[line][column].setLength(r.nextInt(1, 10));
                    moveChar(ch, info[line][column].getWay(), line, column);
                }
                break;
            default:
                break;
            
        }
    }
    public void moveChars() {
        Random r = new Random();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] == '4') {
                    if (info[i][j] == null) {
                        info[i][j] = new InfoForChars(r.nextInt(1, 5), r.nextInt(1, line));
                        info[i][j].setUsed(true);
                        moveChar('4', info[i][j].getWay(), i, j);
                        info[i][j] = null;
                    }
                    else {
                        if (!info[i][j].isUsed()) {
                            if(info[i][j].getLength() != 0) {
                                info[i][j].setUsed(true);
                                moveChar('4', info[i][j].getWay(), i, j);
                                info[i][j] = null;
                            }
                            else {
                                info[i][j].setUsed(true);
                                info[i][j].setWay(r.nextInt(1, 5));
                                info[i][j].setLength(r.nextInt(1, line));
                                moveChar('4', info[i][j].getWay(), i, j);
                                info[i][j] = null;
                            }
                        }
                    }
                }
                else if (map[i][j] == '5') {
                    if (info[i][j] == null) {
                        info[i][j] = new InfoForChars(r.nextInt(1, 5), r.nextInt(1, line));
                        info[i][j].setUsed(true);
                        moveChar('5', info[i][j].getWay(), i, j);
                        info[i][j] = null;
                    }
                    else {
                        if (!info[i][j].isUsed()) {
                            if(info[i][j].getLength() != 0) {
                                info[i][j].setUsed(true);
                                moveChar('5', info[i][j].getWay(), i, j);
                                info[i][j] = null;
                            }
                            else {
                                info[i][j].setUsed(true);
                                info[i][j].setWay(r.nextInt(1, 5));
                                info[i][j].setLength(r.nextInt(1, line));
                                moveChar('5', info[i][j].getWay(), i, j);
                                info[i][j] = null;
                            }
                        }
                    }
                }
                else if (map[i][j] == 'C') {
                    if (info[i][j] == null) {
                        info[i][j] = new InfoForChars(r.nextInt(1, 5), r.nextInt(1, line), i, j);
                        info[i][j].setUsed(true);
                        checkComputerSquare(i, j);
                        moveChar('C', info[i][j].getWay(), i, j);
                        info[i][j] = null;
                    }
                    else if (!info[i][j].isUsed() && isThereP(i, j) && info[i][j].isMovable()) {
                        checkComputerSquare(i, j);
                        moveComputerToPlayer(i, j);
                    }
                    else if (info[i][j].isMovable()) {
                        if (!info[i][j].isUsed()) {
                            if(info[i][j].getLength() != 0) {
                                info[i][j].setUsed(true);
                                checkComputerSquare(i, j);
                                moveChar('C', info[i][j].getWay(), i, j);
                                info[i][j] = null;
                            }
                            else {
                                info[i][j].setUsed(true);
                                info[i][j].setWay(r.nextInt(1, 5));
                                info[i][j].setLength(r.nextInt(1, line));
                                checkComputerSquare(i, j);
                                moveChar('C', info[i][j].getWay(), i, j);
                                info[i][j] = null;
                            }
                        }
                    }
                }
            }
        }
        cn.getTextWindow().setCursorPosition(0, 0);
        writeMap();
        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < info[0].length; j++) {
                if (info[i][j] != null) {
                    info[i][j].setUsed(false);
                }
            }
        }
    }
    public boolean isThereP(int x, int y) {
        if (x >= 7 && x <= line - 7 && y >= 7 && y <= column - 7) {
            for (int i = x - 7; i < x + 7; i++) {
                for (int j = y - 7; j < y + 7; j++) {
                    if(map[i][j] == 'P') {
                        return true;
                    }
                }
            }
            return false;
        }
        else if (x >= 6 && x <= line - 6 && y >= 6 && y <= column - 6) {
            for (int i = x - 6; i < x + 6; i++) {
                for (int j = y - 6; j < y + 6; j++) {
                    if(map[i][j] == 'P') {
                        return true;
                    }
                }
            }
            return false;
        }
        else if (x >= 5 && x <= line - 5 && y >= 5 && y <= column - 5) {
            for (int i = x - 5; i < x + 5; i++) {
                for (int j = y - 5; j < y + 5; j++) {
                    if(map[i][j] == 'P') {
                        return true;
                    }
                }
            }
            return false;
        }
        else if (x >= 4 && x <= line - 4 && y >= 4 && y <= column - 4) {
            for (int i = x - 4; i < x + 4; i++) {
                for (int j = y - 4; j < y + 4; j++) {
                    if(map[i][j] == 'P') {
                        return true;
                    }
                }
            }
            return false;
        }
        else if (x >= 3 && x <= line - 3 && y >= 3 && y <= column - 3) {
            for (int i = x - 3; i < x + 3; i++) {
                for (int j = y - 3; j < y + 3; j++) {
                    if(map[i][j] == 'P') {
                        return true;
                    }
                }
            }
            return false;
        }
        else if (x >= 2 && x <= line - 2 && y >= 2 && y <= column - 2) {
            for (int i = x - 2; i < x + 2; i++) {
                for (int j = y - 2; j < y + 2; j++) {
                    if(map[i][j] == 'P') {
                        return true;
                    }
                }
            }
            return false;
        }
        else {
            for (int i = x - 1; i < x + 1; i++) {
                for (int j = y - 1; j < y + 1; j++) {
                    if(map[i][j] == 'P') {
                        return true;
                    }
                }
            }
            return false;
        }
    }
    private void moveComputerToPlayer(int i, int j) {
        int temp_x = playercolumn - j, temp_y = playerline - i;
        if (temp_x < 0 && map[i][j - 1] != '#') {
            info[i][j].setLength(1);
            info[i][j].setWay(1);
            moveChar('C', info[i][j].getWay(), i, j);
        }
        else if (temp_x > 0 && map[i][j + 1] != '#') {
            info[i][j].setLength(1);
            info[i][j].setWay(3);
            moveChar('C', info[i][j].getWay(), i, j);
        }
        else if (temp_y < 0 && map[i - 1][j] != '#') {
            info[i][j].setLength(1);
            info[i][j].setWay(2);
            moveChar('C', info[i][j].getWay(), i, j);
        }
        else if (temp_y > 0 && map[i + 1][j] != '#') {
            info[i][j].setLength(1);
            info[i][j].setWay(4);
            moveChar('C', info[i][j].getWay(), i, j);
        }
    }
    public void checkComputerSquare(int i, int j) {
        if(map[i - 1][j] != '#' && map[i - 1][j] != ' ' && 
        map[i - 1][j] != '=' && map[i - 1][j] != '*') {
            switch (map[i - 1][j]) {
                case '1':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 2);
                    break;
                case '2':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 10);
                    break;
                case '3':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 30);
                    break;
                case '4':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 100);
                    break;
                case '5':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 300);
                    break;
                case 'P':
                    player.setLives(player.getLives() - 1);
                    break;
                case '=':
                    info[i][j].setMovable(false);
                    break;
                case '*':
                    info[i][j] = null;
                    map[i][j] = ' ';
                    break;
                default:
                    info[i][j].setMovable(true);
                    break;
            }
            map[i - 1][j] = ' ';
        }
        if(map[i - 1][j + 1] != '#' && map[i - 1][j + 1] != ' ' && 
        map[i - 1][j + 1] != '=' && map[i - 1][j + 1] != '*') {
            switch (map[i - 1][j + 1]) {
                case '1':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 2);
                    break;
                case '2':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 10);
                    break;
                case '3':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 30);
                    break;
                case '4':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 100);
                    break;
                case '5':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 300);
                    break;
                case 'P':
                    player.setLives(player.getLives() - 1);
                    break;
                case '=':
                    info[i - 1][j + 1].setMovable(false);
                    break;
                case '*':
                    info[i - 1][j + 1] = null;
                    map[i - 1][j + 1] = ' ';
                    break;
                default:
                    info[i - 1][j + 1].setMovable(true);
                    break;
            }
            map[i - 1][j + 1] = ' ';
        }
        if(map[i][j + 1] != '#' && map[i][j + 1] != ' ' && 
        map[i][j + 1] != '=' && map[i][j + 1] != '*') {
            switch (map[i][j + 1]) {
                case '1':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 2);
                    break;
                case '2':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 10);
                    break;
                case '3':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 30);
                    break;
                case '4':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 100);
                    break;
                case '5':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 300);
                    break;
                case 'P':
                    player.setLives(player.getLives() - 1);
                    break;
                case '=':
                    info[i][j + 1].setMovable(false);
                    break;
                case '*':
                    info[i][j + 1] = null;
                    map[i][j + 1] = ' ';
                    break;
                default:
                    info[i][j + 1].setMovable(true);
                    break;
            }
            map[i][j + 1] = ' ';
        }
        if(map[i + 1][j + 1] != '#' && map[i + 1][j + 1] != ' ' && 
        map[i + 1][j + 1] != '=' && map[i + 1][j + 1] != '*') {
            switch (map[i + 1][j + 1]) {
                case '1':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 2);
                    break;
                case '2':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 10);
                    break;
                case '3':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 30);
                    break;
                case '4':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 100);
                    break;
                case '5':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 300);
                    break;
                case 'P':
                    player.setLives(player.getLives() - 1);
                    break;
                case '=':
                    info[i + 1][j + 1].setMovable(false);
                    break;
                case '*':
                    info[i + 1][j + 1] = null;
                    map[i + 1][j + 1] = ' ';
                    break;
                default:
                    info[i + 1][j + 1].setMovable(true);
                    break;
            }
            map[i + 1][j + 1] = ' ';
        }
        if(map[i + 1][j] != '#' && map[i + 1][j] != ' ' && 
        map[i + 1][j] != '=' && map[i + 1][j] != '*') {
            switch (map[i + 1][j]) {
                case '1':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 2);
                    break;
                case '2':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 10);
                    break;
                case '3':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 30);
                    break;
                case '4':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 100);
                    break;
                case '5':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 300);
                    break;
                case 'P':
                    player.setLives(player.getLives() - 1);
                    break;
                case '=':
                    info[i + 1][j].setMovable(false);
                    break;
                case '*':
                    info[i + 1][j] = null;
                    map[i + 1][j] = ' ';
                    break;
                default:
                    info[i + 1][j].setMovable(true);
                    break;
            }
            map[i + 1][j] = ' ';
        }
        if(map[i + 1][j - 1] != '#' && map[i + 1][j - 1] != ' ' && 
        map[i + 1][j - 1] != '=' && map[i + 1][j - 1] != '*') {
            switch (map[i + 1][j - 1]) {
                case '1':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 2);
                    break;
                case '2':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 10);
                    break;
                case '3':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 30);
                    break;
                case '4':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 100);
                    break;
                case '5':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 300);
                    break;
                case 'P':
                    player.setLives(player.getLives() - 1);
                    break;
                case '=':
                    info[i + 1][j - 1].setMovable(false);
                    break;
                case '*':
                    info[i + 1][j - 1] = null;
                    map[i + 1][j - 1] = ' ';
                    break;
                default:
                    info[i][j + 1].setMovable(true);
                    break;
            }
            map[i + 1][j - 1] = ' ';
        }
        if(map[i][j - 1] != '#' && map[i][j - 1] != ' ' && 
        map[i][j - 1] != '=' && map[i][j - 1] != '*') {
            switch (map[i][j - 1]) {
                case '1':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 2);
                    break;
                case '2':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 10);
                    break;
                case '3':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 30);
                    break;
                case '4':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 100);
                    break;
                case '5':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 300);
                    break;
                case 'P':
                    player.setLives(player.getLives() - 1);
                    break;
                case '=':
                    info[i][j - 1].setMovable(false);
                    break;
                case '*':
                    info[i][j - 1] = null;
                    map[i][j - 1] = ' ';
                    break;
                default:
                    info[i][j - 1].setMovable(true);
                    break;
            }
            map[i][j - 1] = ' ';
        }
        if(map[i - 1][j - 1] != '#' && map[i - 1][j - 1] != ' ' && 
        map[i - 1][j - 1] != '=' && map[i - 1][j - 1] != '*') {
            switch (map[i - 1][j - 1]) {
                case '1':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 2);
                    break;
                case '2':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 10);
                    break;
                case '3':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 30);
                    break;
                case '4':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 100);
                    break;
                case '5':
                    InfoForChars.setComputer_score(InfoForChars.getComputer_score() + 300);
                    break;
                case 'P':
                    player.setLives(player.getLives() - 1);
                    break;
                case '=':
                    info[i - 1][j - 1].setMovable(false);
                    break;
                case '*':
                    info[i - 1][j - 1] = null;
                    map[i - 1][j - 1] = ' ';
                    break;
                default:
                    info[i - 1][j - 1].setMovable(true);
                    break;
            }
            map[i - 1][j - 1] = ' ';
        }
    }
    public void writePlayerLives() {
        cn.getTextWindow().setCursorPosition(70, 12);
        System.out.print("Player Lives: " + player.getLives());
    }
}