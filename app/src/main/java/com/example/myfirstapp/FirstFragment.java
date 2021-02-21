package com.example.myfirstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.orhanobut.logger.Logger;

public class FirstFragment extends Fragment {

    TextView showCountTextView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);
        showCountTextView = fragmentFirstLayout.findViewById(R.id.textview_first);
        return fragmentFirstLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.toast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(), "Hello toast!", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        view.findViewById(R.id.count_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countMe(view);
            }
        });

        view.findViewById(R.id.random_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCount = Integer.parseInt(showCountTextView.getText().toString());

                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(action);
            }
        });
    }

    private void countMe(View view) {
        String countString = showCountTextView.getText().toString();
        Integer count = Integer.parseInt(countString);
        count++;
        showCountTextView.setText(count.toString());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Logger.i("~~~~~~~~~~Fragment onCreate Bundle null:" + (savedInstanceState == null ) );
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.i("~~~~~~~~~~Fragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.i("~~~~~~~~~~Fragment onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.i("~~~~~~~~~~Fragment onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i("~~~~~~~~~~Fragment onDestroy");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.i("~~~~~~~~~~Fragment onSaveInstanceState");
        String countString = showCountTextView.getText().toString();
        Logger.i("Save Count: %s", countString );
        outState.putString("MyCount", countString);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if( savedInstanceState != null  ) {
            Logger.i("Fragment onViewStateRestored");
            String countString = savedInstanceState.getString("MyCount");
            Logger.i("Load Count: %s", countString);
            showCountTextView.setText(countString);
        }
    }
}