package com.btlandroid.music.retrofit;

import com.btlandroid.music.model.Album;
import com.btlandroid.music.model.BaiHat;
import com.btlandroid.music.model.ChuDe;
import com.btlandroid.music.model.Mv;
import com.btlandroid.music.model.Playlist;
import com.btlandroid.music.model.QuangCao;
import com.btlandroid.music.model.TheLoai;
import com.btlandroid.music.model.TheLoaiTrongNgay;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("quangcao.php")
    Call<List<QuangCao>> getDataBanner();

    @GET("playlistforday.php")
    Call<List<Playlist>> getPlaylistCurrentDay();

    @GET("theloaichudeforday.php")
    Call<TheLoaiTrongNgay> getCategoryMusic();

    @GET("albumforday.php")
    Call<List<Album>> getListAlbumHot();

    @GET("baihatyeuthich.php")
    Call<List<BaiHat>> getListBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getListSongByBanner(@Field("IDQuangCao") String idBanner);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getListSongByPlaylist(@Field("IDPlaylist") String idPlaylist);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getListSongByTheLoai(@Field("IDTheLoai") String idTheLoai);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getListSongByAlbum(@Field("IDAlbum") String idAlbum);

    @GET("playlist.php")
    Call<List<Playlist>> getListPlaylist();


    @GET("chude.php")
    Call<List<ChuDe>> getListChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> getListTheLoaiByChuDe(@Field("IDChuDe") String IDChuDe);

    @GET("album.php")
    Call<List<Album>> getListAlbum();

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> updateCountLike(@Field("LuotThich") String like, @Field("IDBaiHat") String song);

    @FormUrlEncoded
    @POST("timkiembaihat.php")
    Call<List<BaiHat>> getListSongBySearch(@Field("tuKhoa") String keyWord);


//    @FormUrlEncoded
//    @POST("process_login_with_facebook.php")
//    Call<String> loginByFacebook(@Field("user_IdFacebook") String user_IdFacebook
//                                , @Field("user_name") String user_name
//                                , @Field("user_url_image") String user_url_image);
//
//    @FormUrlEncoded
//    @POST("process_login_with_google.php")
//    Call<String> loginByGoogle(@Field("user_name") String user_name
//            , @Field("user_email") String user_email
//            , @Field("user_IdGoogle") String user_IdGoogle
//            , @Field("user_url_image") String user_url_image);

    @GET("mvhot.php")
    Call<List<Mv>> getMVHot();

    @GET("danhsachmvall.php")
    Call<List<Mv>> getAllMVHot();

    @POST("danhsachmvvn.php")
    Call<List<Mv>> getVnMV();

    @POST("danhsachmvusuk.php")
    Call<List<Mv>> getUsUkMV();

    @POST("danhsachmvasia.php")
    Call<List<Mv>> getAsiaMV();

    @POST("danhsachmvhoatau.php")
    Call<List<Mv>> getHoaTauMV();

    @FormUrlEncoded
    @POST("list_baihat_yeuthich_user.php")
    Call<List<BaiHat>> getListSongLiked(@Field("user_id") Integer user_id);

//    @FormUrlEncoded
//    @GET("list_baihat_yeuthich_user.php")
//    Call<List<BaiHat>> getListSongLiked(@Query("id_FbOrGoogle") String id_FbOrGoogle
//            , @Query("type_login") int type_login);

//    @FormUrlEncoded
//    @POST("getUserID.php")
//    Call<Integer> getUserId(@Field("id_FbOrGoogle") String id_FbOrGoogle, @Field("type_login") int type_login);


    @FormUrlEncoded
    @POST("user.php")
    Call<String> user(@Field("user_name") TextInputLayout user_name, @Field("user_pass") TextInputLayout user_pass, @Field("user_email") TextInputLayout user_email);

    @FormUrlEncoded
    @POST("userlike.php")
    Call<String> handleLike(@Field("user_id") Integer user_id, @Field("id_baihat") Integer id_baihat);

}
