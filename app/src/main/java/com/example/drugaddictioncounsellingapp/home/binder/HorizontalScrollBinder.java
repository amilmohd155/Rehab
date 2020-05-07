/*
 * *
 *  * Created by Amil Muhammed Hamza on 4/9/20 11:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/19/20 10:37 PM
 *
 */

package com.example.drugaddictioncounsellingapp.home.binder;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugaddictioncounsellingapp.R;
import com.example.drugaddictioncounsellingapp.mentor.Mentor;

import java.util.ArrayList;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.OnItemClickListener;

public class HorizontalScrollBinder extends ItemBinder<Boolean, HorizontalScrollBinder.HSViewBinder> {

    private ArrayList<Mentor> mentors;
    private MultiViewAdapter adapter;
    private ListSection<Mentor> listSection;
    private MentorBinder.MentorListener listener;

    public HorizontalScrollBinder(MentorBinder.MentorListener listener) {

        this.listener = listener;
        mentors = new ArrayList<>();
        adapter = new MultiViewAdapter();
        listSection = new ListSection<>();

    }

    public void setMentors(ArrayList<Mentor> mentors) {
        this.mentors = mentors;
        listSection.clear();
        listSection.addAll(mentors);
        adapter.notifyDataSetChanged();
    }

    public MultiViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(MultiViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public HSViewBinder createViewHolder(ViewGroup parent) {
        return new HSViewBinder(inflate(parent, R.layout.layout_recycler_view));
    }

    @Override
    public void bindViewHolder(HSViewBinder holder, Boolean item) {

        holder.recyclerView.setAdapter(adapter);

        adapter.unRegisterAllItemBinders();

        SpaceDecoration decoration = new SpaceDecoration(holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.mentor_card_margin));
        holder.recyclerView.addItemDecoration(decoration);

        MentorBinder binder = new MentorBinder(listener);

        adapter.registerItemBinders(binder);

        adapter.clearAllSelections();

        adapter.addSection(listSection);

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof Boolean;
    }

    static class HSViewBinder extends ItemViewHolder<Boolean> {

        RecyclerView recyclerView;

        public HSViewBinder(View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemViewCacheSize(2);
            recyclerView.setNestedScrollingEnabled(false);

        }

    }



    public class SpaceDecoration extends RecyclerView.ItemDecoration {

        int space;

        public SpaceDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

            outRect.top = space;
            outRect.bottom = space;
            outRect.left = space;

            if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1)
                outRect.right = space;
            else outRect.right = 0;
        }
    }

}
