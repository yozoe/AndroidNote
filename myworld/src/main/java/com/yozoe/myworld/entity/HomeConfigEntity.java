package com.yozoe.myworld.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangdong on 16/8/12.
 */
public class HomeConfigEntity implements Serializable {


    private static final long serialVersionUID = -5359447643615773456L;
    private List<HomeConfigModule> modules;

    public List<HomeConfigModule> getModules() {
        return modules;
    }

    public void setModules(List<HomeConfigModule> modules) {
        this.modules = modules;
    }

    public static class HomeConfigModule implements Serializable {

        private static final long serialVersionUID = -7907094370391188156L;

        public static final String MODULE_BLOCKS = "blocks";
        public static final String MODULE_CIRCLE = "circle";
        public static final String MODULE_SCOLL_BLOCKS = "scollBlocks";
        public static final String MODULE_GUESS_LIST = "carList";

        private String title;
        //大模块的列数
        private int columns;
        //大模块的行数
        private int rows;
        //每项之间的间隔.与屏幕精度有关,中间区域减半
        private int cellPadding;
        //用于标识些模块
        private String moduleID;
        //模块的类型，blocks为块型，如各种专区 ，circle为圆型，如价格，3文字分块，比如热租车型
        private String moduleType;
        //背景支持颜色值和网络图片
        private String background;
        //为空无效果，zoomin,缩小,zoomout 放大，动画完成后再响应
        private String pressType;
        //每个模块下面的子项
        private List<HomeConfigCell> cells;
        //此模块是否需要动画
        private boolean needAnim;

        //------------>blocks
        //cell (高/宽)比例
        private float cellRatio;

        //------------>circle
        //整个区域的(高/宽)比例.
        private float heightRatio;

        //------------>scollBlocks
        private List<String> summaries;

        @SerializedName("carListConfig")
        private CarListBlock carListBlock;

        private String subTitle;

        @SerializedName("goto")
        private String titleGoto;

        @SerializedName("params")
        private String titleParams;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getColumns() {
            return columns;
        }

