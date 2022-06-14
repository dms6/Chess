import java.util.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.*;

public abstract class Piece{
    BufferedImage img;
    
    String path; //path to image
    String[] h = {"a","b","c","d","e","f","g","h"};
    int[] v = {8,7,6,5,4,3,2,1};
    boolean isWhite; 
    HashMap<String,Piece> squares;
    ArrayList<String> moves = new ArrayList<>();
    public Piece(String path, HashMap<String, Piece> squares){
        this.path = path;
        try{
            img = ImageIO.read(new File(path));
        } 
        catch(Exception e){System.out.println("no pic");}
        this.squares = squares;
    }
    
   
    abstract ArrayList<String> getMoves(String position);
    
    public void check(int i, int j){
        if(i>'h'||i<'a'||j<1||j>8) return;
        String pos = ""+(char)i+j;
        if(squares.get(pos)!=null){
            if(squares.get(pos).isWhite != isWhite){
                moves.add(pos);
            }
            return;
        }
        moves.add(pos);
    }
    public String toString(){
        return "";//+path.charAt(7);
    }
}

class Rook extends Piece{
    public Rook(boolean isWhite, HashMap<String, Piece> squares){
        super(isWhite?"pieces/wr.png":"pieces/br.png", squares);
        this.isWhite = isWhite;
    }
    public ArrayList<String> getMoves(String position){
        
        char x = position.charAt(0);
        int y = Integer.parseInt(position.substring(1));
        ArrayList<String> moves = new ArrayList<>();
        for(int i = x+1; i<='h'; i++){
            String pos = ""+(char)i+y;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int i = x-1; i>='a'; i--){
            String pos = ""+(char)i+y;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int j = y+1; j<=8; j++){
            String pos = ""+x+""+j;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int j = y-1; j>=1; j--){
            String pos = ""+x+""+j;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        return moves;
    }
}
class Bishop extends Piece{
    public Bishop(boolean isWhite, HashMap<String, Piece> squares){
        super(isWhite?"pieces/wb.png":"pieces/bb.png", squares);
        this.isWhite = isWhite;
    }
    public ArrayList<String> getMoves(String position){
        char x = position.charAt(0);
        int y = Integer.parseInt(position.substring(1));
        ArrayList<String> moves = new ArrayList<>();
        for(int i = x+1, j = y+1; i<='h' && j<=8; i++,j++){
            String pos = ""+(char)i+j;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int i = x-1, j = y-1; i>='a' && j>=1; i--,j--){
            String pos = ""+(char)i+j;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int i = x+1, j = y-1; i<='h' && j>=1; i++,j--){
            String pos = ""+(char)i+j;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int i = x-1, j = y+1; i>='a' && j<=8; i--,j++){
            String pos = ""+(char)i+j;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        return moves;
    }
}
class Queen extends Piece{
    public Queen(boolean isWhite, HashMap<String, Piece> squares){
        super(isWhite?"pieces/wq.png":"pieces/bq.png", squares);
        this.isWhite = isWhite;
    }
    public ArrayList<String> getMoves(String position){
        char x = position.charAt(0);
        int y = Integer.parseInt(position.substring(1));
        ArrayList<String> moves = new ArrayList<>();
        for(int i = x+1; i<='h'; i++){
            String pos = ""+(char)i+y;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int i = x-1; i>='a'; i--){
            String pos = ""+(char)i+y;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int j = y+1; j<=8; j++){
            String pos = ""+x+""+j;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int j = y-1; j>=1; j--){
            String pos = ""+x+""+j;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int i = x+1, j = y+1; i<='h' && j<=8; i++,j++){
            String pos = ""+(char)i+j;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int i = x-1, j = y-1; i>='a' && j>=1; i--,j--){
            String pos = ""+(char)i+j;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int i = x+1, j = y-1; i<='h' && j>=1; i++,j--){
            String pos = ""+(char)i+j;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        for(int i = x-1, j = y+1; i>='a' && j<=8; i--,j++){
            String pos = ""+(char)i+j;
            if(squares.get(pos)!=null){
                if(squares.get(pos).isWhite != isWhite){
                    moves.add(pos);
                }
                break;
            }
            moves.add(pos);
        }
        return moves;
    }
}
class Pawn extends Piece{
    public Pawn(boolean isWhite, HashMap<String, Piece> squares){
        super(isWhite?"pieces/wp.png":"pieces/bp.png", squares);
        this.isWhite = isWhite;
    }
    public ArrayList<String> getMoves(String position){
        char x = position.charAt(0);
        int y = Integer.parseInt(position.substring(1));
        ArrayList<String> moves = new ArrayList<>();
        String test;
        if(isWhite){
            int places = y==2?2:1;
            for(int j = y+1; j<=y+places; j++){
                String pos = ""+x+j;
                if(squares.get(pos)!=null){
                    if(squares.get(pos).isWhite != isWhite){
                        moves.add(pos);
                    }
                    break;
                }
                moves.add(pos);
            }
            test = ""+(char)(x+1)+(y+1);
            if(squares.get(test)!=null&&squares.get(test).isWhite!=isWhite){
                moves.add(test);
            }
            test = ""+(char)(x-1)+(y+1);
            if(squares.get(test)!=null&&squares.get(test).isWhite!=isWhite){
                moves.add(test);
            }
        }
        else{
            int places = y==7?2:1;
            for(int j = y-1; j>=y-places; j--){
                String pos = ""+x+j;
                if(squares.get(pos)!=null){
                    if(squares.get(pos).isWhite != isWhite){
                        moves.add(pos);
                    }
                    break;
                }
                moves.add(pos);
            }
            test = ""+(char)(x-1)+(y-1);
            if(squares.get(test)!=null&&squares.get(test).isWhite!=isWhite){
                moves.add(test);
            }
            test = ""+(char)(x+1)+(y-1);
            if(squares.get(test)!=null&&squares.get(test).isWhite!=isWhite){
                moves.add(test);
            }
            
        }
        return moves;
    }
}
class Knight extends Piece{
    
