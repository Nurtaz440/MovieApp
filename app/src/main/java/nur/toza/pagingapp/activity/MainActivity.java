package nur.toza.pagingapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import nur.toza.pagingapp.R;
import nur.toza.pagingapp.adapter.MoviesAdapter;
import nur.toza.pagingapp.adapter.MoviesLoadStateAdapter;
import nur.toza.pagingapp.databinding.ActivityMainBinding;
import nur.toza.pagingapp.util.GridSpace;
import nur.toza.pagingapp.util.MovieComparator;
import nur.toza.pagingapp.util.Utils;
import nur.toza.pagingapp.viewmodel.MovieViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    MovieViewModel mainActivityViewModel;
    ActivityMainBinding binding;
    MoviesAdapter moviesAdapter;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Utils.API_KEY == null || Utils.API_KEY.isEmpty()){
            Toast.makeText(this,"Error in API Key", Toast.LENGTH_SHORT).show();
        }
        moviesAdapter = new MoviesAdapter(new MovieComparator(),requestManager);
        mainActivityViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        initRecyclerView();
        //subscribe to paging data
        mainActivityViewModel.moviePagingDataFlowable.subscribe(moviePagingData -> {
            moviesAdapter.submitData(getLifecycle(),moviePagingData);
        });
    }
    private void initRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.rvMovie.setLayoutManager(gridLayoutManager);
        binding.rvMovie.addItemDecoration(new GridSpace(2,20,true));

        binding.rvMovie.setAdapter(
                moviesAdapter.withLoadStateFooter(
                        new MoviesLoadStateAdapter(v -> {
                            moviesAdapter.retry();
                        })
                )
        );
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return moviesAdapter.getItemViewType(position) == MoviesAdapter.LOADING_ITEM ? 1 : 2;
            }
        });
    }
}