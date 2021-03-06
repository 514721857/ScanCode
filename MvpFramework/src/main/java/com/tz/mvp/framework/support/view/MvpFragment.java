package com.tz.mvp.framework.support.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.View;

import com.tz.mvp.framework.base.presenter.MvpPresenter;
import com.tz.mvp.framework.base.view.MvpView;
import com.tz.mvp.framework.support.delegate.fragment.FragmentMvpDelegate;
import com.tz.mvp.framework.support.delegate.fragment.FragmentMvpDelegateCallback;
import com.tz.mvp.framework.support.delegate.fragment.FragmentMvpDelegateImpl;

public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>>
		extends Fragment implements FragmentMvpDelegateCallback<V, P>, MvpView {

	private P presenter;

	private FragmentMvpDelegate<V, P> activityMvpDelegate;

	protected FragmentMvpDelegate<V, P> getFragmentMvpDelegate() {
		if (this.activityMvpDelegate == null) {
			this.activityMvpDelegate = new FragmentMvpDelegateImpl<V, P>(this);
		}
		return this.activityMvpDelegate;
	}

	@Override
	public P getPresenter() {
		return this.presenter;
	}

	@Override
	public void setPresenter(P presenter) {
		this.presenter = presenter;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V getMvpView() {
		return (V) this;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		getFragmentMvpDelegate().onAttach(context);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentMvpDelegate().onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getFragmentMvpDelegate().onActivityCreated(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getFragmentMvpDelegate().onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
		getFragmentMvpDelegate().onStart();
	}

	@Override
	public void onPause() {
		super.onPause();
		getFragmentMvpDelegate().onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		getFragmentMvpDelegate().onResume();
	}

	@Override
	public void onStop() {
		super.onStop();
		getFragmentMvpDelegate().onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		getFragmentMvpDelegate().onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getFragmentMvpDelegate().onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		getFragmentMvpDelegate().onDetach();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getFragmentMvpDelegate().onSaveInstanceState(outState);
	}

	@Override
	public boolean isRetainInstance() {
		return false;
	}

	@Override
	public boolean shouldInstanceBeRetained() {
		return false;
	}

}
