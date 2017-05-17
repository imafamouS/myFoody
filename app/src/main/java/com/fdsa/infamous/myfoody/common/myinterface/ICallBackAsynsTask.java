package com.fdsa.infamous.myfoody.common.myinterface;

/**
 * Created by apple on 5/10/17.
 */

public interface ICallBackAsynsTask<T> {
    void onSuccess(T object);

    void onFail(T object);

    void onRunnin();
}
