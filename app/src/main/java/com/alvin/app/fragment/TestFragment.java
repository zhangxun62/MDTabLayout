package com.alvin.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @Title TestFragment
 * @Description:
 * @Author: alvin
 * @Date: 2016/10/27.17:08
 * @E-mail: 49467306@qq.com
 */
public class TestFragment extends Fragment {
    private static final String TAG = TestFragment.class.getSimpleName();
    private String mTitle;
    private RecyclerView mRecyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mTitle = getArguments().getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = new RecyclerView(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new Adapter());
        return mRecyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public static Fragment newInstance(String i) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", i);
        fragment.setArguments(bundle);
        return fragment;
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(item);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(mTitle + "_" + position);
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView text;
            public ViewHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
