package com.example.alan.factory.net;

import com.example.alan.factory.model.api.RspModel;
import com.example.alan.factory.model.api.account.AccountRspModel;
import com.example.alan.factory.model.api.account.LoginModel;
import com.example.alan.factory.model.api.account.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author alan
 *         Date  2018/7/13.
 *         Function :
 *         Issue :
 */

public interface RemoteService {


    /**
     * 注册接口
     *
     * @param model 传入的是RegisterModel
     * @return 返回的是RspModel<AccountRspModel>
     */
    @POST("account/register")
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model);

    /**
     * 登录接口
     *
     * @param model LoginModel
     * @return RspModel<AccountRspModel>
     */
    @POST("account/login")
    Call<RspModel<AccountRspModel>> accountLogin(@Body LoginModel model);


}
