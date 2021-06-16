package com.meli.challenge.views.specificproduct.viewmodel

import android.os.Build.VERSION_CODES.O_MR1
import android.os.Looper.getMainLooper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.meli.challenge.core.basemodel.Result
import com.meli.challenge.model.Item
import com.meli.challenge.views.specificproduct.repository.SpecificProductRepository
import com.utils.TestUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import org.robolectric.annotation.LooperMode.Mode.PAUSED
import java.net.SocketTimeoutException


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LooperMode(PAUSED)
@Config(sdk = [O_MR1])
class SpecificProductViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: SpecificProductRepository

    lateinit var viewModel: SpecificProductViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = SpecificProductViewModel(repository)
    }

    @Test
    fun whenUserRequestSpecificItemViewModelsReturnsIt() = runBlocking() {

        Mockito.`when`(repository.getSpecificProduct(FAKE_PRODUCT_ID))
            .thenReturn(RESULT_ITEM)

        viewModel.findSpecificProduct(FAKE_PRODUCT_ID)

        shadowOf(getMainLooper()).idle()

        val result = viewModel.onSuccessResponse.value

        assertEquals(result, ITEM)

    }

    @Test
    fun whenUserRequestSpecificItemRepositoryReturnsAnException() = runBlocking() {

        given(repository.getSpecificProduct(FAKE_PRODUCT_ID)).willAnswer {
            throw SocketTimeoutException("You lose internet connection")
        }

        viewModel.findSpecificProduct(FAKE_PRODUCT_ID)

        shadowOf(getMainLooper()).idle()

        val result = viewModel.onConnectionError.value

        assertTrue(result!!)

    }

    companion object {
        private const val FAKE_PRODUCT_ID = "MLA-1234"
        private val ITEM = TestUtils.getObjectFromJsonResource<Item>("item_response")
        private val RESULT_ITEM = Result.success(ITEM)
    }
}