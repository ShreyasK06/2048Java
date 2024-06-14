public class logic {

    private String[][] board;
    private int score =0;

    public logic(int width, int height){
        board = new String[width][height];
        makeGame();
    }

    public void makeGame(){
        for(int x = 0; x < board.length;x++){
            for(int y = 0; y < board[0].length;y++){
                board[x][y] = "0";
            }
        }
        board[board.length-1][2] = "2";
        board[board.length-1][3] = "2";

    }

    public void addRandom(){
        int random = (int)(Math.random()*findNumClear());
        String[] clear = getClear();
        board[Integer.parseInt(clear[random].split(" ")[0])][Integer.parseInt(clear[random].split(" ")[1])] = "2";

    }

    public int findNumClear(){
        int total =0;
        for(int x =0;x < board.length;x++){
            for(int y = 0; y < board[0].length;y++){
                if(board[x][y].equals("0"))total++;
            }
        }
        return total;
    }

    public String[] getClear(){
        String[] clear = new String[findNumClear()];
        int count = 0;
        for(int x =0;x < board.length;x++){
            for(int y = 0; y < board[0].length;y++){
                if(board[x][y].equals("0")){
                    clear[count] = x + " " + y;
                    count++;
                }
            }
        }
        return clear;
    }

    public String[] getFilled(){
        String[] filled = new String[(board.length*board[0].length)-findNumClear()];
        int count = 0;
        for(int x =0;x < board.length;x++){
            for(int y = 0; y < board[0].length;y++){
                if(!board[x][y].equals("0")){
                    filled[count] = x + " " + y;
                    count++;
                }
            }
        }
        return filled;
    }



    public void makeMoves(String direction){
        if(direction.equals("up")){
            for(int x = 0; x < board.length;x++){
                String[] filled = getFilled();
                for(int y = 0; y < filled.length;y++){
                    int[] xy = new int[]{Integer.parseInt(filled[y].split(" ")[0]),Integer.parseInt(filled[y].split(" ")[1])};
                    if(xy[0] != 0){
                        if(board[xy[0]-1][xy[1]].equals("0")){
                            board[xy[0]-1][xy[1]] = board[xy[0]][xy[1]];
                            board[xy[0]][xy[1]] = "0";
                        }
                        else if(board[xy[0]-1][xy[1]].equals(board[xy[0]][xy[1]])){
                            int tempScore = Integer.parseInt(board[xy[0]-1][xy[1]])*2;
                            score+= Integer.parseInt(board[xy[0]-1][xy[1]])*2;
                            board[xy[0]-1][xy[1]] = Integer.toString(tempScore);
                            board[xy[0]][xy[1]] = "0";
                        }
                    }
                }
            }
        }

        if(direction.equals("down")){
            for(int x = 0; x < board.length;x++){
                String[] filled = getFilled();
                for(int y = filled.length-1; y >= 0;y--){
                    int[] xy = new int[]{Integer.parseInt(filled[y].split(" ")[0]),Integer.parseInt(filled[y].split(" ")[1])};
                    if(xy[0] != board.length-1){
                        if(board[xy[0]+1][xy[1]].equals("0")){
                            board[xy[0]+1][xy[1]] = board[xy[0]][xy[1]];
                            board[xy[0]][xy[1]] = "0";
                        }
                        else if(board[xy[0]+1][xy[1]].equals(board[xy[0]][xy[1]])){
                            int tempScore = Integer.parseInt(board[xy[0]+1][xy[1]])*2;
                            score+= Integer.parseInt(board[xy[0]+1][xy[1]])*2;
                            board[xy[0]+1][xy[1]] = Integer.toString(tempScore);
                            board[xy[0]][xy[1]] = "0";
                        }
                    }
                }
            }
        }

        if(direction.equals("left")){
            for(int y =0; y < board[0].length;y++){
                String[] filled = getFilled();
                for(int x = 0; x < filled.length;x++){
                    int[] xy = new int[]{Integer.parseInt(filled[x].split(" ")[0]),Integer.parseInt(filled[x].split(" ")[1])};
                    if(xy[1] != 0){
                        if(board[xy[0]][xy[1]-1].equals("0")){
                            board[xy[0]][xy[1]-1] = board[xy[0]][xy[1]];
                            board[xy[0]][xy[1]] = "0";
                        }
                        else if(board[xy[0]][xy[1]-1].equals(board[xy[0]][xy[1]])){
                            int tempScore = Integer.parseInt(board[xy[0]][xy[1]-1])*2;
                            score+= Integer.parseInt(board[xy[0]][xy[1]-1])*2;
                            board[xy[0]][xy[1]-1] = Integer.toString(tempScore);
                            board[xy[0]][xy[1]] = "0";
                        }
                    }
                }
            }
        }

        if(direction.equals("right")){
            for(int y=0; y < board[0].length;y++){
                String[] filled = getFilled();
                for(int x = filled.length-1; x >= 0;x--){
                    int[] xy = new int[]{Integer.parseInt(filled[x].split(" ")[0]),Integer.parseInt(filled[x].split(" ")[1])};
                    if(xy[1] != board[0].length-1){
                        if(board[xy[0]][xy[1]+1].equals("0")){
                            board[xy[0]][xy[1]+1] = board[xy[0]][xy[1]];
                            board[xy[0]][xy[1]] = "0";
                        }
                        else if(board[xy[0]][xy[1]+1].equals(board[xy[0]][xy[1]])){
                            int tempScore = Integer.parseInt(board[xy[0]][xy[1]+1])*2;
                            score+= Integer.parseInt(board[xy[0]][xy[1]+1])*2;
                            board[xy[0]][xy[1]+1] = Integer.toString(tempScore);
                            board[xy[0]][xy[1]] = "0";
                        }
                    }
                }
            }
        }

        addRandom();
    }


    public boolean isValidMove(String direction){
        if(direction.equals("up")){
            for(int x = 0; x < board.length;x++){
                String[] filled = getFilled();
                for(int y = 0; y < filled.length;y++){
                    int[] xy = new int[]{Integer.parseInt(filled[y].split(" ")[0]),Integer.parseInt(filled[y].split(" ")[1])};
                    if(xy[0] != 0){
                        if(board[xy[0]-1][xy[1]].equals("0") || board[xy[0]-1][xy[1]].equals(board[xy[0]][xy[1]]))return true;
                    }
                }
            }
        }

        if(direction.equals("down")){
            for(int x = 0; x < board.length;x++){
                String[] filled = getFilled();
                for(int y = filled.length-1; y >= 0;y--){
                    int[] xy = new int[]{Integer.parseInt(filled[y].split(" ")[0]),Integer.parseInt(filled[y].split(" ")[1])};
                    if(xy[0] != board.length-1){
                        if(board[xy[0]+1][xy[1]].equals("0") || board[xy[0]+1][xy[1]].equals(board[xy[0]][xy[1]]))return true;
                    }
                }
            }
        }

        if(direction.equals("left")){
            for(int y =0; y < board[0].length;y++){
                String[] filled = getFilled();
                for(int x = 0; x < filled.length;x++){
                    int[] xy = new int[]{Integer.parseInt(filled[x].split(" ")[0]),Integer.parseInt(filled[x].split(" ")[1])};
                    if(xy[1] != 0){
                        if(board[xy[0]][xy[1]-1].equals("0") || board[xy[0]][xy[1]-1].equals(board[xy[0]][xy[1]]))return true;
                    }
                }
            }
        }

        if(direction.equals("right")){
            for(int y =0; y < board[0].length;y++){
                String[] filled = getFilled();
                for(int x = filled.length-1; x >= 0;x--){
                    int[] xy = new int[]{Integer.parseInt(filled[x].split(" ")[0]),Integer.parseInt(filled[x].split(" ")[1])};
                    if(xy[1] != board[0].length-1){
                        if(board[xy[0]][xy[1]+1].equals("0") || board[xy[0]][xy[1]+1].equals(board[xy[0]][xy[1]]))return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isWin(){
        for(int x = 0; x < board.length;x++){
            for(int y = 0; y < board[0].length;y++){
                if(board[x][y].equals("2048"))return true;
            }
        }
        return false;
    }

    
    public String[][] getBoard(){
        return board;
    }

    public int getScore(){
        return score;
    }
}