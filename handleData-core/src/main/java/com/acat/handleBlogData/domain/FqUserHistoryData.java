package com.acat.handleBlogData.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * {
 *     "uuid":"",        #用户唯一ID
 *     "platform":"",    #来源平台：FB、FQ、TW、IS、LI
 *     "data_source":"", #数据来源,数据来源文件名
 *     "create_time":"", #数据入库时间
 *     "importance":"",  #重要等级,0：普通 （默认）1：关注 2：重要 3：特别重要
 *     "remark":"",      #扩展字段，对重要数据手动编辑的批注信息
 *     "language_type":"",  #语种
 *     "source_id":"",      #自增id  ->   id
 *     "user_id":"",        #用户id
 *     "screen_name":"",    #昵称（人工标注） ->   user_name
 *     "use_name":"",       #目前无此属性（通过Firstname和Lastname拼接）      ->      name
 *     "user_url":"",       #主页    ->     canonical_url
 *     "user_avatar":"",    #头像地  ->      photo_url
 *     "local_photo_url":"", #本地头像url（服务器存放地址，仅保留文件名）
 *     "gender":"",      #用户性别，样例数据既有男，也有FEMALE，需统一编码（若为空，设置为0，-1是男，1是女）
 *     "country":"",     #国家编码（示例：TW）   ->    country_code
 *     "city":"",        #城市编码（示例：中山區, 台北市）   ->      home_city
 *     "user_type":"",   #用户类型（个人、公开page、其他）
 *     "verified":"",    #是否认证 1：认证，0：非认证
 *     "followers_count":"",   #追随者数量,    ->      follower_count
 *     "friend_count":"",      #关注者数量     ->      following_count
 *     "post_count":"",        #评论数       ->      tips_count
 *     "source_create_time":"",#采集时间   ->    create_time
 *     "contact":"",     #社交联系方式
 *     "alg_result":"",  #预留
 *     "bio":"",         #用户简介
 *     "checkins_count":"",    #签到数量
 *     "first_name":"",        #名称前缀
 *     "last_name":"",         #名称后缀
 *     "lenses":"",            #镜头
 *     "lists":"",             #列表
 *     "lists_count":"",       #列表数
 *     "person":"",            #人物标签
 *     "photo":"",             #图片源数据
 *     "photo_prefix":"",      #头像前缀
 *     "photo_suffix":"",      #头像后缀
 *     "real_name":"",         #人工维护真实姓名（样例数据为空）
 *     "recent_list":"",       #最近列表
 *     "send_state":"",        #发送状态
 *     "top_tips":"",          #top评论
 *     "visibility":"",        #可见性
 *     "friends":"",           #朋友数量
 * }
 */
@Data
@Builder
@Document(indexName = "fq_history")
public class FqUserHistoryData {

    /**
     * 用户唯一ID
     */
//    @Id
    private String uuid;

    /**
     * 来源平台：FB、FQ、TW、IS、LI
     */
    private String platform;

    /**
     * 数据来源,数据来源文件名
     */
    private String data_source;

    /**
     * 数据入库时间
     */
    private String create_time;

    /**
     * 重要等级,0：普通 （默认）1：关注 2：重要 3：特别重要
     */
    private String importance;

    /**
     * 扩展字段，对重要数据手动编辑的批注信息
     */
    private String remark;

    /**
     * 语种
     */
    private String language_type;

    /**
     * 自增id  ->   id
     */
    private String source_id;

    /**
     * 用户id
     */
  //  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    @Id
    private String user_id;

    /**
     * 昵称（人工标注） ->   user_name
     */
   // @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String screen_name;

    /**
     * 目前无此属性（通过Firstname和Lastname拼接）      ->      name
     */
 //   @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String use_name;

    /**
     * 主页    ->     canonical_url
     */
    private String user_url;

    /**
     * 头像地  ->      photo_url
     */
    private String user_avatar;

    /**
     * 本地头像url（服务器存放地址，仅保留文件名）
     */
    private String local_photo_url;

    /**
     * 用户性别，样例数据既有男，也有FEMALE，需统一编码（若为空，设置为0，-1是男，1是女）
     */
    private String gender;

    /**
     * 国家编码（示例：TW）   ->    country_code
     */
   // @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String country;

    /**
     * 城市编码（示例：中山區, 台北市）   ->      home_city
     */
   // @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String city;

    /**
     * 用户类型（个人、公开page、其他）
     */
    private String user_type;

    /**
     * 是否认证 1：认证，0：非认证
     */
    private String verified;

    /**
     * 追随者数量,    ->      follower_count
     */
    private String followers_count;

    /**
     * 关注者数量     ->      following_count
     */
    private String friend_count;

    /**
     * 评论数       ->      tips_count
     */
    private String post_count;

    /**
     * 采集时间   ->    create_time
     */
    private String source_create_time;

    /**
     * 社交联系方式
     */
    private String contact;

