package com.fdsa.infamous.myfoody.common.myinterface;

/**
 * Created by apple on 5/10/17.
 */
//Interface dùng để thực hiện callback sau khi thực thi AsynsTask (nếu thành công, hay thất bại)
public interface ICallBackAsynsTask<T> {
    void onSuccess(T object);

    void onFail(T object);
}
