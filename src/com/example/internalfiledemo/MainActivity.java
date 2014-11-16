package com.example.internalfiledemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    EditText et;
    CheckBox cb;
    TextView tv,tv1;
    final static String FILENAME="mytextfile.txt";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et=(EditText) findViewById(R.id.editText1);
		cb=(CheckBox) findViewById(R.id.checkBox1);
		tv=(TextView) findViewById(R.id.textView1);
		tv1=(TextView) findViewById(R.id.textView2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    public void WriteFile(View v){
    	FileOutputStream fos=null;
    	try {
    		if(cb.isChecked()){
    			fos=openFileOutput(FILENAME,Context.MODE_APPEND);
    		}else{
    			fos=openFileOutput(FILENAME,Context.MODE_PRIVATE);
    		}
			String text=et.getText().toString();
			fos.write(text.getBytes());
			tv1.setText("文件写入成功，文件长度为"+text.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void ReadFile(View v){
    	FileInputStream fis=null;
    	try {
			fis=openFileInput(FILENAME);
			if(fis.available()==0){
				return ;
			}
			byte[] readByte =new byte[fis.available()];
			while(fis.read(readByte)!=-1){}
			String text=new String(readByte);
			tv.setText(text);
			tv1.setText("文件读取成功，文件长度为"+text.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 }
    }
 }
