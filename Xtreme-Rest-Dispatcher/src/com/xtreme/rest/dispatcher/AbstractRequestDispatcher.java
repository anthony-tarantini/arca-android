package com.xtreme.rest.dispatcher;

import android.os.Bundle;

abstract class AbstractRequestDispatcher implements RequestDispatcher {
	
	protected static final class Extras {
		public static final String REQUEST = "request";
	}
	
	private final RequestExecutor mRequestExecutor;

	public AbstractRequestDispatcher(final RequestExecutor executor) {
		mRequestExecutor = executor;
	}
	
	public RequestExecutor getRequestExecutor() {
		return mRequestExecutor;
	}
	
	@Override
	public QueryResult execute(final Query request) {
		return getRequestExecutor().execute(request);
	}
	
	@Override
	public UpdateResult execute(final Update request) {
		return getRequestExecutor().execute(request);
	}
	
	@Override
	public InsertResult execute(final Insert request) {
		return getRequestExecutor().execute(request);
	}
	
	@Override
	public DeleteResult execute(final Delete request) {
		return getRequestExecutor().execute(request);
	}
	
	protected static Bundle createRequestBundle(final ContentRequest<?> request) {
		final Bundle bundle = new Bundle();
		bundle.putParcelable(Extras.REQUEST, request);
		return bundle;
	}
}