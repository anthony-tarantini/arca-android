package com.arca.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;

import com.arca.dispatcher.Error;
import com.arca.dispatcher.QueryResult;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class ArcaAdapterFragment extends ArcaQueryFragment {
	
	protected abstract int getAdapterViewId();
	
	public abstract CursorAdapter onCreateAdapter(final AdapterView<CursorAdapter> adapterView, final Bundle savedInstanceState);
	
	private AdapterView<CursorAdapter> mAdapterView;
	
	@Override
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public void onViewCreated(final View view, final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		setupAdapterView(view, savedInstanceState);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
			setupAdapterView(getView(), null);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void setupAdapterView(final View view, final Bundle savedInstanceState) {
		final int adapterViewId = getAdapterViewId();	
		mAdapterView = (AdapterView<CursorAdapter>) view.findViewById(adapterViewId);
		mAdapterView.setAdapter(onCreateAdapter(mAdapterView, savedInstanceState));
	}
	
	public AdapterView<CursorAdapter> getAdapterView() {
		return mAdapterView;
	}
	
	public CursorAdapter getCursorAdapter() {
		final AdapterView<CursorAdapter> adapterView = getAdapterView();
		if (adapterView != null) {
			return adapterView.getAdapter();
		} else {
			return null;
		}
	}
	
	@Override
	public final void onRequestComplete(final QueryResult result) {
		if (result.hasError()) {
			onContentError(result.getError());
		} else {
			getCursorAdapter().swapCursor(result.getResult());
			onContentChanged(result);
		}
	}
	
	@Override
	public final void onRequestReset() {
		getCursorAdapter().swapCursor(null);
		onContentReset();
	}
	
	protected void onContentChanged(final QueryResult result) {}
	protected void onContentError(final Error error) {}
	protected void onContentReset() {}
}