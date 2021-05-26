package data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Logs {
	private String text="日志";
	private String filename="logs.txt";
	public void writeLog(String log) throws IOException {   
	    
    	File file = new File(filename);
    	FileOutputStream fos = null;
    	Calendar nowtime = new GregorianCalendar();
    	String time="["+String.format("%04d", nowtime.get(Calendar.YEAR))+"/"+
				String.format("%02d", nowtime.get(Calendar.MONTH))+"/" +
				String.format("%02d", nowtime.get(Calendar.DATE))+" " +
				String.format("%02d", nowtime.get(Calendar.HOUR))+":" +
				String.format("%02d", nowtime.get(Calendar.MINUTE))+":" +
				String.format("%02d", nowtime.get(Calendar.SECOND))+"." +
				String.format("%03d", nowtime.get(Calendar.MILLISECOND))+"]";
    	if (!file.exists()) {
    	    try {
    	        file.createNewFile();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}
	    
	    // 保存文件内容
    	fos = new FileOutputStream(file,true);
    	OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
    	osw.write(time+log+"\n");   
    	osw.close();   
	    //System.out.println("对象保存完毕。");
	    
    }
}
