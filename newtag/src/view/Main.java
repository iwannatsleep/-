package view;

import java.io.IOException;

import data.Logs;

public class Main{
	public static void main(String[] args) throws IOException {
	    Logs logwrite=new Logs();
		String log="程序启动";
		try {
			logwrite.writeLog(log);
		} catch (IOException e3) {
			// TODO 自动生成的 catch 块
			e3.printStackTrace();
		}
		new MainView();
	}
}
