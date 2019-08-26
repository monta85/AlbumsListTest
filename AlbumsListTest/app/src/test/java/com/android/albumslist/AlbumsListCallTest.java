package com.android.albumslist;

import com.android.albumslist.adapter.AlbumsAdapter;
import com.android.albumslist.api.Api;
import com.android.albumslist.api.Client;
import com.android.albumslist.model.Album;
import com.android.albumslist.view.AlbumsListActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@Config(constants = BuildConfig.class, sdk = 21,
    manifest = "app/src/main/AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)
public class AlbumsListCallTest {

    private AlbumsListActivity mainActivity;

    @Mock
    private Api mockRetrofitApiImpl;

    @Captor
    private ArgumentCaptor<Callback<List<Album>>> callbackArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ActivityController<AlbumsListActivity> controller = Robolectric.buildActivity(AlbumsListActivity.class);
        mainActivity = controller.get();

        // Then we need to swap the retrofit api impl. with a mock one

       //Client.setApi(mockRetrofitApiImpl);

        controller.create();
    }

    @Test
    public void shouldFillAdapter() throws Exception {
        Mockito.verify(mockRetrofitApiImpl)
            .getAlbums();

        int objectsQuantity = 10;
        List<Album> list = new ArrayList<Album>();
        for(int i = 0; i < objectsQuantity; ++i) {
            list.add(new Album());
        }

        //callbackArgumentCaptor.getValue().success(list, null);

       // AlbumsAdapter yourAdapter = mainActivity.getAdapter(); // Obtain adapter
        // Simple test check if adapter has as many items as put into response
        //assertThat(yourAdapter.getItemCount(), equalTo(objectsQuantity));
    }
}
