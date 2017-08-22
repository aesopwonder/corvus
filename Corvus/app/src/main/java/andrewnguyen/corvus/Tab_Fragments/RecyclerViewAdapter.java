package andrewnguyen.corvus.Tab_Fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import andrewnguyen.corvus.Data.List_Item;
import andrewnguyen.corvus.R;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {
    private View v;
    private List<List_Item> listData;
    private LayoutInflater inflater;
    private ItemClickCallback itemClickCallback;
    public static Context ctx;

    public interface ItemClickCallback {
        void onItemClick(View v, int p);

        void onSecondaryIconClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public RecyclerViewAdapter(List<List_Item> listData, Context c) {
        inflater = LayoutInflater.from(c);
        this.listData = listData;
        ctx= c ;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Sets the holder for the list items
        View view = inflater.inflate(R.layout.list_item_layout, parent, false);
        v = view;
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        List_Item item = listData.get(position);
        holder.name.setText(item.getName());
        if(item.getStatus().equals("OK")){
            holder.status.setText("OK");
            holder.status.setTextColor(ctx.getResources().getColor(R.color.green));
        }
        else{
            holder.status.setText("Inactive");
            holder.status.setTextColor(ctx.getResources().getColor(R.color.red));
        }

        WebSettings web_settings = holder.animation.getSettings();
        web_settings.setJavaScriptEnabled(true);

        if (item.getStatus().equals("OK")) {
            holder.animation.loadUrl("file:///android_asset/greenpi.html");
        }
        else{
            holder.animation.loadUrl("file:///android_asset/redpi.html");
        }
    }
    public void setListData(ArrayList<List_Item> exerciseList) {
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        WebView animation;
        TextView name;
        TextView status;
        View container_of_list_item;

        public Holder (View itemView) {
            super(itemView);
            animation = (WebView) itemView.findViewById(R.id.animation);
            status = (TextView)itemView.findViewById(R.id.status);
            name = (TextView)itemView.findViewById(R.id.name);
            container_of_list_item = itemView.findViewById(R.id.container_of_list_item);
            container_of_list_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.container_of_list_item){
                itemClickCallback.onItemClick(v, getAdapterPosition());
            } else {
                itemClickCallback.onSecondaryIconClick(getAdapterPosition());
            }
        }
    }
}
