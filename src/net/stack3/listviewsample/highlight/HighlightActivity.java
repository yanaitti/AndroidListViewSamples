package net.stack3.listviewsample.highlight;

import java.util.ArrayList;

import net.stack3.listviewsample.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class HighlightActivity extends Activity {
    private HighlightListItemAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highlight_activity);
        setTitle("ChoiceSingle");
        
        ArrayList<String> items = new ArrayList<String>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        
        adapter = new HighlightListItemAdapter(this, items);
        
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listViewOnItemClickListener);
        
        Button addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(addButtonOnClickLisetenr);
        
        Button removeButton = (Button)findViewById(R.id.removeButton);
        removeButton.setOnClickListener(removeButtonOnClickLisetenr);
    }
    
    private OnItemClickListener listViewOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            adapter.notifyDataSetChanged();
        }
    };
    
    private OnClickListener addButtonOnClickLisetenr = new OnClickListener() {
        @Override
        public void onClick(View view) {
            ListView listView = (ListView)findViewById(R.id.listView);
            int position = listView.getCheckedItemPosition();
            if (position >= 0) {
                adapter.insert("New Item", position + 1);
            } else {
                adapter.add("New Item");
            }
        }
    };

    private OnClickListener removeButtonOnClickLisetenr = new OnClickListener() {
        @Override
        public void onClick(View view) {
            ListView listView = (ListView)findViewById(R.id.listView);
            int position = listView.getCheckedItemPosition();
            if (position >= 0) {
                String item = adapter.getItem(position);
                adapter.remove(item);
                // 削除したpositionのチェックを外す。これをしないと範囲外のものがチェックされたままになりうる。
                listView.setItemChecked(position, false);
            }
        }
    };    
}
