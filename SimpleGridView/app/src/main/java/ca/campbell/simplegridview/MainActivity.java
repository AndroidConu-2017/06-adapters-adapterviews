package ca.campbell.simplegridview;
/*
 * Code from http://developer.android.com/guide/topics/ui/layout/gridview.html
 */
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));
		gridview.setOnItemClickListener(handleClick); 

	}
	
	private OnItemClickListener handleClick = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
		}
	};
/*
 * First, this implements some required methods inherited from BaseAdapter. 
 * The constructor and getCount() are self-explanatory. 
 * Normally, getItem(int) should return the actual object at the specified position in the adapter,
 * but it's ignored for this example. 
 * Likewise, getItemId(int) should return the row id of the item, but it's not needed here.
 * 
 * The first method necessary is getView(). This method creates a new View for each image added to the ImageAdapter.
 *  When this is called, a View is passed in, which is normally a recycled object
 *  (at least after this has been called once), so there's a check to see if the object is null.
 *  If it is null, an ImageView is instantiated and configured with desired properties 
 *  for the image presentation:
 *  -setLayoutParams(ViewGroup.LayoutParams) sets the height and width for the View
 *  	this ensures that, no matter the size of the drawable, each image is resized and 
 *  	cropped to fit in these dimensions, as appropriate.
 *  -setScaleType(ImageView.ScaleType) declares that images should be cropped toward the center (if necessary).
 *  -setPadding(int, int, int, int) defines the padding for all sides. 
 *  	(Note that, if the images have different aspect-ratios, then less padding will cause more cropping 
 *  	of the image if it does not match the dimensions given to the ImageView.)  
 *  
 *  If the View passed to getView() is not null, then the local ImageView is initialized with
 *  the recycled View object.  At the end of the getView() method, the position integer passed into
 *  the method is used to select an image from the mThumbIds array, which is set as the image resource
 *  for the ImageView.  All that's left is to define the mThumbIds array of drawable resources.
 */
	public class ImageAdapter extends BaseAdapter {
		private Context context;

		public ImageAdapter(Context c) {
			context = c;
		}

		public int getCount() {
			return mThumbIds.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// create a new ImageView for each item referenced by the Adapter
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {  // if it's not recycled, initialize some attributes
				imageView = new ImageView(context);
				// don't do this it constrains
				imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			//	imageView.setPadding(4, 4, 4, 4);
				imageView.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
			} else {
				imageView = (ImageView) convertView;
			}

			imageView.setImageResource(mThumbIds[position]);
			return imageView;
		}

		// references to our images
		private Integer[] mThumbIds = {
				R.drawable.sample_2, R.drawable.sample_3,
				R.drawable.sample_4, R.drawable.sample_5,
				R.drawable.sample_6, R.drawable.sample_7,
				R.drawable.sample_0, R.drawable.sample_1,
				R.drawable.sample_2, R.drawable.sample_3,
				R.drawable.sample_4, R.drawable.sample_5,
				R.drawable.sample_6, R.drawable.sample_7,
				R.drawable.sample_0, R.drawable.sample_1,
				R.drawable.sample_2, R.drawable.sample_3,
				R.drawable.sample_4, R.drawable.sample_5,
				R.drawable.sample_6, R.drawable.sample_7
		};
	}
}
