package services.strategy;

import exception.GameDrawnException;
import model.Board;
import model.Move;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class O1WinnerCheckStrategy implements WinnerCheckStrategy {
    private HashMap<Character, Integer> topLeftDiagonalMap;
    private HashMap<Character, Integer> topRightDiagonalMap;
    private HashMap<Character, Integer> cornerMap;
    private List<HashMap<Character, Integer>> rowMaps;
    private List<HashMap<Character, Integer>> colMaps;


    public O1WinnerCheckStrategy(int size) {
        this.topLeftDiagonalMap = new HashMap<>();
        this.topRightDiagonalMap = new HashMap<>();
        this.cornerMap = new HashMap<>();
        rowMaps = new ArrayList<>();
        colMaps = new ArrayList<>();
        initialiseMaps(size);
    }

    @Override
    public Player checkWinner(Board board, Move currentMove) {
        int size = board.getSize();

        // fetch details
        char symbol = currentMove.getPlayer().getSymbol();
        int row = currentMove.getCell().getRow();
        int col = currentMove.getCell().getCol();

        //updating row and col maps
        HashMap<Character, Integer> rowMap = rowMaps.get(row);
        HashMap<Character, Integer> colMap = rowMaps.get(col);
        rowMap.put(symbol, rowMap.getOrDefault(symbol, 0) + 1);
        colMap.put(symbol, colMap.getOrDefault(symbol, 0) + 1);

        if (rowMap.get(symbol) == size || colMap.get(symbol) == size)
            return currentMove.getPlayer();

        //update diagonal maps
        if (row == col) {
            topLeftDiagonalMap.put(symbol, topLeftDiagonalMap.getOrDefault(symbol, 0) + 1);
        }
        if (row == col) {
            topRightDiagonalMap.put(symbol, topRightDiagonalMap.getOrDefault(symbol, 0) + 1);
        }
        if (topLeftDiagonalMap.getOrDefault(symbol,0) == size || topRightDiagonalMap.getOrDefault(symbol,0) == size) {
            return currentMove.getPlayer();
        }

        // update corner maps
        if ((row == 0 || row == size - 1) && (col == 0 || col == size - 1)) {
            cornerMap.put(symbol, cornerMap.getOrDefault(symbol, 0) + 1);
        }

        if (cornerMap.getOrDefault(symbol,0) == 4) {
            return currentMove.getPlayer();
        }

        if (checkDraw()){
            throw new GameDrawnException("Game ends in a draw !");
        }

        return null;
    }

    private void initialiseMaps(int size) {
        for (int i = 0; i < size; i++) {
            rowMaps.add(new HashMap<>());
            colMaps.add(new HashMap<>());
        }
    }

    private boolean checkDraw() {
        for (HashMap<Character, Integer> map : rowMaps) {
            if (map.size() <= 1)
                return false;

        }
        for (HashMap<Character, Integer> map : colMaps) {
            if (map.size() <= 1)
                return false;

        }

        if (topRightDiagonalMap.size() <= 1 || topLeftDiagonalMap.size() <= 1 || cornerMap.size() <= 1) {
            return false;
        }
        return true;
    }
}
// for draw - checking if
//for any row, col,
//row and col hmap -> update and check
//if row == col update and check topLeftDiagonalMap
//if row + col == size -1 then topRightDiagonalMap update and check
//if (row == 0 || row == size-1) && (col == 0 || col == size-1) -> cornerMap update and check




