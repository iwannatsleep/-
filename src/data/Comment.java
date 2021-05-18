package data;

public class Comment {
	private int id;
    private String text;
    private String symbol;

    public Comment(int id, String text, String symbol) {
        this.id=id;
        this.text=text;
        this.symbol=symbol;
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

}
