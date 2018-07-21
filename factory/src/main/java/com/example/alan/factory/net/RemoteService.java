package com.example.alan.factory.net;

import com.example.alan.factory.model.api.RspModel;
import com.example.alan.factory.model.api.account.AccountRspModel;
import com.example.alan.factory.model.api.account.LoginModel;
import com.example.alan.factory.model.api.account.RegisterModel;
import com.example.alan.factory.model.api.user.UserUpdateModel;
import com.example.alan.factory.model.card.UserCard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

    /**
     * 绑定设备ID
     * @param pushId
     * @return
     */
    @POST("account/bind/{pushId}")
    Call<RspModel<AccountRspModel>> accountBind(@Path(encoded = true, value = "pushId") String pushId);


    /**
     * 用户更新
     * @param model
     * @return
     */
    @PUT("user")
    Call<RspModel<UserCard>> userUpdate(@Body UserUpdateModel model);

    /**
     *获取联系人列表
     * @return
     */
    @GET("user/contact")
    Call<RspModel<List<UserCard>>> userContacts();


    /**
     *  找用户通过ID
     * @param userId
     * @return
     */
    @GET("user/{userId}")
    Call<RspModel<UserCard>> userFind(@Path("userId") String userId);


    /**
     * 通过用户
     * @param name
     * @return
     */
    @GET("user/search/{name}")
    Call<RspModel<List<UserCard>>> userSearch(@Path("name") String name);

    /**
     *用户关注接口
     */
    @PUT("user/follow/{userId}")
    Call<RspModel<UserCard>> userFollow(@Path("userId") String userId);

}
