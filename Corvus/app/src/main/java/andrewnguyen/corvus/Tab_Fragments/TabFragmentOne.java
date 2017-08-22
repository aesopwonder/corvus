package andrewnguyen.corvus.Tab_Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import andrewnguyen.corvus.Data.List_Item;
import andrewnguyen.corvus.R;
import andrewnguyen.corvus.View_Device;


public class TabFragmentOne extends Fragment implements RecyclerViewAdapter.ItemClickCallback {
    private RecyclerView recView;
    private LinearLayoutManager linearLayoutManager;
    private ItemTouchHelper itemTouchHelper;
    private static View view;
    private List<List_Item> listData;
    private static TextView hiddenText;
    private List_Item item;
    Global global = new Global();
    private final String EXTRA_NAME = "NAME",
            EXTRA_STATUS = "STATUS",
            EXTRA_RADIO="RADIO",
            EXTRA_AUDIO = "AUDIO",
            EXTRA_VIDEO = "VIDEO",
            EXTRA_AMOUNT_AUDIO = "AMOUNT_AUDIO",
            EXTRA_AMOUNT_VIDEO = "AMOUNT_VIDEO",
            EXTRA_AMOUNT_RADIO = "AMOUNT_RADIO",
            EXTRA_PRICE_AUDIO = "PRICE_AUDIO",
            EXTRA_PRICE_VIDEO = "PRICE_VIDEO",
            EXTRA_PRICE_RADIO = "PRICE_RADIO",
            BUNDLE_EXTRAS ="DEVICE_DETAILS";

    private static RecyclerViewAdapter adapter;
//    private static final String ARG_EXAMPLE = "This_is_a_constant";

    //Public:
    public TabFragmentOne() {
    //Constructor
    }
    public static TabFragmentOne newInstance(String example_argument) {
        TabFragmentOne tabFragmentOne = new TabFragmentOne();
//        Bundle args = new Bundle();
//        args.putString(ARG_EXAMPLE, example_argument);
//        tabFragmentOne.setArguments(args);
        return tabFragmentOne;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.device_list, container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
            //Initialize variables

            recView = (RecyclerView) getView().findViewById(R.id.rec_list);
            linearLayoutManager = new LinearLayoutManager(getActivity()) {};
            linearLayoutManager.setStackFromEnd(true);
            linearLayoutManager.setReverseLayout(true);
            hiddenText = (TextView) getActivity().findViewById(R.id.hidden_text);
            recView.setLayoutManager(linearLayoutManager);
            recView.addItemDecoration(new Divider(getActivity()));
            listData = global.getDevice_list();
            adapter = new RecyclerViewAdapter(listData, getActivity());
            recView.setAdapter(adapter);
            adapter.setItemClickCallback(this);
            itemTouchHelper = new ItemTouchHelper(createHelperCallback());
            itemTouchHelper.attachToRecyclerView(recView);
        if(adapter.getItemCount()==0){
            show_hidden_text();
        }
    }

    @Override
    public void onItemClick(View v, int p) {
        item = (List_Item) listData.get(p);
        Intent i = new Intent(getActivity(), View_Device.class);
        Bundle extras = new Bundle();
        extras.putString(EXTRA_NAME, item.getName());
        extras.putString(EXTRA_STATUS, item.getStatus());
        extras.putString(EXTRA_RADIO, item.getRadio());
        extras.putString(EXTRA_AUDIO, item.getAudio());
        extras.putString(EXTRA_VIDEO, item.getVideo());
        extras.putString(EXTRA_AMOUNT_AUDIO, item.getAmount_audio());
        extras.putString(EXTRA_AMOUNT_VIDEO, item.getAmount_video());
        extras.putString(EXTRA_AMOUNT_RADIO, item.getAmount_radio());
        extras.putString(EXTRA_PRICE_AUDIO, item.getPrice_audio());
        extras.putString(EXTRA_PRICE_VIDEO, item.getPrice_video());
        extras.putString(EXTRA_PRICE_RADIO, item.getPrice_radio());
        i.putExtra(BUNDLE_EXTRAS, extras);
            startActivity(i);
    }

    @Override
    public void onSecondaryIconClick(int p) {
        adapter.notifyDataSetChanged();
    }
    //Private:

    private void custom_onChildDraw(Canvas c, RecyclerView.ViewHolder viewHolder){
        View itemView = viewHolder.itemView;
        Paint p = new Paint();
        p.setColor(ContextCompat.getColor(getActivity(), R.color.red));
        c.drawRect(
                (float) itemView.getLeft(),
                (float) itemView.getTop(),
                (float) itemView.getRight(),
                (float) itemView.getBottom(),
                p
        );
        p.setColor(Color.WHITE);
        p.setTextSize(70);
        p.setTextAlign(Paint.Align.CENTER);
        float xPos = (itemView.getWidth() / 2);
        float yPos = ((float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop()+50) / 2);
        c.drawText("Delete Device", xPos, yPos, p);
    }
    private void deleteItem(final int position) {//Deletes mutually from DB and adapter
        item = listData.get(position);
        listData.remove(position);
        adapter.notifyItemRemoved(position);
        if(adapter.getItemCount()==0){
            show_hidden_text();
        }
        global.setDevice_list(listData);
    }

    private ItemTouchHelper.Callback createHelperCallback() {//Swiping
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0| 0,
                        ItemTouchHelper.LEFT | 0) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return true;
                    }

                    @Override
                    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        deleteItem(viewHolder.getAdapterPosition());
                    }

                    @Override
                    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        custom_onChildDraw(c, viewHolder);
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    }
                };
        return simpleItemTouchCallback;
    }
    private void show_hidden_text(){
        recView.setVisibility(View.GONE);
        hiddenText.setVisibility(View.VISIBLE);
    }

//    private ProgressDialog working_dialog;
//
//    private void showWorkingDialog() {
//        working_dialog = ProgressDialog.show(getActivity(), "","Loading Devices", true);
//    }
//
//    private void removeWorkingDialog() {
//        if (working_dialog != null) {
//            working_dialog.dismiss();
//            working_dialog = null;
//        }
//    }
}



