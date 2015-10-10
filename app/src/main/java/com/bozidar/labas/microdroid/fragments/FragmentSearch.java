package com.bozidar.labas.microdroid.fragments;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.utils.UtilSearch;

public class FragmentSearch extends Fragment {
    private FragmentCallbacks mCallbacks;
    private RelativeLayout mTRansBackView;
    private CardView mCardView;
    private ImageView mImageBack, mImageVoiceClear;
    private EditText mSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.test_fragment_search, container, false);
        mSearch = (EditText) v.findViewById(R.id.edit_text_search);
        mTRansBackView = (RelativeLayout) v.findViewById(R.id.view_search);
        mSearch.requestFocus();
        mCardView = (CardView) v.findViewById(R.id.card_search);
        mImageBack = (ImageView) v.findViewById(R.id.image_search_back);
        mImageVoiceClear = (ImageView) v.findViewById(R.id.clearSearch);

        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (mSearch.getText().toString().trim().length() > 0) {
                        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mSearch.getWindowToken(), 0);
                        mCallbacks.searchClicked(mSearch.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCardView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                UtilSearch.handleToolBar(getActivity(), mCardView, mTRansBackView, mSearch);
            }
        });



        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilSearch.handleToolBar(getActivity(), mCardView, mTRansBackView, mSearch);
                endFragment();
            }
        });

        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mSearch.getText().toString().length() == 0) {
                    mImageVoiceClear.setImageResource(R.mipmap.ic_keyboard_voice_white_24dp);
                } else {
                    //mImageVoiceClear.setImageResource(R.mipmap.ic_close_grey24dp);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mTRansBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilSearch.handleToolBar(getActivity(), mCardView, mTRansBackView, mSearch);
                endFragment();
            }
        });
    }

    private void endFragment() {
        final Handler handler = new Handler();
        mImageBack.setClickable(false);
        mImageVoiceClear.setClickable(false);
        mTRansBackView.setClickable(false);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }, 300);
    }

    public interface FragmentCallbacks {
        void searchClicked(String searchText);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (FragmentCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement Fragment Two.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }


}
