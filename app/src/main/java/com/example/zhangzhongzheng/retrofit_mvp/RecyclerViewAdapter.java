package com.example.zhangzhongzheng.retrofit_mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.zhangzhongzheng.retrofit_mvp.utils.MyLogger;

/**
 * Created by zhangzhongzheng on 2016/9/10.
 * <p>
 * 负责把数据适配到试图上
 * 负责 header 和 footer
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    protected LayoutInflater mInflater;
    private int count = 3;

    private int BEHAVIOR_MODE;
    protected int mState;

    public static final int NEITHER = 0;
    public static final int ONLY_HEADER = 1;
    public static final int ONLY_FOOTER = 2;
    public static final int BOTH_HEADER_FOOTER = 3;

    public static final int VIEW_TYPE_NORMAL = 0;
    public static final int VIEW_TYPE_HEADER = -1;
    public static final int VIEW_TYPE_FOOTER = -2;

    /**
     * recyclerview的显示模式 0都没有 1只有header。。。。
     *
     * @param context
     * @param mode
     */
    public RecyclerViewAdapter(Context context, int mode) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        checkMode(mode);
    }


    private void checkMode(int mode) {
        if (mode != NEITHER && mode != ONLY_HEADER && mode != ONLY_FOOTER && mode != BOTH_HEADER_FOOTER) {
            this.BEHAVIOR_MODE = NEITHER;
            MyLogger.log("", "check out your mode. the mode must be the one of NEITHER,ONLY_HEADER,ONLY_FOOTER,BOTH_HEADER_FOOTER");
        } else {
            this.BEHAVIOR_MODE = mode;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && (BEHAVIOR_MODE == ONLY_HEADER || BEHAVIOR_MODE == BOTH_HEADER_FOOTER))
            return VIEW_TYPE_HEADER;
        if (position + 1 == getItemCount() && (BEHAVIOR_MODE == ONLY_FOOTER || BEHAVIOR_MODE == BOTH_HEADER_FOOTER))
            return VIEW_TYPE_FOOTER;
        else return VIEW_TYPE_NORMAL;//super的这个方法返回的是0.
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                return null;
            case VIEW_TYPE_FOOTER:
                return new FooterViewHolder(mInflater.inflate(R.layout.layout_list_view_footer, parent, false));
            default:
                View view = View.inflate(mContext, R.layout.activity_main, null);
                ViewHolder vh = new ViewHolder(view);
                return vh;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_HEADER:
                break;
            case VIEW_TYPE_FOOTER:
                if (position > 10) {
                    holder.itemView.setVisibility(View.VISIBLE);
                } else {
                    holder.itemView.setVisibility(View.GONE);
                }
                break;
            default:
                ((ViewHolder) holder).mTextView.setText("wowo");
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (BEHAVIOR_MODE == ONLY_FOOTER || BEHAVIOR_MODE == ONLY_HEADER) {
            return count + 1;
        } else if (BEHAVIOR_MODE == BOTH_HEADER_FOOTER) {
            return count + 2;
        } else return count;
    }

    public final void addItem() {
        count++;
        notifyItemChanged(count);
    }

    public final void removeItem() {
        if (count >= 1) {
            count--;
            notifyItemRemoved(count + 1);
        }
    }

    public void updateItem(int position) {
        if (getItemCount() > position) {
            notifyItemChanged(position);
        }
    }

    /**
     * @param mState
     * @param isUpdate 设置了footer的状态后是否立即刷新footer状态
     */
    public void setmState(int mState, boolean isUpdate) {
        this.mState = mState;
        if (isUpdate) {
            updateItem(getItemCount() - 1);
        }
    }

    public int getState() {
        return mState;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.hello);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;
        private TextView state;

        public FooterViewHolder(View itemView) {
            super(itemView);
            this.progressBar = (ProgressBar) itemView.findViewById(R.id.pb_footer);
            this.state = (TextView) itemView.findViewById(R.id.tv_footer);
        }
    }


}
