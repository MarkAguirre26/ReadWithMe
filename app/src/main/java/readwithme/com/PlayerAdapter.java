package readwithme.com;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 PlayerAdapter * Created by Mark on 10/18/2016.
 */

public class PlayerAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;

    Player tempValues = null;
    int i = 0;

    public PlayerAdapter(Activity activity_, ArrayList arrayList) {
        activity = activity_;
        data = arrayList;
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView text1;
        public TextView text2;
        public  TextView text3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {


            vi = inflater.inflate(R.layout.row_listview, null);

            holder = new ViewHolder();
            holder.text1 = (TextView) vi.findViewById(R.id.txt1);
            holder.text2 = (TextView) vi.findViewById(R.id.txt2);
            holder.text3 = (TextView) vi.findViewById(R.id.txt3);


            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (data.size() <= 0) {
            holder.text1.setText("No Data");

        } else {

            tempValues = null;
            tempValues = (Player) data.get(position);


            holder.text1.setTag(tempValues.getCn());
            holder.text1.setText(tempValues.getName());
           // holder.text2.setText(tempValues.getScore());
            holder.text3.setText(tempValues.getRemark());


        }
        return vi;
    }

    public  void  updateResult(ArrayList<Player> players){
        this.data = players;
        notifyDataSetChanged();
    }
}
