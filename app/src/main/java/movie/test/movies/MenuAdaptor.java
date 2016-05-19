package movie.test.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import movie.test.movies.model.SideMenu;

/**
 * Created by muhammad ali
 * on 13/05/2016.
 */
public class MenuAdaptor extends ArrayAdapter<SideMenu> {

    private List<SideMenu> sideMenus = new ArrayList<>();

    public MenuAdaptor(Context context, int resource) {
        super(context, resource);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext().getApplicationContext()).inflate(R.layout.drawer_list_item, parent, false);
        TextView textView = (TextView) convertView.findViewById(R.id.text1);
        textView.setText(sideMenus.get(position).getTitle());
        return convertView;
    }



    @Override
    public int getCount() {
        return sideMenus.size();
    }


    @Override
    public SideMenu getItem(int position) {
        return sideMenus.get(position);
    }

    public void addInSideMenuList(List<SideMenu> sideMenus) {
        this.sideMenus = sideMenus;
        notifyDataSetChanged();
    }

}
