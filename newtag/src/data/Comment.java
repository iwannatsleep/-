package data;

public class Comment {
	private int id;
    private String text;
    private String symbol;
    private boolean tagedornot;
    private boolean istagconflict;
    public Comment(int id, String text, String symbol, boolean tagedornot, boolean isconflict) {
        this.id=id;
        this.text=text;
        this.symbol=symbol;
        this.tagedornot=tagedornot;
        this.istagconflict=isconflict;
    }

    public int getId() {
        return id;
    }
    public void setName(int id) {this.id = id;}

    public String getText() {
        return text;
    }
    public void setText(String text) {this.text = text;}

    public String getSymbol() {
        return symbol;
    }
    public void setMonth(String symbol) {this.symbol = symbol;}

    public boolean getTagedornot() {
        return tagedornot;
    }
    public void setTagedornot(boolean tagedornot) {this.tagedornot = tagedornot;}
    
    public boolean getIsconflict() {
        return istagconflict;
    }
    public void setIsconflict(boolean isconflict) {this.istagconflict = isconflict;}

    
}