    public Knight(boolean isWhite, HashMap<String, Piece> squares){
        super(isWhite?"pieces/wn.png":"pieces/bn.png", squares);
        this.isWhite = isWhite;
    }
    public ArrayList<String> getMoves(String position){
        moves.clear();
        int x = position.charAt(0);
        int y = Integer.parseInt(position.substring(1));
        check(x+2, y-1);
        check(x+2, y+1);
        check(x-2, y-1);
        check(x-2, y+1);
        check(x+1, y-2);
        check(x+1, y+2);
        check(x-1, y-2);
        check(x-1, y+2);
        return moves;
    }
    
}
class King extends Piece{
    HashMap<Piece,ArrayList<String>> cantGo;
    public King(boolean isWhite, HashMap<String, Piece> squares, HashMap<Piece,ArrayList<String>> targettedSquares){
        super(isWhite?"pieces/wk.png":"pieces/bk.png", squares);
        this.isWhite = isWhite;
        cantGo = targettedSquares;
    }
    public ArrayList<String> getMoves(String position){
        moves.clear();
        int x = position.charAt(0); 
        int y = Integer.parseInt(position.substring(1));
        check(x+1,y+1);
        check(x+1,y);
        check(x+1,y-1);
        check(x-1,y+1);
        check(x-1,y);
        check(x-1,y-1);
        check(x,y+1);
        check(x,y-1);
        
        return moves;
    }
    @Override
    public void check(int i, int j){
        if(i>'h'||i<'a'||j<1||j>8) return;
        String pos = ""+(char)i+j;
        
        if(squares.get(pos)!=null&&squares.get(pos).isWhite == isWhite){
            return;
        }
        for(ArrayList<String> pieceMoves: cantGo.values()){
            if(pieceMoves.contains(pos)){
               return;
            }
        }
        moves.add(pos);
    }
}
