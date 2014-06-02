package com.xpyct.ondatra;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.htmlcleaner.TagNode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class HtmlActivity  extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.htmlcleaner);

        //Находим кнопку
        Button button = (Button)findViewById(R.id.parse);
        //Регистрируем onClick слушателя
        button.setOnClickListener(myListener);
	}

	  //Диалог ожидания
	   private ProgressDialog pd;
	   //Слушатель OnClickListener для нашей кнопки
	   private OnClickListener myListener = new OnClickListener() {
	     public void onClick(View v) {
	       //Показываем диалог ожидания
	       pd = ProgressDialog.show(/*StackParser.this*/ HtmlActivity.this, 
	    		   "Working...", "request to server", true, false);
	       //Запускаем парсинг
	       new ParseSite().execute("http://www.stackoverflow.com");
	     }
	   };
	   
	   private class ParseSite extends AsyncTask<String, Void, List<String>> {
	     //Фоновая операция
	     protected List<String> doInBackground(String... arg) {
	       List<String> output = new ArrayList<String>();
	       try
	       {
	         HtmlHelper hh = new HtmlHelper(new URL(arg[0]));
	         List<TagNode> links = hh.getLinksByClass("question-hyperlink");

	         for (Iterator<TagNode> iterator = links.iterator(); iterator.hasNext();)
	         {
	           TagNode divElement = (TagNode) iterator.next();
	           output.add(divElement.getText().toString());
	         }
	       }
	       catch(Exception e)
	       {
	         e.printStackTrace();
	       }
	       return output;
	     }

	     //Событие по окончанию парсинга
	     protected void onPostExecute(List<String> output) {
	       //Убираем диалог загрузки
	       pd.dismiss();
	       //Находим ListView
	       ListView listview = (ListView) findViewById(R.id.listViewData);
	       //Загружаем в него результат работы doInBackground
	       listview.setAdapter(new ArrayAdapter<String>(/*StackParser.this*/ HtmlActivity.this,
	           android.R.layout.simple_list_item_1 , output));
	     }
	   }
}
