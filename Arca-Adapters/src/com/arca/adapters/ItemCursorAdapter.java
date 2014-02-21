package com.arca.adapters;


import java.util.Collection;
import java.util.List;

import android.database.Cursor;
import android.view.View;

import com.arca.adapters.ViewBinder.DefaultViewBinder;

public class ItemCursorAdapter {

	private static final int TYPE = 0;
	
	private final CursorAdapterHelper mHelper;
	private final DefaultViewBinder mDefaultBinder;
	private final View mView;
	
	private ViewBinder mViewBinder;
	private Cursor mCursor;
	
	public ItemCursorAdapter(final View view, final Collection<Binding> bindings) {
		mHelper = new CursorAdapterHelper(bindings);
		mDefaultBinder = new DefaultViewBinder();
		mView = view;
	}
	
	public void setViewBinder(final ViewBinder viewBinder) {
		mViewBinder = viewBinder;
	}
	
	public boolean hasResults() {
		final Cursor cursor = getCursor();
		return cursor != null && cursor.getCount() > 0;
	}
	
	public Cursor getCursor() {
		return mCursor;
	}
	
	public void swapCursor(final Cursor cursor) {
		mCursor = cursor;

		bindViewAtPosition(mView, cursor, 0);
	}
	
	public void bindViewAtPosition(final View container, final Cursor cursor, final int position) {
		if (cursor != null && cursor.moveToPosition(position)) {
			bindView(container, cursor);
		}
	}

	public void bindView(final View container, final Cursor cursor) {
		final List<Binding> bindings = getBindings(cursor);
		
		for (final Binding binding : bindings) {
			bindView(container, cursor, binding);
		}
	}
	
	private List<Binding> getBindings(final Cursor cursor) {
		return mHelper.getBindings(TYPE, cursor);
	}
	
	private void bindView(final View container, final Cursor cursor, final Binding binding) {
		final View view = mHelper.getView(container, binding);

		boolean bound = false;
		
		if (mViewBinder != null) {
			bound = mViewBinder.setViewValue(view, cursor, binding);
		}
		
		if (!bound) {
			bound = mDefaultBinder.setViewValue(view, cursor, binding);
		}
		
		if (!bound) {
			throw new IllegalStateException("Connot bind to view: " + view);
		}
	}
}