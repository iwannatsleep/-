package view;

import java.io.IOException;

import data.Logs;

public class Main{
	public static void main(String[] args) throws IOException {
	    Logs logwrite=new Logs();
		String log="��������";
		try {
			logwrite.writeLog(log);
		} catch (IOException e3) {
			// TODO �Զ����ɵ� catch ��
			e3.printStackTrace();
		}
		new MainView();
	}
}
