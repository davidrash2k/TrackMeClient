package mobapde.trackme;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jasonsioco on 3/16/2016.
 */

public class UserCursorAdapter extends CursorRecyclerViewAdapter<UserCursorAdapter.UserViewHolder> {

        OnItemClickListener mOnItemClickListener;

        public UserCursorAdapter(Context context, Cursor cursor) {
            super(context, cursor);
        }

        @Override
        public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.useritem, null);
            return new UserViewHolder(v);
        }

        @Override
        public void onBindViewHolder(UserViewHolder viewHolder, Cursor cursor) {
            String name = cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME));
            viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int dbid = Integer.parseInt(v.getTag().toString());
                    mOnItemClickListener.onItemClick(dbid);
                }
            });
        }

        public void setmOnItemClickListener(OnItemClickListener m){

            this.mOnItemClickListener = m;

        }

        public interface OnItemClickListener{
            public void onItemClick(int id);
        }

        public class UserViewHolder extends RecyclerView.ViewHolder{
            TextView tvTitle;

            public UserViewHolder(View itemView) {
                super(itemView);
                //findViewById
                //tvTitle = (TextView) itemView.findViewById(R.id.tv_title);

            }
        }
}
