package com.roguekingapps.bgdb.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) result.value = newValue
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse -> onApiSuccess(response)
                is ApiEmptyResponse -> onApiEmpty()
                is ApiErrorResponse -> onApiError(dbSource, response)
            }
        }
    }

    private fun onApiSuccess(response: ApiSuccessResponse<RequestType>) {
        appExecutors.diskIO().execute {
            saveCallResult(processResponse(response))
            appExecutors.mainThread().execute {
                result.addSource(loadFromDb()) { newData -> setValue(Resource.success(newData)) }
            }
        }
    }

    private fun onApiEmpty() {
        appExecutors.mainThread().execute {
            result.addSource(loadFromDb()) { newData -> setValue(Resource.success(newData)) }
        }
    }

    private fun onApiError(
        dbSource: LiveData<ResultType>,
        response: ApiErrorResponse<RequestType>
    ) {
        onFetchFailed()
        result.addSource(dbSource) { newData -> setValue(Resource.error(response.errorMessage, newData)) }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(data: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}