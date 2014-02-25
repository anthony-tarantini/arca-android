package com.arca.adapters;


import java.util.Collection;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

public class SupportItemAdapter extends CursorAdapter {
	
	private final CursorAdapterHelper mHelper;

	public SupportItemAdapter(final Context context, final Collection<Binding> bindings) {
		super(context, null, 0);
		mHelper = new CursorAdapterHelper(bindings);
	}
	
	public void setViewBinder(final ViewBinder binder) {
		mHelper.setViewBinder(binder);
	}

	public boolean hasResults() {
		final Cursor cursor = getCursor();
		return cursor != null && cursor.getCount() > 0;
	}
	
	@Override
	public void bindView(final View container, final Context context, final Cursor cursor) {
		final int type = getItemViewType(cursor.getPosition());
		mHelper.bindView(container, context, cursor, type);
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public View newView(final Context context, final Cursor cursor, final ViewGroup parent) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public View newDropDownView(final Context context, final Cursor cursor, final ViewGroup parent) {
		throw new UnsupportedOperationException();
	}

}
