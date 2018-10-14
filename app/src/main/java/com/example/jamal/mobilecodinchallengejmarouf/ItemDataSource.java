package com.example.jamal.mobilecodinchallengejmarouf;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    private static final String SORT = "stars";
    private static final int FIRST_PAGE = 0;
    private static final String ORDER = "desc";
    private static final String QUERY = "created:>"+Util.getTargetDate();
    public  static final int PAGE_SIZE=100;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {
        RetrofitUser.getInstance()
                .getApi().getRepositories(QUERY, SORT, ORDER,FIRST_PAGE)
                .enqueue(new Callback<GithubApiResponse>() {
                    @Override
                    public void onResponse(Call<GithubApiResponse> call, Response<GithubApiResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().items, null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubApiResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitUser.getInstance()
                .getApi().getRepositories(QUERY,SORT,ORDER,params.key)
                .enqueue(new Callback<GithubApiResponse>() {
                    @Override
                    public void onResponse(Call<GithubApiResponse> call, Response<GithubApiResponse> response) {
                        Integer adjacentKey = (params.key > 0) ? params.key - 1 : null;
                        if (response.body() != null) {
                            callback.onResult(response.body().items, adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubApiResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitUser.getInstance()
                .getApi()
                .getRepositories(QUERY,SORT,ORDER,params.key)
                .enqueue(new Callback<GithubApiResponse>() {
                    @Override
                    public void onResponse(Call<GithubApiResponse> call, Response<GithubApiResponse> response) {
                        if (response.body() != null && response.body().incomplete_results) {
                            callback.onResult(response.body().items, params.key + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubApiResponse> call, Throwable t) {

                    }
                });
    }
}

