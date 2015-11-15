package com.bozidar.microdroid.base;

import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bozidar.microdroid.R;
import com.bozidar.microdroid.searchview.OnSearchItemClickListener;
import com.bozidar.microdroid.searchview.SearchAdapter;
import com.bozidar.microdroid.utils.SharedPreference;
import com.bozidar.microdroid.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class MicroSearchActivity extends MicroActivity implements OnSearchItemClickListener {

    private ArrayList<String> data;
    private ArrayList<String> filterList;

    public void loadToolBarSearch() {



        ArrayList<String> countryStored = SharedPreference.loadList(this, Utils.PREFS_NAME, Utils.KEY_COUNTRIES);

        View view = getLayoutInflater().inflate(R.layout.view_toolbar_search, null);



        ImageView imgToolBack = (ImageView) view.findViewById(R.id.img_tool_back);
        final EditText edtToolSearch = (EditText) view.findViewById(R.id.edt_tool_search);
        ImageView imgToolMic = (ImageView) view.findViewById(R.id.img_tool_mic);
        final ListView listSearch = (ListView) view.findViewById(R.id.list_search);
        final TextView txtEmpty = (TextView) view.findViewById(R.id.txt_empty);

        Utils.setListViewHeightBasedOnChildren(listSearch);

        edtToolSearch.setHint("Search your country");


        final Dialog toolbarSearchDialog = new Dialog(this, R.style.MaterialSearch);
        toolbarSearchDialog.setContentView(view);
        toolbarSearchDialog.setCancelable(false);
        toolbarSearchDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        toolbarSearchDialog.getWindow().setGravity(Gravity.BOTTOM);
        toolbarSearchDialog.show();
        toolbarSearchDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        final SearchAdapter searchAdapter = new SearchAdapter(this, countryStored, false);
        listSearch.setVisibility(View.VISIBLE);








        edtToolSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                String[] country = setSearchableData();
                data = new ArrayList<>(Arrays.asList(country));
                listSearch.setVisibility(View.VISIBLE);
                searchAdapter.updateList(data, true);


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterList = new ArrayList<>();
                boolean isNodata = false;
                if (s.length() > 0) {
                    for (int i = 0; i < data.size(); i++) {


                        if (data.get(i).toLowerCase().startsWith(s.toString().trim().toLowerCase())) {
                            filterList.add(data.get(i));
                            listSearch.setVisibility(View.VISIBLE);
                            searchAdapter.updateList(filterList, true);
                            isNodata = true;
                        }
                    }
                    if (!isNodata) {
                        listSearch.setVisibility(View.GONE);
                        txtEmpty.setVisibility(View.VISIBLE);
                        txtEmpty.setText("No data found");
                    }
                } else {
                    listSearch.setVisibility(View.GONE);
                    txtEmpty.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imgToolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarSearchDialog.dismiss();
            }
        });

        imgToolMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtToolSearch.setText("");

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();

        } else if (id == R.id.action_search) {
            loadToolBarSearch();
        }

        return super.onOptionsItemSelected(item);
    }


    public abstract String[] setSearchableData();
}