    /**
     * 预留
     */
    private String alg_result;

    /**
     * 用户简介
     */
    private String bio;

    /**
     * 签到数量
     */
    private String checkins_count;

    /**
     * 名称前缀
     */
    private String first_name;

    /**
     * 名称后缀
     */
    private String last_name;

    /**
     * 镜头
     */
    private String lenses;

    /**
     * 列表
     */
    private String lists;

    /**
     * 列表数
     */
    private String lists_count;

    /**
     * 人物标签
     */
    private String person;

    /**
     * 图片源数据
     */
    private String photo;

    /**
     * 头像前缀
     */
    private String photo_prefix;

    /**
     * 头像后缀
     */
    private String photo_suffix;

    /**
     * 人工维护真实姓名（样例数据为空）
     */
    private String real_name;

    /**
     * 最近列表
     */
    private String recent_list;

    /**
     * 发送状态
     */
    private String send_state;

    /**
     * top评论
     */
    private String top_tips;

    /**
     * 可见性
     */
    private String visibility;

    /**
     * 朋友数量
     */
    private String friends;

    /**
     * 算法标签
     */
    private String alg_remark;

    /**
     * 喜欢数
     */
    private String like_count;

    /**
     * 语言
     */
    private String language;

    /**
     * 字段非空值累加
     */
//    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private Integer integrity;

    /**
     * {
     *     "contact":"{\"twitter\": \"katiebethea\", \"facebook\": \"692622425\"}",
     *     "alg_remark":"junshi "
     *     "checkins_count":"0",
     *     "first_name":"Katie",
     *     "last_name":"Bethea",
     *     "lists_count":"0",
     *     "photo":"",
     *     "photo_prefix":"https://fastly.4sqi.net/img/user/",
     *     "photo_suffix":"/27758970-4QWFBXA5VKBXCBYT.jpg",
     *     "visibility":"",
     *     "friends":"0",
     *     "uuid":"deafc408-3f17-4dea-84a1-fc8da75f6553",
     *     "platform":"FQ",
     *     "data_source":"fq_user_data_2022-05-10.json",
     *     "create_time":"08:42:09.627",
     *     "importance":"0",
     *     "remark":"",
     *     "language_type":"la",
     *     "source_id":"36",
     *     "user_id":"27758970",
     *     "screen_name":"",
     *     "use_name":"Katie Bethea",
     *     "user_url":"https://foursquare.com/user/27758970",
     *     "local_photo_url":"fq_Info_27758970_photo.jpg",
     *     "gender":"0",
     *     "country":"US",
     *     "city":"Columbia, SC",
     *     "verified":"",
     *     "followers_count":"0",
     *     "friend_count":"0",
     *     "post_count":"2",
     *     "like_count":"0",  cuo
     *     "language":"la",   cuo
     * }
     */

    public FqUserHistoryData() {}

    public FqUserHistoryData(String uuid, String platform, String data_source, String create_time, String importance, String remark, String language_type, String source_id, String user_id, String screen_name, String use_name, String user_url, String user_avatar, String local_photo_url, String gender, String country, String city, String user_type, String verified, String followers_count, String friend_count, String post_count, String source_create_time, String contact, String alg_result, String bio, String checkins_count, String first_name, String last_name, String lenses, String lists, String lists_count, String person, String photo, String photo_prefix, String photo_suffix, String real_name, String recent_list, String send_state, String top_tips, String visibility, String friends, String alg_remark, String like_count, String language, Integer integrity) {
        this.uuid = uuid;
        this.platform = platform;
        this.data_source = data_source;
        this.create_time = create_time;
        this.importance = importance;
        this.remark = remark;
        this.language_type = language_type;
        this.source_id = source_id;
        this.user_id = user_id;
        this.screen_name = screen_name;
        this.use_name = use_name;
        this.user_url = user_url;
        this.user_avatar = user_avatar;
        this.local_photo_url = local_photo_url;
        this.gender = gender;
        this.country = country;
        this.city = city;
        this.user_type = user_type;
        this.verified = verified;
        this.followers_count = followers_count;
        this.friend_count = friend_count;
        this.post_count = post_count;
        this.source_create_time = source_create_time;
        this.contact = contact;
        this.alg_result = alg_result;
        this.bio = bio;
        this.checkins_count = checkins_count;
        this.first_name = first_name;
        this.last_name = last_name;
        this.lenses = lenses;
        this.lists = lists;
        this.lists_count = lists_count;
        this.person = person;
        this.photo = photo;
        this.photo_prefix = photo_prefix;
        this.photo_suffix = photo_suffix;
        this.real_name = real_name;
        this.recent_list = recent_list;
        this.send_state = send_state;
        this.top_tips = top_tips;
        this.visibility = visibility;
        this.friends = friends;
        this.alg_remark = alg_remark;
        this.like_count = like_count;
        this.language = language;
        this.integrity = integrity;
    }
}