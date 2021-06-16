package com.meli.challenge.views.suggestion.viewmodel

import android.os.Build
import android.os.Looper
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.meli.challenge.model.SuggestionResponse
import com.meli.challenge.core.basemodel.Result
import com.meli.challenge.views.suggestion.repository.SuggestionRepository
import com.utils.TestUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ExplorerViewModelTest {

    @Mock
    lateinit var repository: SuggestionRepository

    lateinit var viewModel: ExplorerViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = ExplorerViewModel(repository)
    }

    @Test
    fun whenUserRequestForSuggestionsViewModelsReturnsIts() = runBlocking {

        Mockito.`when`(repository.getSuggestedQuery(QUERY_EXAMPLE))
            .thenReturn(RESULT)

        viewModel.searchItemByName(QUERY_EXAMPLE)

        Shadows.shadowOf(Looper.getMainLooper()).idle()

        val result = viewModel.onSuccessResponse.value

        Assert.assertEquals(result, SUGGESTION_RESPONSE)

    }

    @Test
    fun whenUserRequestForSuggestionsRepositoryReturnsAnException() = runBlocking {

        BDDMockito.given(repository.getSuggestedQuery(QUERY_EXAMPLE)).willAnswer {
            throw SocketTimeoutException("You lose internet connection")
        }

        viewModel.searchItemByName(QUERY_EXAMPLE)

        Shadows.shadowOf(Looper.getMainLooper()).idle()

        val result = viewModel.onConnectionError.value

        Assert.assertTrue(result!!)

    }

    companion object {
        const val QUERY_EXAMPLE = "ojotas"
        private val SUGGESTION_RESPONSE =
            TestUtils.getObjectFromJsonResource<SuggestionResponse>("suggestion_response")
        private val RESULT = Result.success(SUGGESTION_RESPONSE)
    }
}