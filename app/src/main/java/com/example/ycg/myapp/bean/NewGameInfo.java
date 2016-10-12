package com.example.ycg.myapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/5.
 */
public class NewGameInfo {

    /**
     * id : 864
     * fid : 20160923
     * appid : 1468993358
     * appname : 剑侠世界
     * typename : 角色扮演
     * appsize : 1.08MB
     * adimg : /allimgs/img_iapp/201609/_1474617628427.jpeg
     * appkfs : 西山居
     * iconurl : /allimgs/img_iapp/201607/_1468993333759.png
     * addtime : 2016-09-23
     * descs : 《剑侠世界》手游由西山居金牌团队历时两年倾力研发，在充斥着“快餐游戏”的手游红海中，是不可多得的匠心之作。《剑侠世界》手游在游戏上继承了剑网系列优秀的视觉、美术表现——真3D大世界，次世代视觉画面、极致物理光照，被称为“华丽得不像手机游戏”。百变换装系统更是深受女性玩家的喜爱。此外18种天气系统，细腻精致的场景，栩栩如生的人物，都让这款游戏饱受玩家欢迎。
     * critique : 本作最大的卖点，商城不卖数值。是的，商城目前沿袭了《剑侠情缘网络版三》的模式，出售外观及腰部、背部挂件。而玩家耳熟能详的月卡所提供的也仅仅是扫荡和雇佣杀手以及元宝分期付等功能。也就是说，玩家想变强，只有两个途径…——付费扫荡，或者肝。最后需要提到的就是游戏并不需要消耗时间点或月卡。所以，只要努力，人人都可以成为一代大侠，抱得美人归~

     * iszq : 0
     * typeid : 0
     * istop : 1
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int id;
        private int fid;
        private String appid;
        private String appname;
        private String typename;
        private String appsize;
        private String adimg;
        private String appkfs;
        private String iconurl;
        private String addtime;
        private String descs;
        private String critique;
        private int iszq;
        private int typeid;
        private int istop;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getAppsize() {
            return appsize;
        }

        public void setAppsize(String appsize) {
            this.appsize = appsize;
        }

        public String getAdimg() {
            return adimg;
        }

        public void setAdimg(String adimg) {
            this.adimg = adimg;
        }

        public String getAppkfs() {
            return appkfs;
        }

        public void setAppkfs(String appkfs) {
            this.appkfs = appkfs;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getDescs() {
            return descs;
        }

        public void setDescs(String descs) {
            this.descs = descs;
        }

        public String getCritique() {
            return critique;
        }

        public void setCritique(String critique) {
            this.critique = critique;
        }

        public int getIszq() {
            return iszq;
        }

        public void setIszq(int iszq) {
            this.iszq = iszq;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getIstop() {
            return istop;
        }

        public void setIstop(int istop) {
            this.istop = istop;
        }
    }
}