        public void setColumns(int columns) {
            this.columns = columns;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public int getCellPadding() {
            return cellPadding;
        }

        public void setCellPadding(int cellPadding) {
            this.cellPadding = cellPadding;
        }

        public String getModuleID() {
            return moduleID;
        }

        public void setModuleID(String moduleID) {
            this.moduleID = moduleID;
        }

        public String getModuleType() {
            return moduleType;
        }

        public void setModuleType(String moduleType) {
            this.moduleType = moduleType;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getPressType() {
            return pressType;
        }

        public void setPressType(String pressType) {
            this.pressType = pressType;
        }

        public List<HomeConfigCell> getCells() {
            return cells;
        }

        public void setCells(List<HomeConfigCell> cells) {
            this.cells = cells;
        }

        public boolean isNeedAnim() {
            return needAnim;
        }

        public void setNeedAnim(boolean needAnim) {
            this.needAnim = needAnim;
        }

        public float getCellRatio() {
            return cellRatio;
        }

        public void setCellRatio(float cellRatio) {
            this.cellRatio = cellRatio;
        }

        public float getHeightRatio() {
            return heightRatio;
        }

        public void setHeightRatio(float heightRatio) {
            this.heightRatio = heightRatio;
        }

        public List<String> getSummaries() {
            return summaries;
        }

        public void setSummaries(List<String> summaries) {
            this.summaries = summaries;
        }

        public CarListBlock getCarListBlock() {
            return carListBlock;
        }

        public void setCarListBlock(CarListBlock carListBlock) {
            this.carListBlock = carListBlock;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getTitleGoto() {
            return titleGoto;
        }

        public void setTitleGoto(String titleGoto) {
            this.titleGoto = titleGoto;
        }

        public String getTitleParams() {
            return titleParams;
        }

        public void setTitleParams(String titleParams) {
            this.titleParams = titleParams;
        }
    }

    public static class HomeConfigCell implements Serializable {

        private static final long serialVersionUID = -3288684211964470722L;

        private String cellID;
        //名字。如果是去car search页面，需要带过去
        private String cellName;
        //背景支持颜色值和网络图片
        private String background;
        //参数
        private String params;
        //跳转类型，car_search->搜索，cluster,聚类,webview->进入网页，cardetail->进入车辆详情，favorites->收藏页面
        @SerializedName("goto")
        private String gotoTarget;
        //用于定制此模块的说明。如果是去car search页面，需要带过去
        private String introduce;

        //------------>blocks
        //小模块的列数
        private int cellColumns;
        //小模块的行数
        private int cellRows;
        //小模块的起始列,0开始计数
        private int positionX;
        //小模块的起始行
        private int positionY;
        //图标
        private String icon;
        //图标的展示形式，fitXY->平铺，center居中等等
        private String iconScaleType;


        //------------>circle
        private float positionXPer;
        private float positionYPer;
        private String backgroundColor;
        //文
        private String text;
        //半径
        private float radius;

        public String getCellID() {
            return cellID;
        }

        public void setCellID(String cellID) {
            this.cellID = cellID;
        }

        public String getCellName() {
            return cellName;
        }

        public void setCellName(String cellName) {
            this.cellName = cellName;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public String getGotoTarget() {
            return gotoTarget;
        }

        public void setGotoTarget(String gotoTarget) {
            this.gotoTarget = gotoTarget;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getCellColumns() {
            return cellColumns;
        }

        public void setCellColumns(int cellColumns) {
            this.cellColumns = cellColumns;
        }

        public int getCellRows() {
            return cellRows;
        }

        public void setCellRows(int cellRows) {
            this.cellRows = cellRows;
        }

        public int getPositionX() {
            return positionX;
        }

        public void setPositionX(int positionX) {
            this.positionX = positionX;
        }

        public int getPositionY() {
            return positionY;
        }

        public void setPositionY(int positionY) {
            this.positionY = positionY;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIconScaleType() {
            return iconScaleType;
        }

        public void setIconScaleType(String iconScaleType) {
            this.iconScaleType = iconScaleType;
        }

        public float getPositionXPer() {
            return positionXPer;
        }

        public void setPositionXPer(float positionXPer) {
            this.positionXPer = positionXPer;
        }

        public float getPositionYPer() {
            return positionYPer;
        }

        public void setPositionYPer(float positionYPer) {
            this.positionYPer = positionYPer;
        }

        public String getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public float getRadius() {
            return radius;
        }

        public void setRadius(float radius) {
            this.radius = radius;
        }
    }

    public static class CarListBlock {
        @SerializedName("api")
        private String apiForRecommand;

        @SerializedName("title")
        private String bottomText;

        @SerializedName("goto")
        private String listItemGoto;

        @SerializedName("params")
        private String searchParams;

        private int total;

        private int pagesize;

        //文
        private String introduce;
        private String cellID;
        private String cellName;

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getCellID() {
            return cellID;
        }

        public void setCellID(String cellID) {
            this.cellID = cellID;
        }

        public String getCellName() {
            return cellName;
        }

        public void setCellName(String cellName) {
            this.cellName = cellName;
        }

        public String getApiForRecommand() {
            return apiForRecommand;
        }

        public void setApiForRecommand(String apiForRecommand) {
            this.apiForRecommand = apiForRecommand;
        }

        public String getBottomText() {
            return bottomText;
        }

        public void setBottomText(String bottomText) {
            this.bottomText = bottomText;
        }

        public String getListItemGoto() {
            return listItemGoto;
        }

        public void setListItemGoto(String listItemGoto) {
            this.listItemGoto = listItemGoto;
        }

        public String getSearchParams() {
            return searchParams;
        }

        public void setSearchParams(String searchParams) {
            this.searchParams = searchParams;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

    }
}
