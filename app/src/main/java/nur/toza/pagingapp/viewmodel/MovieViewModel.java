package nur.toza.pagingapp.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;
import nur.toza.pagingapp.module.Movie;
import nur.toza.pagingapp.paging.MoviePagingSource;

public class MovieViewModel extends ViewModel {
    public Flowable<PagingData<Movie>> moviePagingDataFlowable;

    public MovieViewModel(){
        init();
    }
    private void init(){
        //defining Paging Source
        MoviePagingSource moviePagingSource = new MoviePagingSource();
        Pager<Integer,Movie> pager = new Pager<>(
                new PagingConfig(
                20,20,false,20,
                20*499),
                () -> moviePagingSource);

        // Flowable
        moviePagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(moviePagingDataFlowable,coroutineScope);
    }
}
