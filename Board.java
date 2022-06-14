import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Board extends JPanel 
{
    Color dark = new Color(181,136,99);
    Color light = new Color(240,217,189);
    Color red = new Color(120,0,0);
    Color yellow = new Color(255,243,95);
    boolean isWhite = true;
    String[] h = {"a","b","c","d","e","f","g","h"};
    Integer[] v = {8,7,6,5,4,3,2,1};
    
    Piece selected = null;
    HashMap<String, Piece> squares = new HashMap<>();
    HashMap<Piece, ArrayList<String>> black = new HashMap<>();
    HashMap<Piece, ArrayList<String>> white = new HashMap<>();
    String from = null;
    JLabel status = new JLabel("Game Started!");
    
    
    public Board() {
        squares.put("a1", new Rook(true, squares));
        squares.put("h1", new Rook(true, squares));
        squares.put("a8", new Rook(false,squares));
        squares.put("h8", new Rook(false,squares));
        squares.put("c1", new Bishop(true,squares));
        squares.put("f1", new Bishop(true,squares));
        squares.put("c8", new Bishop(false,squares));
        squares.put("f8", new Bishop(false,squares));
        squares.put("d1", new Queen(true,squares));
        squares.put("d8", new Queen(false,squares));
        squares.put("b1", new Knight(true,squares));
        squares.put("g1", new Knight(true,squares));
        squares.put("b8", new Knight(false,squares));
        squares.put("g8", new Knight(false,squares));
        squares.put("e1", new King(true,squares,white));
        squares.put("e8", new King(false,squares,black));
        for(char i = 'a';i<='h';i++){
            squares.put(""+i+2,new Pawn(true,squares));
            squares.put(""+i+7,new Pawn(false,squares));
        }
        
        
       
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();
                String clicked = h[x/100]+v[y/100]; //clicked 
                //if is piece and nothing selected
                if(squares.get(clicked)!=null && selected == null && squares.get(clicked).isWhite==isWhite ){
                    selected = squares.get(clicked);
                    
                    from = clicked;
                    
                    status.setText("Selected " + clicked);
                }
                //if piece selected already, move is legal, and is not capturing own piece
                else if (selected!=null&& selected.getMoves(from).contains(clicked)){
                    //move piece, update array
                    if(isWhite)
                        black.put(selected, selected.getMoves(clicked));
                    else 
                        white.put(selected, selected.getMoves(clicked));
                    squares.put(clicked,selected);
                    squares.put(from, null);
                    selected = null;
                    status.setText(from +" - "+clicked);
                    isWhite = !isWhite;
                    
                }
                //DESELECT
                else {
                    status.setText("Invalid Move");
                    selected = null;
                }
                for(String pos : squares.keySet()){
                    if(squares.get(pos)!=null){
                        Piece p = squares.get(pos);
                        if(p.isWhite)
                            black.put(p,p.getMoves(pos));
                        else
                            white.put(p,p.getMoves(pos));
                    }
                }
                repaint();
            }
        });
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(dark);
        g.fillRect(0,0,800,800);
        //DRAW BOARD
        g.setColor(light);
        for(int i = 0;i<800;i+=100){
            for(int j = 0;j<800;j+=200){
                if(i%200==0){
                    
                    g.fillRect(i,j,100,100);
                }
                else{
                    g.fillRect(i,j+100,100,100);
                }
            }
        }
        //CLICKED PIECE
        if(selected!=null){
            g.setColor(yellow);
            g.fillRect(toCoords(from)[0],toCoords(from)[1],100,100);
        }
        //DRAW PIECES
        for (HashMap.Entry<String, Piece> entry : squares.entrySet()){
            if(entry.getValue() == null) continue;
            int[] coords = toCoords(entry.getKey());
            g.drawImage(entry.getValue().img, coords[0], coords[1], this);
        }
        
        /*
        g.setColor(Color.red);
        if(isWhite){
            for(ArrayList<String> list : white.values()){
                for(String s : list){
                    int[] coords = toCoords(s);
                    g.fillRect(coords[0],coords[1],100,10);
                }
            }
        }
        else{
            for(ArrayList<String> list : black.values()){
                for(String s : list){
                    int[] coords = toCoords(s);
                    g.fillRect(coords[0],coords[1],100,10);
                }
            }
        }
        */
        //DRAW POSSIBLE MOVES
        if(selected!=null) {
            g.setColor(Color.gray);
            for(String pos : selected.getMoves(from)){
                //status1.setText(status1.getText()+pos + " ");
                int[] coords = toCoords(pos);
                
                g.fillOval(coords[0]+37,coords[1]+37,25,25);
            }
        }
    }
    public int[] toCoords(String pos){
        int[] coords = new int[2];
        coords[0] = Arrays.asList(h).indexOf(pos.substring(0,1))*100;
        coords[1] = Arrays.asList(v).indexOf(Integer.parseInt(pos.substring(1)))*100;
        return coords;
    }
}
