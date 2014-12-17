package com.xpyct.ondatra;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.widget.*;
import org.htmlcleaner.TagNode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class HtmlActivity  extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		setTheme(android.R.style.Theme_Holo); // (for Android Built In Theme)

        setContentView(R.layout.htmlcleaner);

		// Create the array of numbers that will populate the numberpicker
		//final String[] nums = new String[21];
		//for(int i=0; i<nums.length; i++) {
		//	nums[i] = Integer.toString(i*5);
		//}
		final String[] nums = new String[150];
		for(int i=nums.length; i>0; i--) {
			nums[i-1] = Integer.toString(i);
		}

		// Set the max and min values of the numberpicker, and give it the
		// array of numbers created above to be the displayed numbers
		final NumberPicker np = (NumberPicker) findViewById(R.id.np);
		np.setMaxValue(149);
		np.setMinValue(0);
		np.setValue(81);
		np.setWrapSelectorWheel(false);
		np.setDisplayedValues(nums);

		final String[] nums2 = new String[10];
		for(int i=nums2.length-1; i>-1; i--) {
			nums2[i] = Integer.toString(i);
		}
		final NumberPicker np2 = (NumberPicker) findViewById(R.id.np2);
		np2.setMaxValue(9);
		np2.setMinValue(0);
		np2.setValue(2);
		np2.setWrapSelectorWheel(false);
		np2.setDisplayedValues(nums2);

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Show the selected value of the numberpicker when the button is clicked
				Toast.makeText(HtmlActivity.this, "Selected value: " + nums[np.getValue()], Toast.LENGTH_SHORT).show();
			}
		});

        //Находим кнопку
        //Button button = (Button)findViewById(R.id.parse);
        //Регистрируем onClick слушателя
        //button.setOnClickListener(myListener);
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
