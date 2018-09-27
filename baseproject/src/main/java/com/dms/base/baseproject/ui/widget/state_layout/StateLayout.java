package com.dms.base.baseproject.ui.widget.state_layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dms.base.baseproject.R;


public class StateLayout extends FrameLayout implements IStateView {

    private SparseArray<View> mStateViews = new SparseArray<>(4);

    private int mShowViewIndex = IStateView.CONTENT;

    private OnClickListener mErrorAction, mEmptyAction;

    private ImageView mErrorIv, mEmptyIv;

    private TextView mErrorTitleTv, mErrorContentTv, mEmptyTitleTv, mEmptyContentTv;

    public StateLayout(@NonNull Context context) {
        this(context, null);
        init(context, null);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StateLayout, 0, 0);

        int loadingViewId, errorViewId, emptyViewId;

        try {
            loadingViewId = typedArray.getResourceId(R.styleable.StateLayout_loadingView, -1);
            if (-1 == loadingViewId) {
                loadingViewId = R.layout.view_loading;
            }
            errorViewId = typedArray.getResourceId(R.styleable.StateLayout_errorView, -1);
            if (-1 == errorViewId) {
                errorViewId = R.layout.view_error;
            }
            emptyViewId = typedArray.getResourceId(R.styleable.StateLayout_emptyView, -1);
            if (-1 == emptyViewId) {
                emptyViewId = R.layout.view_empty;
            }
        } finally {
            typedArray.recycle();
        }

        setLoadingResource(loadingViewId);
        setErrorResource(errorViewId);
        setEmptyResource(emptyViewId);
    }

    /**
     * showView
     */

    private void switchShowView(int toBeShow) {
        if (mShowViewIndex == toBeShow) {
            return;
        }
        if (null != mStateViews.get(mShowViewIndex)) {
            mStateViews.get(mShowViewIndex).setVisibility(GONE);
        }
        if (null != mStateViews.get(toBeShow)) {
            mStateViews.get(toBeShow).setVisibility(VISIBLE);
        }
        mShowViewIndex = toBeShow;
    }

    public void showContent() {
        switchShowView(IStateView.CONTENT);
    }

    public void showLoading() {
        switchShowView(IStateView.LOADING);
    }


    public void showEmpty() {
        switchShowView(IStateView.EMPTY);
    }

    @Override
    public void showEmpty(int img, String title, String content) {
        switchShowView(IStateView.EMPTY);
        if (null != mEmptyIv && img > 0) {
            mEmptyIv.setImageResource(img);
        }
        if (null != mEmptyTitleTv) {
            mEmptyTitleTv.setText(title);
        }
        if (null != mEmptyContentTv) {
            mEmptyContentTv.setText(content);
        }
    }

    public void showError() {
        switchShowView(IStateView.ERROR);
    }

    @Override
    public void showError(int img, String title, String content) {
        switchShowView(IStateView.ERROR);
        if (null != mErrorIv && img > 0) {
            mErrorIv.setImageResource(img);
        }
        if (null != mErrorTitleTv) {
            mErrorTitleTv.setText(title);
        }
        if (null != mErrorContentTv) {
            mErrorContentTv.setText(content);
        }
    }

    /**
     * setView
     */
    @Override
    public void setLoadingResource(int resource) {
        if (resource <= 0) {
            return;
        }
        View view = mStateViews.get(IStateView.LOADING);
        if (null != view) {
            removeView(view);
        }
        ViewStub viewStub = new ViewStub(getContext(), resource);
        mStateViews.put(IStateView.LOADING, viewStub);
        addView(viewStub);
    }

    @Override
    public void setErrorResource(int resource) {
        if (resource <= 0) {
            return;
        }
        View view = mStateViews.get(IStateView.ERROR);
        if (null != view) {
            removeView(view);
        }
        ViewStub viewStub = new ViewStub(getContext(), resource);
        viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                inflated.setOnClickListener(mErrorAction);
                mErrorIv = inflated.findViewById(R.id.error_iv);
                mErrorTitleTv = inflated.findViewById(R.id.error_title_tv);
                mErrorContentTv = inflated.findViewById(R.id.error_content_tv);
            }
        });
        mStateViews.put(IStateView.ERROR, viewStub);
        addView(viewStub);
    }

    @Override
    public void setEmptyResource(int resource) {
        if (resource <= 0) {
            return;
        }
        View view = mStateViews.get(IStateView.EMPTY);
        if (null != view) {
            removeView(view);
        }
        ViewStub viewStub = new ViewStub(getContext(), resource);
        viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                inflated.setOnClickListener(mEmptyAction);
                mEmptyIv = inflated.findViewById(R.id.empty_iv);
                mEmptyTitleTv = inflated.findViewById(R.id.empty_title_tv);
                mEmptyContentTv = inflated.findViewById(R.id.empty_content_tv);
            }
        });
        mStateViews.put(IStateView.EMPTY, viewStub);
        addView(viewStub);
    }

    @Override
    public void setContentResource(int resource) {
        if (resource <= 0) {
            return;
        }
        View view = mStateViews.get(IStateView.CONTENT);
        if (null != view) {
            removeView(view);
        }
        View contentView = LayoutInflater.from(getContext()).inflate(resource, this, false);
        mStateViews.put(IStateView.CONTENT, contentView);
        addView(contentView);
    }

    /**
     * setAction
     */
    @Override
    public void setErrorAction(OnClickListener onClickListener) {
        mErrorAction = onClickListener;
    }

    @Override
    public void setEmptyAction(OnClickListener onClickListener) {
        mEmptyAction = onClickListener;
    }

    /**
     * addView
     */

    private void checkIsContentView(View view) {
        if (mStateViews.get(IStateView.CONTENT) != null
                || view == mStateViews.get(IStateView.LOADING)
                || view == mStateViews.get(IStateView.ERROR)
                || view == mStateViews.get(IStateView.EMPTY)) {
            return;
        }

        mStateViews.put(IStateView.CONTENT, view);
    }

    @Override
    public void addView(View child) {
        checkIsContentView(child);
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        checkIsContentView(child);
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        checkIsContentView(child);
        super.addView(child, index, params);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        checkIsContentView(child);
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int width, int height) {
        checkIsContentView(child);
        super.addView(child, width, height);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        checkIsContentView(child);
        return super.addViewInLayout(child, index, params);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        checkIsContentView(child);
        return super.addViewInLayout(child, index, params, preventRequestLayout);
    }


}
