package com.example.heroes_vs_villains.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.heroes_vs_villains.Callback.CallBack_List;
import com.example.heroes_vs_villains.MYSP;
import com.example.heroes_vs_villains.R;
import com.example.heroes_vs_villains.Top10;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
public class Fragment_List extends Fragment {

    private static Fragment_List INSTANCE = null;
    View view;
    private CallBack_List callBack_list;
    private ArrayList<TextView> listFragment_LBL_name = new ArrayList<>();
    private ArrayList<TextView> listFragment_LBL_moves = new ArrayList<>();
    private ArrayList<TextView> listFragment_LBL_time = new ArrayList<>();
    private ArrayList <Top10> top10;


    public void setTop10ActivityCallBack(CallBack_List callBack_list){
        this.callBack_list = callBack_list;
    }
    public Fragment_List(){

    }
    public static Fragment_List getINSTANCE()
    {
        if(INSTANCE==null)
            INSTANCE = new Fragment_List();
        return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list,container,false);

            findViews();
            ArrayList<Top10> arrayList = getListFromSp();
            addDataToList(arrayList);

        return view;
    }

    private ArrayList<Top10> getListFromSp() {
        Gson gson = new Gson();
        String json = MYSP.getInstance().getString("TOP10", "");
        Type type =  new TypeToken<ArrayList<Top10>>() {}.getType();
        ArrayList<Top10> arrayList = gson.fromJson(json, type);
        return  arrayList;
    }

    @SuppressLint("SetTextI18n")
    private void addDataToList(ArrayList<Top10> arrayList) {
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                listFragment_LBL_name.get(i).setText(arrayList.get(i).getName());
                listFragment_LBL_moves.get(i).setText(arrayList.get(i).getNumOfMoves() + "");
                listFragment_LBL_time.get(i).setText(arrayList.get(i).getTime() + "");
            }


        }
    }

        private void findViews () {
            listFragment_LBL_name.add((TextView) view.findViewById(R.id.listFragment_LBL_row1name));
            listFragment_LBL_name.add((TextView) view.findViewById(R.id.listFragment_LBL_row2name));
            listFragment_LBL_name.add((TextView) view.findViewById(R.id.listFragment_LBL_row3name));
            listFragment_LBL_name.add((TextView) view.findViewById(R.id.listFragment_LBL_row4name));
            listFragment_LBL_name.add((TextView) view.findViewById(R.id.listFragment_LBL_row5name));
            listFragment_LBL_name.add((TextView) view.findViewById(R.id.listFragment_LBL_row6name));
            listFragment_LBL_name.add((TextView) view.findViewById(R.id.listFragment_LBL_row7name));
            listFragment_LBL_name.add((TextView) view.findViewById(R.id.listFragment_LBL_row8name));
            listFragment_LBL_name.add((TextView) view.findViewById(R.id.listFragment_LBL_row9name));
            listFragment_LBL_name.add((TextView) view.findViewById(R.id.listFragment_LBL_row10name));

            listFragment_LBL_moves.add((TextView) view.findViewById(R.id.listFragment_LBL_row1moves));
            listFragment_LBL_moves.add((TextView) view.findViewById(R.id.listFragment_LBL_row2moves));
            listFragment_LBL_moves.add((TextView) view.findViewById(R.id.listFragment_LBL_row3moves));
            listFragment_LBL_moves.add((TextView) view.findViewById(R.id.listFragment_LBL_row4moves));
            listFragment_LBL_moves.add((TextView) view.findViewById(R.id.listFragment_LBL_row5moves));
            listFragment_LBL_moves.add((TextView) view.findViewById(R.id.listFragment_LBL_row6moves));
            listFragment_LBL_moves.add((TextView) view.findViewById(R.id.listFragment_LBL_row7moves));
            listFragment_LBL_moves.add((TextView) view.findViewById(R.id.listFragment_LBL_row8moves));
            listFragment_LBL_moves.add((TextView) view.findViewById(R.id.listFragment_LBL_row9moves));
            listFragment_LBL_moves.add((TextView) view.findViewById(R.id.listFragment_LBL_row10moves));

            listFragment_LBL_time.add((TextView) view.findViewById(R.id.listFragment_LBL_row1time));
            listFragment_LBL_time.add((TextView) view.findViewById(R.id.listFragment_LBL_row2time));
            listFragment_LBL_time.add((TextView) view.findViewById(R.id.listFragment_LBL_row3time));
            listFragment_LBL_time.add((TextView) view.findViewById(R.id.listFragment_LBL_row4time));
            listFragment_LBL_time.add((TextView) view.findViewById(R.id.listFragment_LBL_row5time));
            listFragment_LBL_time.add((TextView) view.findViewById(R.id.listFragment_LBL_row6time));
            listFragment_LBL_time.add((TextView) view.findViewById(R.id.listFragment_LBL_row7time));
            listFragment_LBL_time.add((TextView) view.findViewById(R.id.listFragment_LBL_row8time));
            listFragment_LBL_time.add((TextView) view.findViewById(R.id.listFragment_LBL_row9time));
            listFragment_LBL_time.add((TextView) view.findViewById(R.id.listFragment_LBL_row10time));

            // for(TextView btn : listFragment_LBL_name) btn.setOnClickListener(buttonClickeListener);
           // for(TextView btn1 : listFragment_LBL_moves) btn1.setOnClickListener(buttonClickeListener);
           // for(TextView btn2 : listFragment_LBL_time) btn2.setOnClickListener(buttonClickeListener);


        }
      /*  private View.OnClickListener buttonClickeListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int playerRank = Integer.parseInt(((TextView) view).getTag().toString());
                Log.d("pttt", "  " + playerRank);
                top10 = getListFromSp();
                callBack_list.listChoosePlayerNumberN(top10.get(playerRank-1));
                //  callBack_TopNBoard.listChoosePlayerNumberN(top10.get().get(playerRank-1)); //index from 0
            }
        };*/

}
