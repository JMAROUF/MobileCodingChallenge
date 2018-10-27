package com.example.jamal.mobilecodinchallengejmarouf.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.example.jamal.mobilecodinchallengejmarouf.datasource.ItemDataSource;
import com.example.jamal.mobilecodinchallengejmarouf.datasource.factory.ItemDataSourceFactory;
import com.example.jamal.mobilecodinchallengejmarouf.model.Item;

public class ItemViewModel extends ViewModel {

    LiveData<PagedList<Item>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Item>> liveDataSource;

    public ItemViewModel() {
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE).build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }

    public LiveData<PagedList<Item>> getItemPagedList() {
        return itemPagedList;
    }
}

