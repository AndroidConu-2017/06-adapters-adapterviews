package ca.campbell.simplecustomlv;
/**
 *
 * This code implements a simple CustomAdapter for a list view
 * in order to display an small image and a text in the list
 *
 * some code from http://developer.android.com/guide/topics/ui/layout/gridview.html
 *
 * @author P.M.Campbell
 * @version today
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String[] names = getResources().getStringArray(R.array.dog_names);
		int[] ids = { 	R.drawable.sample_2, R.drawable.sample_3,
				R.drawable.sample_4, R.drawable.sample_5,
				R.drawable.sample_6, R.drawable.sample_7,
				R.drawable.sample_0, R.drawable.sample_1};

		ListView lv = (ListView) findViewById(R.id.list);
		lv.setAdapter(new DogAdapter(this, names, ids));
	}
	

	public class DogAdapter extends BaseAdapter {
		private Context context;
		String [] listDogs;
		int [] listIdDogs;
		LayoutInflater inflater;

		public DogAdapter(Context c, String[] list, int[] imageId) {
			context = c;
			listDogs = list;
			listIdDogs = imageId;
			inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public int getCount() {
			return listDogs.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) { return position; }

		public class ViewHolder {
			TextView tv; ImageView iv;
		}
		// create a new layout for each item referenced by the Adapter
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder vh = new ViewHolder();
			View row = convertView;
			if (convertView == null) {
				row = inflater.inflate(R.layout.custom_item, null);

				vh.tv = (TextView) row.findViewById(R.id.itemTV);
				vh.iv = (ImageView) row.findViewById(R.id.itemIV);
				vh.tv.setText(listDogs[position]);
				vh.iv.setImageResource(listIdDogs[position]);
				row.setTag(vh);
				// can set the listener here if I want to
			} else {
				vh = (ViewHolder) convertView.getTag();
				// spotted by Brian Doherty 2017-03-06
				// we are re using the view but changing the content
				vh.tv.setText(listDogs[position]);
				vh.iv.setImageResource(listIdDogs[position]);
			}

		row.setOnClickListener(  new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "Clicked " + listDogs[position], Toast.LENGTH_SHORT).show();
				Intent i = new Intent(context, DogActivity.class);
				i.putExtra("dog_name", listDogs[position]);
				i.putExtra("dog_image", listIdDogs[position]);
				context.startActivity(i);
			}
		});
			return row;
		}

	}  // DogAdapter
}
