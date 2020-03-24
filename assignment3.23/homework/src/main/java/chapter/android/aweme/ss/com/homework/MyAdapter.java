package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MessageViewHolder> {

    private static final String TAG = "Myadapter";

    private ListItemClickListener mOnClickListener;
    private List<Message> MessagesList;

    public MyAdapter(List<Message> Messages, ListItemClickListener listener) {
        MessagesList = Messages;
        mOnClickListener = listener;
    }


    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        MessageViewHolder viewHolder = new MessageViewHolder(view);
        Log.d(TAG, "onCreateViewHolder\n");
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        messageViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return MessagesList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView avatar;
        private ImageView robotNotice;
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvTime;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = (CircleImageView) itemView.findViewById(R.id.iv_avatar);
            robotNotice = (ImageView) itemView.findViewById(R.id.robot_notice);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            Message curMessage = MessagesList.get(position);
            tvTitle.setText(curMessage.getTitle());
            tvDescription.setText(curMessage.getDescription());
            tvTime.setText(curMessage.getTime());
            if(curMessage.isOfficial())
                robotNotice.setImageResource(R.drawable.im_icon_notice_official);
            switch (curMessage.getIcon()){
                case "TYPE_ROBOT":
                    avatar.setImageResource(R.drawable.session_robot);
                    break;
                case "TYPE_SYSTEM":
                    avatar.setImageResource(R.drawable.session_system_notice);
                    break;
                case "TYPE_USER":
                    avatar.setImageResource(R.drawable.icon_girl);
                    break;
                case "TYPE_STRANGER":
                    avatar.setImageResource(R.drawable.session_stranger);
            }

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
