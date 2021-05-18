package crawler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import data.Comment;
 
public class Crawler {
	String symbol; //股票代码，正式操作时从导入界面前端传入
    int page; //爬取页数，正式操作时从导入界面前端传入
    ArrayList<Comment> comments_list=new ArrayList<Comment>();
	
    public Crawler(String symbol, int page) {
        this.symbol=symbol;
        this.page=page;
        //this.comments_list=comments_list;
    }
    
    public String getSymbol() {
        return symbol;
    }
    public void setName(String symbol) {this.symbol = symbol;}

    public int getPage() {
        return page;
    }
    public void setYear(int page) {this.page = page;}
    
    public ArrayList<Comment> getCommentslist() {
        return comments_list;
    }
    //public void setCommentslist(List<Comment> comments_list) {this.comments_list=comments_list;}
    
    public void craw() throws IOException {
        // TODO Auto-generated method stub
    	Process proc;
    	int i=0;
    	int position_comment=0;
        String filename = "data_comments.csv"; //py爬虫后评论存在这个文件里，java项目从该文件中读取评论
        String python_code; 
        python_code = "xueqiu_crawler\\venv\\Scripts\\python.exe xueqiu_crawler\\main.py "+symbol+" "+page; //构造执行python爬虫代码的命令
        try {
        	//执行爬虫py文件进行爬取，爬取后的结果放在data_test_plus.csv
            proc = Runtime.getRuntime().exec(python_code);
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        //Thread.currentThread();
		//Thread.sleep(10000);
        //java从文件中读取评论
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");   
        BufferedReader reader = new BufferedReader(isr);
        String line;
        line = reader.readLine();//从第二行评论开始读（第一行是0）
        while ((line = reader.readLine()) != null) {   
        	comments_list.add(position_comment,new Comment(i+1,line,symbol,false)); //暂时输出到控制台，等comment数据类做好后直接传到list中 
        	i++;
        	position_comment++;
        }
        reader.close();
    }
}