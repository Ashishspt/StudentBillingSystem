package com.example.studentbillingsystem.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.PluralsRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.studentbillingsystem.R;
import com.example.studentbillingsystem.activities.NewsDetailsActivity;
import com.example.studentbillingsystem.helpers.AppFragment;
import com.example.studentbillingsystem.helpers.AppRecyclleViewAdaapter;
import com.example.studentbillingsystem.helpers.DefineClassType;
import com.example.studentbillingsystem.helpers.SpacesItemDecoration;
import com.example.studentbillingsystem.models.News;
import com.example.studentbillingsystem.presenters.NewsPresenter;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;


public class NotificationFragment extends AppFragment implements NewsPresenter.View {
    private RecyclerView recyclerView;
    private NotificationRecyclerViewAdapter notificationRecyclerViewAdapter;
    private NewsPresenter newsPresenter;
    private  HeaderRecyclerViewAdapter headerRecyclerViewAdapter;
    private  FooterRecyclerViewAdapter footerRecyclerViewAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        initializedView(view);
        initializedListners();

        return view;
    }

    private void prepareRecyclerView() {
        notificationRecyclerViewAdapter=new NotificationRecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(notificationRecyclerViewAdapter);


    }

    private void prepareHeaderRecyclerView(RecyclerView recyclerView){
      headerRecyclerViewAdapter =new HeaderRecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(headerRecyclerViewAdapter);

    }
    private void prepareFooterRecyclerView(RecyclerView recyclerView){
       footerRecyclerViewAdapter=new FooterRecyclerViewAdapter();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(footerRecyclerViewAdapter);



    }




    @Override
    protected void initializedView(View view) {
        recyclerView=view.findViewById(R.id.notification_recycler_view);


    }

    @Override
    protected void initializedListners() {
        newsPresenter=new NewsPresenter(this);
        newsPresenter.news();
        prepareRecyclerView();

    }

    @Override
    public void onNewsSucess(News news) {
        Log.e( "onNewsSucess: ",new GsonBuilder().create().toJson(news));
        headerRecyclerViewAdapter.add(news);
        headerRecyclerViewAdapter.notifyDataSetChanged();
        footerRecyclerViewAdapter.add(news);
        footerRecyclerViewAdapter.notifyDataSetChanged();



    }

    @Override
    public void onFailure(String message) {

    }

    public class NotificationRecyclerViewAdapter extends AppRecyclleViewAdaapter{
        private final int TYPE_HEADER=1;
        private final int TYPE_FOOTER=2;
        @Override
        public void add(Object object) {

        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(viewType==1){
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_news,parent,false);
                return new VHHeader(view);
            }
            if(viewType==2){
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer_news,parent,false);
                return new VHFooter(view);
            }
            throw new RuntimeException("view type doesnot exit");
        }

        @Override
        public int getItemViewType(int position) {
            if(isTypeHeader(position)){
                return TYPE_HEADER;
            }
            return TYPE_FOOTER;
        }
        private boolean isTypeHeader(int position) {
            return position==0;
        }
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof VHHeader){
                VHHeader vhHeader= (VHHeader) holder;
                prepareHeaderRecyclerView(vhHeader.recyclerView);

            }
            else if(holder instanceof VHFooter){
                VHFooter vhFooter= (VHFooter) holder;
                prepareFooterRecyclerView(vhFooter.recyclerView);
            }

        }

        @Override
        public int getItemCount() {
            return 2;
        }

        private class VHHeader extends RecyclerView.ViewHolder {
            private RecyclerView recyclerView;
            public VHHeader(View view) {
                super(view);
                recyclerView=view.findViewById(R.id.header_recycler_view);
            }
        }

        private class VHFooter extends RecyclerView.ViewHolder {
            private RecyclerView recyclerView;
            public VHFooter(View view) {
                super(view);
                recyclerView=view.findViewById(R.id.footer_recycler_view);
            }
        }
    }

    private class HeaderRecyclerViewAdapter extends AppRecyclleViewAdaapter{
        public  News news;

        @Override
        public void add(Object object) {
            news= DefineClassType.getType(object,News.class);


        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_news,parent,false);
            return new VhInnerHeader(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            VhInnerHeader vhInnerHeader= (VhInnerHeader) holder;
            final List<News.Detail> detailList=news.getDetails();
            vhInnerHeader.tvNewsTittle.setText(detailList.get(position).getTitle());
            Picasso.get()
                    .load(detailList.get(position).getImageURL())
                    .placeholder(R.drawable.mela)
                    .into(vhInnerHeader.imgNews);
            vhInnerHeader.mainContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), NewsDetailsActivity.class);
                    intent.putExtra("title",detailList.get(position).getTitle());
                    intent.putExtra("details",detailList.get(position).getDescription());
                    intent.putExtra("imgUrl",detailList.get(position).getImageURL());
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            if(news!=null){
                return news.getDetails().size();
            }
            return 0;
        }

        private class VhInnerHeader extends RecyclerView.ViewHolder {
            ImageView imgNews;
            TextView tvNewsTittle;
            LinearLayout mainContainer;
            public VhInnerHeader(View view) {
                super(view);
                imgNews=view.findViewById(R.id.imgNews);
                tvNewsTittle=view.findViewById(R.id.tvNewsTitle);
                mainContainer=view.findViewById(R.id.mainContainer);

            }
        }
    }

    private class FooterRecyclerViewAdapter extends AppRecyclleViewAdaapter{
        public News news;

        @Override
        public void add(Object object) {
            news=DefineClassType.getType(object,News.class);


        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_news,parent,false);
            return new VhInnerFooter(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            VhInnerFooter vhInnerFooter= (VhInnerFooter) holder;
             final List<News.Detail> detailList=news.getDetails();
            vhInnerFooter.tvNewsTitle.setText(detailList.get(position).getTitle());
            Picasso.get()
                    .load(detailList.get(position).getImageURL())
                    .placeholder(R.drawable.mela)
                    .into(vhInnerFooter.imgNews);
            vhInnerFooter.mainContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), NewsDetailsActivity.class);
                    intent.putExtra("title",detailList.get(position).getTitle());
                    intent.putExtra("details",detailList.get(position).getDescription());
                    intent.putExtra("imgUrl",detailList.get(position).getImageURL());
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            if(news!=null){
                return news.getDetails().size();
            }

            return 0;
        }


        private class VhInnerFooter extends RecyclerView.ViewHolder {
            ImageView imgNews;
            TextView tvNewsTitle;
            LinearLayout mainContainer;
            public VhInnerFooter(View view) {
                super(view);
                tvNewsTitle=view.findViewById(R.id.tvNewsTitle);
                imgNews=view.findViewById(R.id.imgNews);
                mainContainer=view.findViewById(R.id.mainContainer);
            }
        }
    }
}