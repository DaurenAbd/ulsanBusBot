package com.google.zxing.client.android.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.google.zxing.client.android.C0224R;
import java.util.List;

final class SearchBookContentsAdapter extends ArrayAdapter<SearchBookContentsResult> {
    SearchBookContentsAdapter(Context context, List<SearchBookContentsResult> items) {
        super(context, C0224R.layout.search_book_contents_list_item, 0, items);
    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        View listItem;
        if (view == null) {
            listItem = (SearchBookContentsListItem) LayoutInflater.from(getContext()).inflate(C0224R.layout.search_book_contents_list_item, viewGroup, false);
        } else if (!(view instanceof SearchBookContentsListItem)) {
            return view;
        } else {
            SearchBookContentsListItem listItem2 = (SearchBookContentsListItem) view;
        }
        listItem.set((SearchBookContentsResult) getItem(position));
        return listItem;
    }
}
