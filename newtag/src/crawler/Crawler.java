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
	String symbol; //��Ʊ���룬��ʽ����ʱ�ӵ������ǰ�˴���
    int page; //��ȡҳ������ʽ����ʱ�ӵ������ǰ�˴���
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
        String filename = "data_comments.csv"; //py��������۴�������ļ��java��Ŀ�Ӹ��ļ��ж�ȡ����
        String python_code; 
        python_code = "xueqiu_crawler\\venv\\Scripts\\python.exe xueqiu_crawler\\main.py "+symbol+" "+page; //����ִ��python������������
        try {
        	//ִ������py�ļ�������ȡ����ȡ��Ľ������data_test_plus.csv
            proc = Runtime.getRuntime().exec(python_code);
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        //Thread.currentThread();
		//Thread.sleep(10000);
        //java���ļ��ж�ȡ����
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");   
        BufferedReader reader = new BufferedReader(isr);
        String line;
        line = reader.readLine();//�ӵڶ������ۿ�ʼ������һ����0��
        while ((line = reader.readLine()) != null) {   
        	comments_list.add(position_comment,new Comment(i+1,line,symbol,false)); //��ʱ���������̨����comment���������ú�ֱ�Ӵ���list�� 
        	i++;
        	position_comment++;
        }
        reader.close();
    }
}